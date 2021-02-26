package telran.logs.bugs.impl;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.logs.bugs.dto.ArtifactDto;
import telran.logs.bugs.dto.AssignBugData;
import telran.logs.bugs.dto.BugAssignDto;
import telran.logs.bugs.dto.BugDto;
import telran.logs.bugs.dto.BugResponseDto;
import telran.logs.bugs.dto.BugStatus;
import telran.logs.bugs.dto.CloseBugDate;
import telran.logs.bugs.dto.EmailBugsCount;
import telran.logs.bugs.dto.OpeningMethod;
import telran.logs.bugs.dto.ProgrammerDto;
import telran.logs.bugs.interfaces.BugsReporter;
import telran.logs.bugs.jpa.entities.Bug;
import telran.logs.bugs.jpa.entities.Programmer;
import telran.logs.bugs.jpa.repo.ArtifactRepository;
import telran.logs.bugs.jpa.repo.BugRepository;
import telran.logs.bugs.jpa.repo.ProgrammerRepository;
@Service
public class BugsReporterImpl implements BugsReporter {

	BugRepository bugRepository;
	ArtifactRepository artifactRepository;
	ProgrammerRepository programmerRepository;
	
	
	
	@Autowired
	public BugsReporterImpl(BugRepository bugRepository, ArtifactRepository artifactRepository,
			ProgrammerRepository programmerRepository) {
		this.bugRepository = bugRepository;
		this.artifactRepository = artifactRepository;
		this.programmerRepository = programmerRepository;
	}

	@Override
	@Transactional
	public ProgrammerDto addProgrammer(ProgrammerDto programmerDto) {
		// FIXME exceptions handling and key duplication check
		
		programmerRepository.save(new Programmer(programmerDto.id, programmerDto.name, programmerDto.email));
		return programmerDto;
	}

	@Override
	@Transactional
	public BugResponseDto openBug(BugDto bugDto) {
		
		LocalDate dateOpen = bugDto.dateOpen != null? bugDto.dateOpen: LocalDate.now();
		Bug bug = new Bug(bugDto.description, dateOpen, null, BugStatus.OPENED, bugDto.seriousness, OpeningMethod.MANUAL, null);
		bugRepository.save(bug);
		return toBugResponseDto(bug);
	}

	private BugResponseDto toBugResponseDto(Bug bug) {
		Programmer programmer = bug.getProgrammer();
		Long programmerId = programmer == null ? 0 : programmer.getId() ;
		return new BugResponseDto(bug.getId(), bug.getSeriousness(), bug.getDescription(), bug.getDateOpen(), programmerId, bug.getDateClose(), 
				bug.getStatus(), bug.getOpeningMethod());
	}

	@Override
	@Transactional
	public BugResponseDto openAndAssignBug(BugAssignDto bugDto) {
		// FIXME exceptions 
		
		Programmer programmer = programmerRepository.findById(bugDto.programmerId).orElse(null); 
//		exception if null
LocalDate dateOpen = bugDto.dateOpen !=null ? bugDto.dateOpen: LocalDate.now();
		
		Bug bug = new Bug(bugDto.description, dateOpen, null, BugStatus.ASSIGNED, bugDto.seriousness,
					OpeningMethod.MANUAL, programmer);
		bug = bugRepository.save(bug);
		return toBugResponseDto(bug);
	}

	@Override
	@Transactional
	public void assingBug(AssignBugData assignDate) {
		// FIXME exceptions 

		Bug bug = bugRepository.findById(assignDate.bugId).orElse(null);
//		exception
		
		bug.setDescription(bug.getDescription()+ "\nAssignment Description: " + assignDate.description);
		Programmer programmer = programmerRepository.findById(assignDate.programmerId).orElse(null);
//		exception
		bug.setStatus(BugStatus.ASSIGNED);
		bug.setProgrammer(programmer);
	}


	@Override
	public List<BugResponseDto> getBugsProgrammer(long programmerId) {
	List<Bug> bugs = bugRepository.findByProgrammerId(programmerId);
	
		return bugs.isEmpty()? new LinkedList<>() : toBugResponseDto(bugs);
	}

	private List<BugResponseDto> toBugResponseDto(List<Bug> bugs) {
		
		return bugs.stream().map(this::toBugResponseDto).collect(Collectors.toList());
	}
	
	@Override
	public ArtifactDto addArtifact(ArtifactDto artifactDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<BugResponseDto> getNonAssignBugs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeBug(CloseBugDate closeDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BugResponseDto> getUnClosedBugsMoreDuration(int days) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<EmailBugsCount> getEmailBugsCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getProgrammerMostBugs(int nProgrammers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getProgrammersLeastBugs(int nProgrammers) {
		// TODO Auto-generated method stub
		return null;
	}

}
