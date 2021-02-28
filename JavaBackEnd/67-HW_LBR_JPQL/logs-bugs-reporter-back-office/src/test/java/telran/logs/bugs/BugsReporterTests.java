package telran.logs.bugs;

import static telran.logs.bugs.api.BugsReporterApi.BUGS_ARTIFACT;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_ASSIGN;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_CLOSE;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_OPEN;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_OPEN_ASSIGN;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_PROGRAMMERS;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_PROGRAMMERS_COUNT;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_SERIOUSNESS_COUNT;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_SERIOUSNESS_MOST_COUNT;
import static telran.logs.bugs.api.BugsReporterApi.N_PROGRAMMERS;
import static telran.logs.bugs.api.BugsReporterApi.N_TYPE;
import static telran.logs.bugs.api.BugsReporterApi.PROGRAMMERS_LEAST_BUGS;
import static telran.logs.bugs.api.BugsReporterApi.PROGRAMMERS_MOST_BUGS;
import static telran.logs.bugs.api.BugsReporterApi.PROGRAMMER_ID;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import telran.logs.bugs.dto.ArtifactDto;
import telran.logs.bugs.dto.AssignBugData;
import telran.logs.bugs.dto.BugAssignDto;
import telran.logs.bugs.dto.BugDto;
import telran.logs.bugs.dto.BugResponseDto;
import telran.logs.bugs.dto.BugStatus;
import telran.logs.bugs.dto.CloseBugData;
import telran.logs.bugs.dto.OpenningMethod;
import telran.logs.bugs.dto.ProgrammerDto;
import telran.logs.bugs.dto.Seriousness;
import telran.logs.bugs.interfaces.BugsReporter;
import telran.logs.bugs.jpa.entities.Programmer;
import telran.logs.bugs.jpa.repo.BugRepository;

@SpringBootTest
@AutoConfigureWebTestClient
@AutoConfigureDataJpa
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BugsReporterTests {

	private static final @NotEmpty String DESCRIPTION = "Not working";
	private static final LocalDate DATE_OPEN = LocalDate.of(2020,12,1);
	private static final @Min(1) long PROGRAMMER_ID_VALUE = 123;
	private static final @Email String EMAIL ="moshe@gmail.com";
	private static final @Email String VASYA_EMAIL = "vasya@gmail.com";
	private static final  @Min(1) long VASYA_ID = 124;
	private static final int N_TYPES = 2;
	BugDto bugUnAssigned = new BugDto(Seriousness.BLOCKING, DESCRIPTION,
			DATE_OPEN);
	BugAssignDto bugAssigned2 = new BugAssignDto(Seriousness.BLOCKING, DESCRIPTION, DATE_OPEN, PROGRAMMER_ID_VALUE);
	BugAssignDto bugAssigned3 = new BugAssignDto(Seriousness.BLOCKING, DESCRIPTION, DATE_OPEN, PROGRAMMER_ID_VALUE);
	BugResponseDto expectedUnAssigned = new BugResponseDto(1, Seriousness.BLOCKING, DESCRIPTION,
			DATE_OPEN, 0, null, BugStatus.OPENNED, OpenningMethod.MANUAL);
	BugResponseDto expectedAssigned2 = new BugResponseDto(2, Seriousness.BLOCKING, DESCRIPTION,
			DATE_OPEN, PROGRAMMER_ID_VALUE, null, BugStatus.ASSIGNED, OpenningMethod.MANUAL);
	BugResponseDto expectedAssigned3 = new BugResponseDto(3, Seriousness.BLOCKING, DESCRIPTION,
			DATE_OPEN, PROGRAMMER_ID_VALUE, null, BugStatus.ASSIGNED, OpenningMethod.MANUAL);
	BugResponseDto expectedAssigned1 = new BugResponseDto(1, Seriousness.BLOCKING, DESCRIPTION + BugsReporter.ASSIGNMENT_DESCRIPTION_TITLE,
			DATE_OPEN, PROGRAMMER_ID_VALUE, null, BugStatus.ASSIGNED, OpenningMethod.MANUAL);
	List<BugResponseDto> expectedBugs123 = Arrays.asList(expectedAssigned1,
			expectedAssigned2, expectedAssigned3);
	
	List<EmailBugCountTest> expectedEmailCounts = Arrays.asList(new EmailBugCountTest(EMAIL, 3), new EmailBugCountTest(VASYA_EMAIL, 0));
	List<RatingProgrammersBugsTest> expectedProgrammersMostBugs = Arrays.asList(new RatingProgrammersBugsTest("Moshe"),
			new RatingProgrammersBugsTest("Vasya"));
	List<RatingProgrammersBugsTest> expectedProgrammersLeastBugs = Arrays.asList(new RatingProgrammersBugsTest("Vasya"),
			new RatingProgrammersBugsTest("Moshe"));
	List<SeriousnessBugCountTest> expectedSeriousness = Arrays.asList(new SeriousnessBugCountTest(Seriousness.BLOCKING, 3),
			new SeriousnessBugCountTest(Seriousness.COSMETIC, 1));
	List<SeriousnessBugMostCountTest> expectedSeriousnessMost = Arrays.asList(new SeriousnessBugMostCountTest("BLOCKING"),
			new SeriousnessBugMostCountTest("COSMETIC"));
	@Autowired
WebTestClient testClient;
	
	@Autowired
	BugRepository bugRepo;
	
	private <T> void testPostMethod(Class <T> clazz, List<T> expected,String requestPath) {
		testClient.post().uri(requestPath)
		.contentType(MediaType.APPLICATION_JSON).bodyValue(expected.get(0))
		.exchange().expectStatus().isOk().expectBody(clazz);
	}
	private <T> void testGetMethod(String requestPath,Class <T> clazz, List<T> expected) {
		testClient.get().uri(requestPath).exchange().expectStatus().isOk()
		.expectBodyList(clazz).isEqualTo(expected);
	}
	private <T> void testPutMethod(String requestPath, List<T> expected) {
		testClient.put().uri(requestPath).bodyValue(expected.get(0))
		.exchange().expectStatus().isOk();
	}
	private void invalidPostRequest(String uriStr, Object invalidObject) {
		testClient.post().uri(uriStr).contentType(MediaType.APPLICATION_JSON).bodyValue(invalidObject)
		.exchange().expectStatus().isBadRequest();
	}
	private void invalidPutRequest(String uriStr, Object invalidObject) {
		testClient.put().uri(uriStr).contentType(MediaType.APPLICATION_JSON).bodyValue(invalidObject)
		.exchange().expectStatus().isBadRequest();
	}
	private void openAssignRequest(BugAssignDto bugAssignDto, BugResponseDto bugResponseDto) {
		testClient.post().uri(BUGS_OPEN_ASSIGN).bodyValue(bugAssignDto).exchange().expectStatus()
		.isOk().expectBody(BugResponseDto.class).isEqualTo(bugResponseDto);
	}

	@Test
	@Order(1)
	void addProgrammers() {
		List<ProgrammerDto> programmer = Arrays.asList(new ProgrammerDto(PROGRAMMER_ID_VALUE,"Moshe", EMAIL));
		
		testPostMethod(ProgrammerDto.class,programmer, BUGS_PROGRAMMERS);
		programmer = Arrays.asList(new ProgrammerDto(VASYA_ID, "Vasya", VASYA_EMAIL));
		testPostMethod(ProgrammerDto.class,programmer, BUGS_PROGRAMMERS);
	}


	@Test
	@Order(2)
	void openBug() {
		testClient.post().uri(BUGS_OPEN)
		.contentType(MediaType.APPLICATION_JSON).bodyValue(bugUnAssigned).exchange().expectStatus().isOk()
		.expectBody(BugResponseDto.class).isEqualTo(expectedUnAssigned);
		
	}
	@Test
	@Order(3) 
	void openAndAssign() {
		
		openAssignRequest(bugAssigned2, expectedAssigned2);
		openAssignRequest(bugAssigned3, expectedAssigned3);
	}

	
	@Test
	@Order(4)
	void assign() {
		testPutMethod(BUGS_ASSIGN, Arrays.asList(new AssignBugData(1, PROGRAMMER_ID_VALUE, "")));
	}
	@Test
	@Order(5)
	void bugsProgrammers() {
		testGetMethod(BUGS_PROGRAMMERS + "?" + PROGRAMMER_ID + "=" + PROGRAMMER_ID_VALUE, BugResponseDto.class, expectedBugs123);
	}
	@Test
	@Order(6)
	void bugsProgrammersNoProgrammerID() {
		testGetMethod(BUGS_PROGRAMMERS + "?" + PROGRAMMER_ID + "=" + 10000, BugResponseDto.class, new LinkedList<>());
	}
	@Test
	@Order(7)
	void invalidOpenBug() {
		invalidPostRequest(BUGS_OPEN, new BugDto(Seriousness.BLOCKING, null, LocalDate.now()));
	}
	@Test
	@Order(8)
	void invalidAddProgrammer() {
		invalidPostRequest(BUGS_PROGRAMMERS, new Programmer(1, "Moshe", "kuku"));
	}
	@Test
	@Order(9)
	void invalidOpenAssignBug() {
		invalidPostRequest(BUGS_OPEN_ASSIGN, new BugAssignDto(Seriousness.BLOCKING,
				DESCRIPTION, DATE_OPEN, -20));
	}
	@Test
	@Order(10)
	void invalidAssignBug() {
		invalidPutRequest(BUGS_ASSIGN, new AssignBugData(0, PROGRAMMER_ID_VALUE, DESCRIPTION));
	}
	
	
	@Test 
	@Order(10)
	void emailCounts(){
		testGetMethod(BUGS_PROGRAMMERS_COUNT, EmailBugCountTest.class, expectedEmailCounts);
		
	}
	
	@Test 
	@Order(11)
	void programmersMostBugs(){
		BugAssignDto bugAssignedVasya = new BugAssignDto(Seriousness.COSMETIC, DESCRIPTION, DATE_OPEN, VASYA_ID);
		BugResponseDto expectedAssignedVasya = new BugResponseDto(4, Seriousness.COSMETIC, DESCRIPTION,
				DATE_OPEN, VASYA_ID, null, BugStatus.ASSIGNED, OpenningMethod.MANUAL);
		openAssignRequest(bugAssignedVasya, expectedAssignedVasya);
		testGetMethod(PROGRAMMERS_MOST_BUGS + "?" + N_PROGRAMMERS + "="+ VASYA_ID, RatingProgrammersBugsTest.class, expectedProgrammersMostBugs);
	}
	@Test 
	@Order(12)
	void programmersLeastBugs(){
		testGetMethod(PROGRAMMERS_LEAST_BUGS + "?" + N_PROGRAMMERS + "=" + VASYA_ID, RatingProgrammersBugsTest.class, expectedProgrammersLeastBugs);
	}

	@Test
	@Order(13)
	void addArtifact() {
		List<ArtifactDto> artifactDto = Arrays.asList(new ArtifactDto("New_artifact", PROGRAMMER_ID_VALUE));	
		testPostMethod(ArtifactDto.class, artifactDto, BUGS_ARTIFACT);
		
	}
	@Test
	@Order(14)
	void close() {
		LocalDate dateClose = LocalDate.now();
		String desc = "bug_closed";
		testPutMethod(BUGS_CLOSE, Arrays.asList(new CloseBugData(4, dateClose, desc)));
		List<BugResponseDto> expectedBugClosed = Arrays.asList(new BugResponseDto(4, Seriousness.COSMETIC, desc,
				DATE_OPEN, VASYA_ID, dateClose, BugStatus.CLOSED, OpenningMethod.MANUAL));
		testGetMethod(BUGS_PROGRAMMERS + "?" + PROGRAMMER_ID + "=" + VASYA_ID, BugResponseDto.class, expectedBugClosed);
		
	}	
	@Test 
	@Order(15)
	void seriousnessCounts(){
		testGetMethod(BUGS_SERIOUSNESS_COUNT, SeriousnessBugCountTest.class, expectedSeriousness);
		
	}
	@Test 
	@Order(16)
	void seriousnessMostCountsBugs(){
		testGetMethod(BUGS_SERIOUSNESS_MOST_COUNT + "?" + N_TYPE + "=" + N_TYPES, SeriousnessBugMostCountTest.class, expectedSeriousnessMost);
	}
}

