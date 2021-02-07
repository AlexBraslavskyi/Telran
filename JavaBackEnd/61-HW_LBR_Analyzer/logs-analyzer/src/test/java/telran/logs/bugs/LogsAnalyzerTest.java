package telran.logs.bugs;

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
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
public class LogsAnalyzerTest {
	static Logger LOG = LoggerFactory.getLogger(LogsAnalyzerTest.class);
	@Autowired
InputDestination producer;
	@Autowired
OutputDestination consumer;
	@Value("${app-binding-name-exception:exceptions-out-0}")
	String exception;

	@Value("${app-binding-name-error:error-out-0}")
	String error;
	@BeforeEach
	void setup() {
		consumer.clear(error);
	}
	@Test
	void analyzerTestNonException() {
		LogDto logDto = new LogDto(new Date(), LogType.NO_EXCEPTION, "artifact", 0, "result");
		producer.send(new GenericMessage<LogDto>(logDto));
		Message<byte[]> message = consumer.receive(0, exception);
		assertNull(message);
		message = consumer.receive(0, error);
		assertNull(message);
	}
	@Test
	void analyzerTestException() {
		LogDto logDto = new LogDto(new Date(), LogType.AUTHENTICATION_EXCEPTION, "artifact", 0, "result");
		producer.send(new GenericMessage<LogDto>(logDto));
		Message<byte[]> message = consumer.receive(0, exception);
		assertNotNull(message);
		LOG.debug("receved in consumer {}", new String(message.getPayload()));
		
	}
	@Test
	void analyzerTestErrorDate() {
		LogDto logDto = new LogDto(null, LogType.AUTHENTICATION_EXCEPTION, "artifact", 0, "result");
		producer.send(new GenericMessage<LogDto>(logDto));
		Message<byte[]> message = consumer.receive(0, error);
		assertNotNull(message);
		LOG.debug("receved in consumer {}", new String(message.getPayload()));
		
	}
	@Test
	void analyzerTestErrorArtifact() {
		LogDto logDto = new LogDto(new Date(), LogType.AUTHENTICATION_EXCEPTION, null, 0, "result");
		producer.send(new GenericMessage<LogDto>(logDto));
		Message<byte[]> message = consumer.receive(0, error);
		assertNotNull(message);
		LOG.debug("receved in consumer {}", new String(message.getPayload()));
		
	}
}
