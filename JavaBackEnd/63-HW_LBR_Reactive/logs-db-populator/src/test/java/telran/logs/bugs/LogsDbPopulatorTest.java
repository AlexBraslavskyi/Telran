package telran.logs.bugs;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;
import telran.logs.bugs.mongo.doc.LogDoc;
import telran.logs.bugs.mongo.repo.LogsRepo;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
public class LogsDbPopulatorTest {
	static Logger LOG = LoggerFactory.getLogger(LogsDbPopulatorTest.class);
	@Value("${app-binding-name:exceptions-out-0}")
	String bindingName;
	@Autowired
	InputDestination input;
	
	@Autowired
	LogsRepo logsRepo;

	@BeforeEach
	void setUp() {
		logsRepo.deleteAll().subscribe();
		
		
	}

	@Test
	void takeLogDtoAndSave() throws InterruptedException {
		LogDto logDto = new LogDto(new Date(), LogType.NO_EXCEPTION, "artifact", 0, "");
		
		Thread.sleep(10000);
		sendLog(logDto);
		List<LogDoc> logDocs = logsRepo.findAll().collectList().block();
		assertEquals(1, logDocs.size());
		assertEquals(logDto, logDocs.get(0).getLogDto());
	}

	
	private void sendLog(LogDto logDto) {
		
		input.send(new GenericMessage<LogDto>(logDto));
		
	}
}
