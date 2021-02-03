package telran.logs.bugs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.jdbc.Sql;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;
import telran.logs.bugs.jpa.entities.Bug;
import telran.logs.bugs.jpa.entities.BugStatus;
import telran.logs.bugs.jpa.entities.OpenningMethod;
import telran.logs.bugs.jpa.entities.Seriousness;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
@AutoConfigureTestDatabase

class OpenningBugsTest {

	private static final String NO_PROGRAMMER = "NO PROGRAMMER";

	@Autowired
	BugsRepo bugsRepo;

	@Autowired
	InputDestination producer;

	@Autowired
	OutputDestination consumer;

	@Value("${app-binding-name:exceptions-out-0}")
	String bindingName;


	@BeforeEach
	void setup() {
		bugsRepo.deleteAll();
		consumer.clear();
	}

	
	@Test
	@Sql("fillDB.sql")
	void testBugNormall() {
		LogDto logDto = new LogDto(new Date(), LogType.NO_EXCEPTION, "LogDto.class", 0, "res");
		producer.send(new GenericMessage<LogDto>(logDto));
		assertEquals(0, bugsRepo.count());
	}
	
	@Test
	@Sql("fillDB.sql")
	void testBugDataNull() {
		LogDto logDto = new LogDto(null, LogType.NO_EXCEPTION, "class", 0, "res");
		testBug(logDto, BugStatus.ASSIGNED, Seriousness.MINOR, "NO_EXCEPTION res");
	}
	@Test
	@Sql("fillDB.sql")
	void testBugLogTypeAndDescr() {
		LogDto logDto = new LogDto(null, LogType.AUTHENTICATION_EXCEPTION, "class", 0, "res");
		testBug(logDto, BugStatus.ASSIGNED, Seriousness.BLOCKING, "AUTHENTICATION_EXCEPTION res");
	}


	void testBug(LogDto logDto, BugStatus bugStatus, Seriousness seriousness, String desc) {
		producer.send(new GenericMessage<LogDto>(logDto));
		assertEquals(1, bugsRepo.count());
		Bug bug = bugsRepo.findAll().get(0);
		assertNotNull(bug.getDateOpen());
		assertEquals(OpenningMethod.AUTOMATIC, bug.getOpenningMethod());
		assertNull(bug.getDateClose());
		assertEquals(bugStatus, bug.getStatus());
		assertEquals(seriousness, bug.getSeriousness());
		assertEquals(desc, bug.getDescription());
		
	}

	
}
