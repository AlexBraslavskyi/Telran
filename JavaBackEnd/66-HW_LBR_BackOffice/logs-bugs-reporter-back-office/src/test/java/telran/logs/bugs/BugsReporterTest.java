package telran.logs.bugs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static telran.logs.bugs.api.LogsInfoApi.ADD_PROGRAMMER;
import static telran.logs.bugs.api.LogsInfoApi.GET_BUGS_PROGRAMMER;
import static telran.logs.bugs.api.LogsInfoApi.OPEN_AND_ASSIGN_BUG;
import static telran.logs.bugs.api.LogsInfoApi.OPEN_BUG;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import telran.logs.bugs.dto.AssignBugData;
import telran.logs.bugs.dto.BugAssignDto;
import telran.logs.bugs.dto.BugDto;
import telran.logs.bugs.dto.BugResponseDto;
import telran.logs.bugs.dto.BugStatus;
import telran.logs.bugs.dto.OpeningMethod;
import telran.logs.bugs.dto.ProgrammerDto;
import telran.logs.bugs.dto.Seriousness;
import telran.logs.bugs.jpa.entities.Programmer;
import telran.logs.bugs.jpa.repo.ArtifactRepository;
import telran.logs.bugs.jpa.repo.BugRepository;
import telran.logs.bugs.jpa.repo.ProgrammerRepository;
@SpringBootTest
@AutoConfigureWebTestClient
@AutoConfigureDataJpa
class BugsReporterTest<T> {

	private static final String POST = "post";
	private static final String PUT = "put";
	private static final String GET = "get";
@Autowired
	WebTestClient webClient;
@Autowired
	BugRepository bugsRepo;
@Autowired
	ProgrammerRepository programmerRepo;
@Autowired
ArtifactRepository artifactRepository;

//@BeforeEach
//void setup() {
//	bugsRepo.deleteAll();
//	programmerRepo.deleteAll();
//	artifactRepository.deleteAll();
//	Programmer programmer = new Programmer(1, "Moshe", "moshe@gmail.com");
//	programmerRepo.save(programmer);
//	BugResponseDto bugDto = new BugResponseDto(1,Seriousness.BLOCKING, "desc", LocalDate.now(), programmer.getId(), null,
//			BugStatus.OPENED, OpeningMethod.MANUAL);
//	BugResponseDto bugDto2 = new BugResponseDto(2,Seriousness.BLOCKING, "desc", LocalDate.now(), programmer.getId(), null,
//			BugStatus.OPENED, OpeningMethod.MANUAL);
//	BugResponseDto bugDto3 = new BugResponseDto(3,Seriousness.BLOCKING, "desc", LocalDate.now(), 3, null,
//			BugStatus.OPENED, OpeningMethod.MANUAL);
//	List<Bug> bugs = new ArrayList<>(Arrays.asList(new Bug(bugDto.description, bugDto.dateOpen,bugDto.dateClose, bugDto.status,bugDto.seriousness,
//			bugDto.openingMethod,programmer),
//			new Bug(bugDto3.description, bugDto3.dateOpen,bugDto3.dateClose, bugDto3.status,bugDto3.seriousness,
//					bugDto3.openingMethod,null)));
//
//	bugsRepo.saveAll(bugs);
//}


private <T> void runTest(String requestType, List<T> expected, String uriString, Class<T> clazz, boolean isValid) {
	
	switch (requestType) {
	case POST:
		if(isValid) {
			webClient.post().uri(uriString).contentType(MediaType.APPLICATION_JSON).bodyValue(expected.get(0)).exchange()
		.expectStatus().isOk().expectBody(clazz).isEqualTo(expected.get(0));}
		else{
			webClient.post().uri(uriString).contentType(MediaType.APPLICATION_JSON).bodyValue(expected.get(0)).exchange()
		.expectStatus().isBadRequest();
		}
		break;
	case PUT:
		if(isValid) {
			webClient.put().uri(uriString).contentType(MediaType.APPLICATION_JSON).bodyValue(expected.get(0)).exchange()
		.expectStatus().isOk();}
		else {
			webClient.put().uri(uriString).contentType(MediaType.APPLICATION_JSON).bodyValue(expected.get(0)).exchange()
		.expectStatus().isBadRequest();
		}
		break;
	case GET:
		if(isValid) {
			webClient.get().uri(uriString).exchange()
		.expectStatus().isOk().expectBodyList(clazz).isEqualTo(expected);}
		else{
			webClient.get().uri(uriString).exchange().expectStatus().isBadRequest();
		}
	
	}
	
}


@Test
@Sql("db.sql")
void testAddProgrammer() {
	List<ProgrammerDto> expected = new ArrayList<>(Arrays.asList(new ProgrammerDto(123L, "David", "david@gmail.com")));
	List<ProgrammerDto>  invalid =new ArrayList<>(Arrays.asList( new ProgrammerDto(1L, null, " ")));

	runTest(POST,expected, ADD_PROGRAMMER,ProgrammerDto.class, true);
	Programmer programmer = programmerRepo.findById(expected.get(0).id).orElse(null);
	assertEquals(new Programmer(expected.get(0).id,expected.get(0).name,expected.get(0).email), programmer);
	runTest(POST, invalid, ADD_PROGRAMMER,ProgrammerDto.class, false);
}




	@Test
	@Sql("db.sql")
	void testGetBugsByProgrammerId() {
		List<BugResponseDto> expected = new ArrayList<>();
		List<BugResponseDto> invalid = new ArrayList<>();
		expected.add(new BugResponseDto(1,Seriousness.MINOR, "desc", LocalDate.now(), 1,
				null, BugStatus.ASSIGNED, OpeningMethod.MANUAL));
		expected.add(new BugResponseDto(2,Seriousness.CRITICAL, "desc", LocalDate.now(), 1,
				null, BugStatus.ASSIGNED, OpeningMethod.MANUAL));

		runTest(GET, expected, GET_BUGS_PROGRAMMER +"/1",BugResponseDto.class, true);
		invalid.add(new BugResponseDto(2,Seriousness.CRITICAL, "desc",  null, 2,
				null, BugStatus.OPENED, OpeningMethod.AUTOMATIC));
		runTest(GET, invalid, GET_BUGS_PROGRAMMER+"/abc",BugResponseDto.class, false);
	}
	
	

	@Test
	@Sql("db.sql")
	void testOpenBug() {

		BugDto dto = new BugDto(Seriousness.MINOR, "desc", LocalDate.now());
		List<BugResponseDto> expected = new ArrayList<>(Arrays.asList( new BugResponseDto(8,dto.seriousness, dto.description, dto.dateOpen, 1, null,
				BugStatus.OPENED, OpeningMethod.MANUAL)));

		BugDto invalidDto = new BugDto(null, "123", null);
		
		List<BugResponseDto> invalid = new ArrayList<>(Arrays.asList( new BugResponseDto(4, invalidDto.seriousness, invalidDto.description, invalidDto.dateOpen, 1, null,
				BugStatus.OPENED, OpeningMethod.MANUAL)));
		
//		runTest(POST, expected, OPEN_BUG,BugResponseDto.class, true);
		runTest(POST, invalid, OPEN_BUG,BugResponseDto.class, false);
		
	}

	@Test
@Sql("db.sql")
	void testOpenAndAssignBug() {
		BugAssignDto dto = new BugAssignDto(Seriousness.BLOCKING, "Description", LocalDate.now(), 1);
		List<BugResponseDto> expected = new ArrayList<>(Arrays.asList( new BugResponseDto(8,dto.seriousness, dto.description, dto.dateOpen, 1, null,
				BugStatus.OPENED, OpeningMethod.MANUAL)));


		BugAssignDto invalidDto = new BugAssignDto(null, null, null, 0);
		List<BugResponseDto> invalid = new ArrayList<>(Arrays.asList( new BugResponseDto(4, invalidDto.seriousness, invalidDto.description, invalidDto.dateOpen, 1, null,
				BugStatus.OPENED, OpeningMethod.MANUAL)));
		
//		runTest(POST, expected, OPEN_AND_ASSIGN_BUG,BugResponseDto.class, true);
		runTest(POST, invalid, OPEN_AND_ASSIGN_BUG,BugResponseDto.class, false);

	}

	@Test
	@Sql("db.sql")
	void testAssignBug() {
		List<AssignBugData> expected = new ArrayList<>(Arrays.asList(new AssignBugData(3, 1, "assigned")));
//		runTest(PUT, expected, ASSIGN_BUG,AssignBugData.class, true);

	}
}