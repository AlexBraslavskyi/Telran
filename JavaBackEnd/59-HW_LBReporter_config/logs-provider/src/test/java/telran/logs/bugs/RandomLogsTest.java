package telran.logs.bugs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
public class RandomLogsTest {
	@Value("${app-authentication-artifact}")
	String AUTHENTICATION_ARTIFACT;
	@Value("${app-authorization-artifact}")
	String AUTHORIZATION_ARTIFACT;
	@Value("${app-class-artifact}")
	String CLASS_ARTIFACT;
	@Value("${app-n-logs}")
	long N_LOGS;
	@Value("${app-n-logs-sent}")
	int N_LOGS_SENT;
	@Autowired
	RandomLogs randomLogs;
	@Autowired
	 OutputDestination output;
	@Test
	void logTypeArtifactTest() throws Exception {
		
		EnumMap<LogType, String> logTypeArtifactsMap = getMapForTest();
		logTypeArtifactsMap.forEach((k, v) -> {
			switch (k) {
			case AUTHENTICATION_EXCEPTION:
				assertEquals(AUTHENTICATION_ARTIFACT, v);
				break;
			case AUTHORIZATION_EXCEPTION:
				assertEquals(AUTHORIZATION_ARTIFACT, v);
				break;
			default:
				testClassArtifact(v);
			
			}
		});
	}

	private EnumMap<LogType, String> getMapForTest()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method getMapMethod = randomLogs.getClass()
				.getDeclaredMethod("getLogArtifactMap");
		getMapMethod.setAccessible(true);
		@SuppressWarnings("unchecked")
		EnumMap<LogType, String> logTypeArtifactsMap =
				(EnumMap<LogType, String>) getMapMethod.invoke(randomLogs);
		return logTypeArtifactsMap;
	}
	@Test
	void generation() throws Exception{
		
		List<LogDto> logs =Stream
				.generate(() -> randomLogs.createRandomLog()).limit(N_LOGS)
				.collect(Collectors.toList());
		testLogContent(logs);
		Map<LogType, Long> logTypeOccurrences = 
				logs.stream().collect(Collectors.groupingBy(l -> l.logType, Collectors.counting()));
		logTypeOccurrences.forEach((k, v) -> {
			System.out.println();
		});
		
	}

	private void testLogContent(List<LogDto> logs) {
		logs.forEach(log -> {
			switch (log.logType) {
			case AUTHENTICATION_EXCEPTION:
				assertEquals(AUTHENTICATION_ARTIFACT, log.artifact);
				assertEquals(0, log.responseTime);
				assertTrue(log.result.isEmpty());
				break;
			case AUTHORIZATION_EXCEPTION:
				assertEquals(AUTHORIZATION_ARTIFACT, log.artifact);
				assertEquals(0, log.responseTime);
				assertTrue(log.result.isEmpty());
				break;
			
			case NO_EXCEPTION:
				testNoException(log);
				break;
			
			default:
				testNonSecException(log);
				break;
			
			}
		});
	}

	private void testNonSecException(LogDto log) {
		testClassArtifact(log.artifact);
		assertEquals(0, log.responseTime);
		assertTrue(log.result.isEmpty());
	}

	private void testClassArtifact(String artifact) {
		assertEquals(CLASS_ARTIFACT, artifact.substring(0, 5));
		int classNumber = Integer.parseInt(artifact.substring(5));
		assertTrue(classNumber >= 1 && classNumber <= randomLogs.nClasses);
	}

	private void testNoException(LogDto log) {
		testClassArtifact(log.artifact);
		assertTrue(log.responseTime > 0);
		assertTrue(log.result.isEmpty());
	}
	@Test
	void sendRandomLogs() throws InterruptedException, JsonMappingException, JsonProcessingException {
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < N_LOGS_SENT; i++) {
			Message<byte[]> receivedMessage = null;
			while (receivedMessage == null) {
				receivedMessage = output.receive();
			}
			byte[] messageBytes = receivedMessage.getPayload();
			String messageStr = new String(messageBytes);
			set.add(messageStr);
		}
		
		assertEquals(N_LOGS_SENT, set.size());
	}
}