package telran.logs.bugs;

import java.util.Set;
import java.util.function.Consumer;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.mongo.doc.LogDoc;


@SpringBootApplication
public class LogsDbPopulatorAppl {
	@Autowired
	LogsRepoDB logs;
	@Autowired
    Validator validator;
	public static void main(String[] args) {
		SpringApplication.run(LogsDbPopulatorAppl.class, args);

	}
	
	
	@Bean
	Consumer<LogDto> getLogDtoConsumer() {
		return this::takeAndSaveLogDto;
	}
	void takeAndSaveLogDto(LogDto logDto) {
		 Set<ConstraintViolation<LogDto>> validations = validator.validate(logDto);

		  if (!validations.isEmpty()) {
		      System.out.println(validations.iterator().next().getMessage());
		  } else {
		      logs.save(new LogDoc(logDto));
		  }
	}

}
