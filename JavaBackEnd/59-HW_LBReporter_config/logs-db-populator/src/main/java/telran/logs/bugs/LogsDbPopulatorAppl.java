package telran.logs.bugs;

import java.util.Date;
import java.util.Set;
import java.util.function.Consumer;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;
import telran.logs.bugs.mongo.doc.LogDoc;
import telran.logs.bugs.mongo.repo.LogsRepo;

@SpringBootApplication
public class LogsDbPopulatorAppl {

	public static void main(String[] args) {
		LOG.info("recieved log {}", "Start populator app");
		SpringApplication.run(LogsDbPopulatorAppl.class, args);
		LOG.info("recieved log {}", "Finished populator app");
	}
	@Bean
	Consumer<LogDto> getLogDtoConsumer() {
		return this::takeAndSaveLogDto;
	}
	static Logger LOG = LoggerFactory.getLogger(LogsDbPopulatorAppl.class);
	@Value("${app-binding-name:exceptions-out-0}")
	String bindingName;
	@Autowired
	Validator validator;
	@Autowired
	LogsRepo logsRepository;
	@Autowired
	StreamBridge streamBridge;
	LogDto inValidDto;
	void takeAndSaveLogDto(LogDto logDto) {
		//TODO taking and saving to MongoDB logDto
		
		 Set<ConstraintViolation<LogDto>> violations = validator.validate(logDto);
		 if (!violations.isEmpty()) {
			violations.forEach(cv -> 
			{inValidDto = new LogDto(new Date(), LogType.BAD_REQUEST_EXCEPTION, 
					LogsDbPopulatorAppl.class.getCanonicalName(), 0, cv.getPropertyPath()+" - "+cv.getMessage());
				LOG.error("recieved log {}", inValidDto);
				logsRepository.save(new LogDoc(inValidDto));
				streamBridge.send(bindingName, inValidDto);
				});
		 } else {
			 logsRepository.save(new LogDoc(logDto));
			 LOG.debug("recieved log {}", logDto);
			 if (logDto.logType != null && logDto.logType != LogType.NO_EXCEPTION) {
					streamBridge.send(bindingName, logDto);
				}
		 }	
	}
}
