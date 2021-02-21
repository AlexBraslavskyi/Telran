package telran.logs.bugs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import telran.logs.bugs.dto.ArtifactClass;
import telran.logs.bugs.dto.ArtifactCount;
import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;
import telran.logs.bugs.dto.LogTypeCount;
import telran.logs.bugs.mongo.doc.LogDoc;
import telran.logs.bugs.repo.LogRepository;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogsInfoTest {
	@Autowired
	WebTestClient testClient;
	@Autowired
	LogRepository logRepository;
	static List<LogDto> exceptions;
	static List<LogDto> noExceptions;
	static List<LogDto> allLogs;
	static List<LogTypeCount> logTypeCount;
	static List<LogType> mostLogTypeEncount = new ArrayList<>();
	static List<ArtifactClass> mostArtifactEncount = new ArrayList<>();
	static List<ArtifactCount> artifactCount;
	static Date DATE_TIME = new Date();
	private static final String AUTHENTICATION_ERROR = "Authentication error";
	private static final String AUTHORIZATION_ERROR = "Authorization error";

	@BeforeAll
	static void setUpAll() {
		mostArtifactEncount.clear();
		mostLogTypeEncount.clear();
		exceptions = new ArrayList<>(Arrays.asList(
				new LogDto(DATE_TIME, LogType.AUTHENTICATION_EXCEPTION, "artifact1", 0, AUTHENTICATION_ERROR),
				new LogDto(DATE_TIME, LogType.AUTHORIZATION_EXCEPTION, "artifact2", 0, AUTHORIZATION_ERROR),
				new LogDto(DATE_TIME, LogType.BAD_REQUEST_EXCEPTION, "artifact3", 0, "")

		));
		noExceptions = new ArrayList<>(
				Arrays.asList(new LogDto(DATE_TIME, LogType.NO_EXCEPTION, "artifact1", 20, "result"),
						new LogDto(DATE_TIME, LogType.NO_EXCEPTION, "artifact1", 25, "result"),
						new LogDto(DATE_TIME, LogType.NO_EXCEPTION, "artifact2", 30, "result")));
		logTypeCount = new ArrayList<>(Arrays.asList(new LogTypeCount(LogType.NO_EXCEPTION, 3L),
				new LogTypeCount(LogType.BAD_REQUEST_EXCEPTION, 1L),
				new LogTypeCount(LogType.AUTHORIZATION_EXCEPTION, 1L),
				new LogTypeCount(LogType.AUTHENTICATION_EXCEPTION, 1L)));
		artifactCount = new ArrayList<>(Arrays.asList(new ArtifactCount("artifact1", 3L),
				new ArtifactCount("artifact2", 2L), new ArtifactCount("artifact3", 1L)));
		mostLogTypeEncount.add(LogType.NO_EXCEPTION);
		mostArtifactEncount.add(new ArtifactClass("artifact1"));
		mostArtifactEncount.add(new ArtifactClass("artifact2"));

		allLogs = new ArrayList<>(noExceptions);
		allLogs.addAll(exceptions);

	}

	@Test
	@Order(1)
	void allLogs() {
		setUpDbInitial();
		commonTest("/logs", LogDto.class, allLogs);
	}

	@Test
	void allNoException() {
		commonTest("/logs/type?type=NO_EXCEPTION", LogDto.class, noExceptions);
	}

	@Test
	void badRequest() {
		testClient.get().uri("/logs/type?type=EXCEPTION").exchange().expectStatus().isBadRequest();
	}

	@Test
	void allException() {
		commonTest("/logs/exceptions", LogDto.class, exceptions);
	}

	@Test
	void testTypeCount() {
		commonTest("/logs/distribution/type", LogTypeCount.class, logTypeCount);

	}

	@Test
	void testArtifactCount() {
		commonTest("/logs/distribution/artifact", ArtifactCount.class, artifactCount);

	}

	@Test
	void testMostEccountredType() {
		commonTest("/logs/types/encountered?amount=1", LogType.class, mostLogTypeEncount);

	}

	@Test
	void testMostEccountredArtifact() {
		commonTest("/logs/artifacts/encountered?amount=2", ArtifactClass.class, mostArtifactEncount);

	}

	<T> void commonTest(String path, Class<T> clazz, List<T> list) {
		testClient.get().uri(path).exchange().expectStatus().isOk().expectBodyList(clazz).isEqualTo(list);
	}

	private void setUpDbInitial() {
		Flux<LogDoc> savingFlux = logRepository.saveAll(allLogs.stream().map(LogDoc::new).collect(Collectors.toList()));
		savingFlux.buffer().blockFirst();

	}

}
