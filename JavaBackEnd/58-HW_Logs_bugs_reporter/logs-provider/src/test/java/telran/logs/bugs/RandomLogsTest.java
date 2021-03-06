package telran.logs.bugs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
public class RandomLogsTest {
	private static final String AUTHENTICATION_ARTIFACT = "authentication";
	private static final String AUTHORIZATION_ARTIFACT = "authorization";
	private static final String CLASS_ARTIFACT = "class";
	private static final long N_LOGS = 100000;
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
				assertEquals(CLASS_ARTIFACT, v);
			
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
				assertEquals(CLASS_ARTIFACT, log.artifact);
				assertTrue(log.responseTime > 0);
				assertTrue(log.result.isEmpty());
				break;
			
			default:
				assertEquals(CLASS_ARTIFACT, log.artifact);
				assertEquals(0, log.responseTime);
				assertTrue(log.result.isEmpty());
				break;
			
			}
		});
	}
	@Test
	void sendRandomLogs() throws InterruptedException {
		Set<String> data = new HashSet<>();
		int countOfMessages = 10;
		for (int i = 0; i < countOfMessages; i++) {
		    byte[] messageBytes = output.receive(1500).getPayload();
		    String messageString = new String(messageBytes);
		    data.add(messageString);
		}
		assertEquals(countOfMessages, data.size());
	}

}
