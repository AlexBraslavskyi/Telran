package telran.logs.bugs;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;

@SpringBootTest (classes = {LogsInfoAppl.class})
@AutoConfigureWebTestClient
public class BackOfficeTest {
	@Autowired
	WebTestClient webClient;
	
	@Value("${app_get_all_logs}")
	String	getAllLogs;
	@Value("${app_get_all_exceptions}")
	String	getAllExceptions;
	@Value("${app_get_logs_by_type_no_exception}")
	String getLogsByTypeNoException;
	@Value("${app_get_logs_by_type_authorization_exception}")
	String getLogsByTypeAuthenticationException;
	

	
	@Test
	void getAllLogsTest() {
		assertEquals(100000, requestResponseMethod(getAllLogs).size());
	}
	
	@Test
	void getLogsAllExceptionsTest() {
		requestResponseMethod(getAllExceptions).forEach(log -> assertNotEquals(log.logType, LogType.NO_EXCEPTION));
	}
	
	@Test
	void getLogsByTypeNoExceptionTest() {
		requestResponseMethod(getLogsByTypeNoException).forEach(log -> assertEquals(log.logType, LogType.NO_EXCEPTION));
	}
	
	@Test
	void getLogsByTypeAuthorizationExceptionTest() {
		requestResponseMethod(getLogsByTypeAuthenticationException).forEach(log -> assertEquals(log.logType, LogType.AUTHORIZATION_EXCEPTION));
	}


	private List <LogDto> requestResponseMethod(String link) {
		return webClient.get().uri(link).exchange().expectStatus().isOk()
		.returnResult(LogDto.class).getResponseBody().collectList().block();
	}
}