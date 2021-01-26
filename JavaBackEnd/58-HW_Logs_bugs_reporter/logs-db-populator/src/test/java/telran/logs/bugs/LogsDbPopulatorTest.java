package telran.logs.bugs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;
import telran.logs.bugs.mongo.doc.LogDoc;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
public class LogsDbPopulatorTest {
	private static final int COUNT_LOGS = 10;
	@Autowired
InputDestination input;
	@Autowired
	TestLogsRepo testLogsRepo;

	List<LogType> logTypes = Arrays.asList(LogType.values());
	@Test
	void takeLogDto() {
		testLogsRepo.deleteAll();
		List<LogDto> logsArrNormall = new ArrayList<>();
		List<LogDto> logsArrError = new ArrayList<>();
//		add valid random logs
		for(int i = 0; i < logTypes.size(); i++) {
		logsArrNormall.add(new LogDto(new Date(),logTypes.get(randomNumber()), "Class"+randomNumber(), 
				randomNumber(), "result"+randomNumber()));
		}
//		add notValid logs
		for(int i = 0; i < logTypes.size(); i++) {
		logsArrError.add(new LogDto(null,logTypes.get(randomNumber()), "Class"+randomNumber(), 
				randomNumber(), "result"+randomNumber()));
		logsArrError.add(new LogDto(new Date(),null, "Class"+randomNumber(), 
				randomNumber(), "result"+randomNumber()));
		logsArrError.add(new LogDto(new Date(),logTypes.get(randomNumber()), "", 
				randomNumber(), "result"+randomNumber()));
		}
//		send logs
		for(int i = 0; i < COUNT_LOGS;i++) {
			input.send(new GenericMessage<LogDto>
			(logsArrNormall.get(randomNumber())));
			input.send(new GenericMessage<LogDto>
			(logsArrError.get(randomNumber())));
		}
		
//		checking contains logs in repo
		List<LogDoc> listLogs = testLogsRepo.findAll();
		for(int i = 0; i<listLogs.size();i++) {
			assertTrue(logsArrNormall.contains(listLogs.get(i).getLogDto()));
			assertFalse(logsArrError.contains(listLogs.get(i).getLogDto()));
		}
		
	}

	private int randomNumber() {
		return ThreadLocalRandom.current().nextInt(0, logTypes.size());
	}
}
