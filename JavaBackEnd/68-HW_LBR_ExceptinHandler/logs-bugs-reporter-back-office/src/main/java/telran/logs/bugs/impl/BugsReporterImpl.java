package telran.logs.bugs.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.logs.bugs.dto.ArtifactDto;
import telran.logs.bugs.dto.AssignBugData;
import telran.logs.bugs.dto.BugAssignDto;
import telran.logs.bugs.dto.BugDto;
import telran.logs.bugs.dto.BugResponseDto;
import telran.logs.bugs.dto.BugStatus;
import telran.logs.bugs.dto.CloseBugData;
import telran.logs.bugs.dto.EmailBugsCount;
import telran.logs.bugs.dto.OpenningMethod;
import telran.logs.bugs.dto.ProgrammerDto;
import telran.logs.bugs.dto.Seriousness;
import telran.logs.bugs.dto.SeriousnessBugCount;
import telran.logs.bugs.exceptions.DuplicatedException;
import telran.logs.bugs.exceptions.NotFoundException;
import telran.logs.bugs.interfaces.BugsReporter;
import telran.logs.bugs.jpa.entities.Artifact;
import telran.logs.bugs.jpa.entities.Bug;
import telran.logs.bugs.jpa.entities.Programmer;
import telran.logs.bugs.jpa.repo.ArtifactRepository;
import telran.logs.bugs.jpa.repo.BugRepository;
import telran.logs.bugs.jpa.repo.ProgrammerRepository;
@Service
public class BugsReporterImpl implements BugsReporter {


public static final String CLOSING_DESCRIPTION = "\nClosing description:\n";
BugRepository bugRepository;
ArtifactRepository artifactRepository;
ProgrammerRepository programmerRepository;

public BugsReporterImpl(BugRepository bugRepository, ArtifactRepository artifactRepository,
		ProgrammerRepository programmerRepository) {
	this.bugRepository = bugRepository;
	this.artifactRepository = artifactRepository;
	this.programmerRepository = programmerRepository;
}
	@Override
	@Transactional
	public ProgrammerDto addProgrammer(ProgrammerDto programmerDto) {
		if(programmerRepository.existsById(programmerDto.id)) {
			throw new DuplicatedException(String.format("programmer with id: %d already exist", programmerDto.id));
		}
		programmerRepository
		.save(new Programmer(programmerDto.id, programmerDto.name, programmerDto.email));
		return programmerDto;
	
	}

	@Override
	public ArtifactDto addArtifact(ArtifactDto artifactDto) {
		Programmer programmer = programmerRepository.findById(artifactDto.programmerId).orElse(null);
		if(programmer == null) {
			throw new NotFoundException(String.format("programmer with id: %d not found", artifactDto.programmerId));
		}
		artifactRepository.save(new Artifact(artifactDto.artifactId,programmer ));
		return artifactDto;
	}

	@Override
	@Transactional
	public BugResponseDto openBug(BugDto bugDto) {
		LocalDate dateOpen = bugDto.dateOpen != null ? bugDto.dateOpen : LocalDate.now();
		Bug bug = new Bug
				(bugDto.description, dateOpen, null, BugStatus.OPENNED,
						bugDto.seriousness,OpenningMethod.MANUAL, null);
		bugRepository.save(bug);
		return toBugResponseDto(bug);
	}

	private BugResponseDto toBugResponseDto(Bug bug) {
		
		Programmer programmer = bug.getProgrammer();
		long programmerId = programmer == null ? 0 : programmer.getId();
		return new BugResponseDto
				(bug.getId(), bug.getSeriousness(), bug.getDescription(),
						bug.getDateOpen(), programmerId, bug.getDateClose(),
						bug.getStatus(), bug.getOpenningMethod());
	}
	@Override
	@Transactional
	public BugResponseDto openAndAssignBug(BugAssignDto bugDto) {
		Programmer programmer = programmerRepository.findById(bugDto.programmerId).orElse(null);
		if(programmer == null) {
			throw new NotFoundException(String.format("assigning can't be done - no programmer"
					+ " with id %d", bugDto.programmerId));
		}
		LocalDate dateOpen = bugDto.dateOpen != null ? bugDto.dateOpen : LocalDate.now();
		Bug bug = 
				new Bug(bugDto.description, dateOpen, null, BugStatus.ASSIGNED,
						bugDto.seriousness, OpenningMethod.MANUAL, programmer);
		bug = bugRepository.save(bug);
		return toBugResponseDto(bug);
	}

	@Override
	@Transactional
	public void assignBug(AssignBugData assignData) {
		Bug bug = bugRepository.findById(assignData.bugId).orElse(null);
		Programmer programmer = programmerRepository.findById(assignData.programmerId)
				.orElse(null);
		if(bug == null) {
			throw new NotFoundException(String.format("bug with id: %d not found", assignData.bugId));
		}
		if(programmer == null) {
			throw new NotFoundException(String.format("programmer with id: %d not found", assignData.programmerId));
		}
		if(bug.getStatus() == BugStatus.ASSIGNED) {
			throw new DuplicatedException(String.format("bug with id %d already assigned", assignData.bugId));
		}
		bug.setDescription(bug.getDescription() + BugsReporter.ASSIGNMENT_DESCRIPTION_TITLE +
		assignData.description);
		bug.setStatus(BugStatus.ASSIGNED);
		bug.setProgrammer(programmer);
	}

	@Override
	public List<BugResponseDto> getNonAssignedBugs() {
		
		List<Bug> bugs = bugRepository.findByStatus(BugStatus.OPENNED);
		return toListBugResponseDto(bugs );
	}

	@Override
	@Transactional
	public void closeBug(CloseBugData closeData) {
		Bug bug = bugRepository.findById(closeData.bugId).orElse(null);
		if(bug == null) {
			throw new NotFoundException(String.format("bug with id: %d not found", closeData.bugId));
		}
		LocalDate dateClose = closeData.dateClose == null ? LocalDate.now() : closeData.dateClose;
		bug.setStatus(BugStatus.CLOSED);
		bug.setDateClose(dateClose);
		bug.setDescription(bug.getDescription() + CLOSING_DESCRIPTION + closeData.description);
		
	}

	@Override
	public List<BugResponseDto> getUnClosedBugsMoreDuration(int days) {
		if(days < 1 && days > Integer.MAX_VALUE) {
			throw new ConstraintViolationException(String.format("not correct parametr days : %d , must be positive value", days), null);
		}
		LocalDate dateOpen = LocalDate.now().minusDays(days);
		List<Bug> bugs = bugRepository.findByStatusNotAndDateOpenBefore(BugStatus.CLOSED, dateOpen );
		return toListBugResponseDto(bugs );
	}

	@Override
	public List<BugResponseDto> getBugsProgrammer(long programmerId) {
		if(programmerId < 1) {
			throw new ConstraintViolationException(String.format("not correct parametr programmerId : %d , must be positive value > 0", programmerId), null);
		}
		List<Bug> bugs = bugRepository.findByProgrammerId(programmerId);
		return bugs.isEmpty() ? new LinkedList<>() : toListBugResponseDto(bugs);
	}

	private List<BugResponseDto> toListBugResponseDto(List<Bug> bugs) {
		return bugs.stream().map(this::toBugResponseDto).collect(Collectors.toList());
	}
	@Override
	public List<EmailBugsCount> getEmailBugsCounts() {
		
		List<EmailBugsCount> result = bugRepository.emailBugsCounts();
		return result ;
	}

	@Override
	public List<String> getProgrammersMostBugs(int nProgrammers) {
		if(nProgrammers < 1 && nProgrammers > Integer.MAX_VALUE) {
			throw new ConstraintViolationException(String.format("not correct parametr nProgrammers : %d , must be positive value", nProgrammers), null);
		}
		return bugRepository.prgrammersMostBugs(nProgrammers);
	}

	@Override
	public List<String> getProgrammersLeastBugs(int nProgrammers) {
		if(nProgrammers < 1 && nProgrammers > Integer.MAX_VALUE) {
			throw new ConstraintViolationException(String.format("not correct parametr nProgrammers : %d , must be positive value", nProgrammers), null);
		}
		return bugRepository.programmersLeastBugs(nProgrammers);
	}
	@Override
	public List<SeriousnessBugCount> getSeriousnessBugCounts() {
		
		
		return Arrays.stream(Seriousness.values()).map(s -> 
		new SeriousnessBugCount(s, bugRepository.countBySeriousness(s))
		).sorted((s1, s2) -> Long.compare(s2.getCount(), s1.getCount())) .collect(Collectors.toList());
	}
	@Override
	public List<Seriousness> getSeriousnessTypesWithMostBugs(int nTypes) {
		if(nTypes < 1 && nTypes > Integer.MAX_VALUE) {
			throw new ConstraintViolationException(String.format("not correct parametr nTypes : %d , must be positive value", nTypes), null);
		}
		return bugRepository.seriousnessMostBugs(nTypes);
	}

}
