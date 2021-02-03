package telran.logs.bugs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

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
//import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;
import telran.logs.bugs.mongo.repo.LogsRepo;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
public class LogsDbPopulatorTest {
	@Autowired
InputDestination producer;
	@Autowired
	LogsRepo logsRepo;
	static Logger LOG; 
	@Autowired
OutputDestination consumer;
	@Value("${app-binding-name:exceptions-out-0}")
	String bindingName;
	Message<byte[]> message;
	
	@BeforeEach
	void setUp() {
		consumer.clear(bindingName);
		logsRepo.deleteAll();
		LOG = LoggerFactory.getLogger(LogsDbPopulatorTest.class);
	}
	
	@Test
	void takeLogDtoAndSaveNormal() {
		LogDto logDto = new LogDto(new Date(), LogType.NO_EXCEPTION, "artifact", 0, "");
		producer.send(new GenericMessage<LogDto>(logDto));
		message = consumer.receive(0, bindingName);
		assertNull(message);
		assertEquals(logDto, logsRepo.findAll().get(0).getLogDto());
	}
	@Test
	void takeLogDtoWithExcType() {
		LogDto logDto = new LogDto(null, LogType.AUTHENTICATION_EXCEPTION, "artifact", 0, "");
		sendAndCheckLog(logDto);
		
	}
	@Test
	void takeLogDtoAndNoSaveNoDate() {
		LogDto logDto = new LogDto(null, LogType.NO_EXCEPTION, "artifact", 0, "");
		sendAndCheckLog(logDto);
		
	}
	@Test
	void takeLogDtoAndNoSaveNoLogType() {
		LogDto logDto = new LogDto(new Date(), null, "artifact", 0, "");
		sendAndCheckLog(logDto);

	}
	@Test
	void takeLogDtoAndNoSaveNoArtifact() {
		LogDto logDto = new LogDto(new Date(), LogType.NO_EXCEPTION, "", 0, "");
		sendAndCheckLog(logDto);
	}
	
	private void sendAndCheckLog(LogDto logDto) {
		producer.send(new GenericMessage<LogDto>(logDto));
		message = consumer.receive(0, bindingName);
		assertNotNull(message);
		LOG.debug("receved in consumer {}", new String(message.getPayload()));
	}
	
}
