package telran.logs.bugs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;
import telran.logs.bugs.mongo.doc.LogDoc;
import telran.logs.bugs.mongo.repo.LogsRepo;





@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class LogsDbPopulatorTest {
	static Logger LOG = LoggerFactory.getLogger(LogsDbPopulatorTest.class);
	@Value("${app-binding-name-exception}")
	String exception;

	@Value("${app-binding-name-error}")
	String error;
	@Value("${app-binding-name-no-exception}")
	String no_exception;
	@Autowired
	InputDestination input;
	@Autowired
	OutputDestination output;
	@Autowired
	LogsRepo logsRepo;
	
	@BeforeEach
	void setUp() {
		logsRepo.deleteAll();

	}

	@Test
	void takeLogDtoAndSaveNormal() {
		LogDto logDto = new LogDto(new Date(), LogType.NO_EXCEPTION, "artifact", 0, "");
		sendLog(logDto);
		List<LogDoc> logDocs = logsRepo.findAll();
		assertEquals(1, logDocs.size());
		assertEquals(logDto, logDocs.get(0).getLogDto());
	
	}

	@Test
	void testNormallExceptionDto() {
		LogDto logDto = new LogDto(new Date(), LogType.AUTHENTICATION_EXCEPTION, "artifact", 0, "");
		sendLog(logDto);
		List<LogDoc> logDocs = logsRepo.findAll();
		assertEquals(1, logDocs.size());
		assertEquals(logDto, logDocs.get(0).getLogDto());

	
	}
	@Test
	void takeLogDtoAndNoSaveNoDate() {
		LogDto logDto = new LogDto(null, LogType.NO_EXCEPTION, "artifact", 0, "");
		sendLog(logDto);
		testWrongLogDto();
	}

	private void testWrongLogDto() {
		List<LogDoc> logDocs = logsRepo.findAll();
		assertEquals(1, logDocs.size());
		LogDto logDto = logDocs.get(0).getLogDto();
		assertEquals(LogType.BAD_REQUEST_EXCEPTION, logDto.logType);
//		assertEquals(LogsAnalyzerService.class.getName(), logDto.artifact);
		assertEquals(0, logDto.responseTime);
		assertFalse(logDto.result.isEmpty());	
	
	}

	@Test
	void takeLogDtoAndNoSaveNoLogType() {
		LogDto logDto = new LogDto(new Date(), null, "artifact", 0, "");
		sendLog(logDto);
		testWrongLogDto();
	}

	@Test
	void takeLogDtoAndNoSaveNoArtifact() {
		LogDto logDto = new LogDto(new Date(), LogType.NO_EXCEPTION, "", 0, "");
		sendLog(logDto);
		testWrongLogDto();
	}

	private void sendLog(LogDto logDto) {
		input.send(new GenericMessage<LogDto>(logDto));
		
	}
}
