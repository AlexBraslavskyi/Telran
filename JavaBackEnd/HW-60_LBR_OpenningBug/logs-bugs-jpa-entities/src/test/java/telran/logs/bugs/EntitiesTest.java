package telran.logs.bugs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import telran.logs.bugs.jpa.entities.Artifact;
import telran.logs.bugs.jpa.entities.Bug;
import telran.logs.bugs.jpa.entities.BugStatus;
import telran.logs.bugs.jpa.entities.OpenningMethod;
import telran.logs.bugs.jpa.entities.Programmer;
import telran.logs.bugs.jpa.entities.Seriousness;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@ContextConfiguration(classes= {ArtifactsRepo.class, ProgrammersRepo.class, BugsRepo.class})
public class EntitiesTest {
@Autowired
ArtifactsRepo artifacts;
@Autowired
BugsRepo bugs;

@Autowired
ProgrammersRepo programmers;
Artifact artifact;
Programmer programmer;
Bug bug;
List<Bug> bugsRes;

private void createAndSave(long id, String programmerName, boolean isProgrammerArtifact, String bugDesc, 
		LocalDate dateOpen, BugStatus bugStatus, Seriousness seriousness, OpenningMethod openningMethod, boolean isProgrammerBug) {
	programmer = new Programmer(id, programmerName);
	programmers.save(programmer);
	artifact = new Artifact("authentication", isProgrammerArtifact?programmer:null);
	artifacts.save(artifact);
	bug = new Bug(bugDesc, dateOpen, null, bugStatus,
			seriousness, openningMethod, isProgrammerBug?programmer:null);
	bugs.save(bug);
	bugsRes = bugs.findAll();
}
@Test
void bugCreationNormallTest () {
	createAndSave(123L,"Moshe",true,"descr", LocalDate.now(),BugStatus.ASSIGNED, Seriousness.COSMETIC, OpenningMethod.AUTOMATIC,true);
	assertEquals(1, bugsRes.size());
	assertEquals(bug, bugsRes.get(0));
}
@Test
void programmerNameNull () {
	assertThrows(DataIntegrityViolationException.class,  () -> {createAndSave(123,null,true,"descr", LocalDate.now(),
			BugStatus.ASSIGNED, Seriousness.COSMETIC, OpenningMethod.AUTOMATIC,true);});
}
@Test
void artifactProgrammerNull () {
	assertThrows(DataIntegrityViolationException.class,  () -> {createAndSave(123,"Moshe",false,"descr", LocalDate.now(),
			BugStatus.ASSIGNED, Seriousness.COSMETIC, OpenningMethod.AUTOMATIC,true);});
}
@Test
void openningDataNull () {
	assertThrows(DataIntegrityViolationException.class,  () -> {createAndSave(123,"Moshe",true,"descr", null,
			BugStatus.ASSIGNED, Seriousness.COSMETIC, OpenningMethod.AUTOMATIC,true);});
}
@Test
void bugStatusNull () {
	assertThrows(DataIntegrityViolationException.class,  () -> {createAndSave(123,"Moshe",true,"descr", LocalDate.now(),
			null, Seriousness.COSMETIC, OpenningMethod.AUTOMATIC,true);});
}
@Test
void seriousnessStatusNull () {
	assertThrows(DataIntegrityViolationException.class,  () -> {createAndSave(123,"Moshe",true,"descr", LocalDate.now(),
			BugStatus.ASSIGNED, null, OpenningMethod.AUTOMATIC,true);});
}
@Test
void openningStatusNull () {
	assertThrows(DataIntegrityViolationException.class,  () -> {createAndSave(123,"Moshe",true,"descr", LocalDate.now(),
			BugStatus.ASSIGNED, Seriousness.COSMETIC, null,true);});
}
@Test
void bugProgrammerNull () {
	assertThrows(DataIntegrityViolationException.class,  () -> {createAndSave(123,"Moshe",true,"descr", LocalDate.now(),
			BugStatus.ASSIGNED, Seriousness.COSMETIC, OpenningMethod.AUTOMATIC,false);});
}
}
