package telran.logs.bugs;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Consumer;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.jpa.entities.Artifact;
import telran.logs.bugs.jpa.entities.Bug;
import telran.logs.bugs.jpa.entities.BugStatus;
import telran.logs.bugs.jpa.entities.OpenningMethod;
import telran.logs.bugs.jpa.entities.Programmer;
import telran.logs.bugs.jpa.entities.Seriousness;

@Service
public class BugsOpenningImpl {
	
	static Logger LOG = LoggerFactory.getLogger(BugsOpenningAppl.class);
	@Autowired
	Validator validator;
	@Autowired
	BugsRepo bugsRepo;
	@Autowired
	ArtifactsRepo artifactRepo;
	@Autowired
	ProgrammersRepo programmersRepo;
	
	@Bean
	Consumer<LogDto> getLogDtoConsumer() {
		return this::createAndSaveBug;
	}

	void createAndSaveBug(LogDto logDto) {
		LOG.debug("recived log {}", logDto);
		Set<ConstraintViolation<LogDto>> violations = validator.validate(logDto);
		if (!violations.isEmpty()) {
			Bug bug = new Bug(logDto.logType +" "+ logDto.result, LocalDate.now(), null,
					getBugStatus(logDto), getSeriousness(logDto), OpenningMethod.AUTOMATIC, getProgrammer(logDto));
			bugsRepo.save(bug);
			LOG.warn("BUG: {} saved to DB", bug);
			
			}
	}

	private Programmer getProgrammer(LogDto logDto) {
		
			Artifact artifact = artifactRepo.findById(logDto.artifact).orElse(null);
			if(artifact!=null) {
			Programmer programmer = artifact.getProgrammer();
			return programmer;
			}
			LOG.warn("programmer not found");
//			programmer can't be null
		return null;
	}

	private Seriousness getSeriousness(LogDto logDto) {
		switch (logDto.logType) {
		case AUTHENTICATION_EXCEPTION:
			return Seriousness.BLOCKING;
		case AUTHORIZATION_EXCEPTION:
			return Seriousness.CRITICAL;
		default:
			return Seriousness.MINOR;
		}
	}

	private BugStatus getBugStatus(LogDto logDto) {
		Programmer programmer = getProgrammer(logDto);
			if (programmer.getId()!=0 && programmersRepo.existsById(programmer.getId())) {
				return BugStatus.ASSIGNED;
			}
		return BugStatus.OPENNED;
	}

}
