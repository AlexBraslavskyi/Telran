package telran.logs.bugs.services;

import java.util.Date;
import java.util.Set;
import java.util.function.Consumer;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;

@Service
public class LogsAnalyzerService {
	static Logger LOG = LoggerFactory.getLogger(LogsAnalyzerService.class);
	@Value("${app-binding-name-exception}")
	String exception;

	@Value("${app-binding-name-error}")
	String error;
	@Autowired
	StreamBridge streamBridge;
	@Autowired
	Validator validator;
	

	@Autowired
	LogsAnalyzerService analyzerService;
	@Bean
	Consumer<LogDto> getAnalyzerBean() {
		
		return analyzerService::analyzerMethod;
	}
	public void analyzerMethod(LogDto logDto) {
		LOG.debug("recieved log {}", logDto);
		Set<ConstraintViolation<LogDto>> violations = validator.validate(logDto);
		if (!violations.isEmpty()) {
			violations.forEach(cv -> LOG.error("error_log : {}; field: {}; message: {}", logDto, cv.getPropertyPath(),
					cv.getMessage()));
			LogDto exceptionLog = new LogDto(new Date(), LogType.BAD_REQUEST_EXCEPTION, LogsAnalyzerService.class.getName(), 0,
					violations.toString());
			streamBridge.send(error, exceptionLog);

		} else if (logDto.logType != LogType.NO_EXCEPTION) {
			LOG.debug("exception_log: {} sent to binding name: {}", logDto, exception);
			streamBridge.send(exception, logDto);

		} 
	
	}
}
