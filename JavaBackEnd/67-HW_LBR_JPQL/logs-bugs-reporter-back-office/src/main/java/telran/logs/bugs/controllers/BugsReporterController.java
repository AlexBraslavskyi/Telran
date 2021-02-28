package telran.logs.bugs.controllers;

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

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.logs.bugs.dto.ArtifactDto;
import telran.logs.bugs.dto.AssignBugData;
import telran.logs.bugs.dto.BugAssignDto;
import telran.logs.bugs.dto.BugDto;
import telran.logs.bugs.dto.BugResponseDto;
import telran.logs.bugs.dto.CloseBugData;
import telran.logs.bugs.dto.EmailBugsCount;
import telran.logs.bugs.dto.ProgrammerDto;
import telran.logs.bugs.dto.Seriousness;
import telran.logs.bugs.dto.SeriousnessBugCount;
import telran.logs.bugs.interfaces.BugsReporter;

@RestController
public class BugsReporterController {
	static Logger LOG = LoggerFactory.getLogger(BugsReporterController.class);
@Autowired
BugsReporter bugsReporter;
@PostMapping(BUGS_OPEN)
BugResponseDto openBug(@Valid @RequestBody BugDto bugDto) {
	BugResponseDto res = bugsReporter.openBug(bugDto);
	LOG.debug("open bug - saved bug with id = {} , description: {}, status: {}", res.bugId, res.description, res.status);
	return res;
	
}
@PostMapping(BUGS_OPEN_ASSIGN)
BugResponseDto openAssignBug(@Valid @RequestBody BugAssignDto bugDto) {
	BugResponseDto res = bugsReporter.openAndAssignBug(bugDto);
	LOG.debug("open and assign bug - saved bug with id = {} , description: {}, status: {}", res.bugId, res.description, res.status);
	return res;
	
}
@PostMapping(BUGS_PROGRAMMERS)
ProgrammerDto addProgrammer(@Valid @RequestBody ProgrammerDto programmer) {
	ProgrammerDto res = bugsReporter.addProgrammer(programmer);
	LOG.debug("addProgrammer - saved programmer with id = {}", res.id);
	return res;
}
@PutMapping(BUGS_ASSIGN)
void assignBug(@Valid @RequestBody AssignBugData assignData) {
	bugsReporter.assignBug(assignData);
}
@GetMapping(BUGS_PROGRAMMERS)
List<BugResponseDto> getBugsOfProgrammer(@RequestParam(name=PROGRAMMER_ID) long programmerId) {
	List<BugResponseDto> result = bugsReporter.getBugsProgrammer(programmerId);
	LOG.debug("found {} bugs", result.size());
	return result ;
}

@GetMapping(BUGS_PROGRAMMERS_COUNT)
List<EmailBugsCount> getEmailBugsCount(){
	
	
	List<EmailBugsCount> result = bugsReporter.getEmailBugsCounts();
	result.forEach(ec->LOG.debug("email: {}; count: {}",ec.getEmail(), ec.getCount()));
	return result ;
	
}
@GetMapping(PROGRAMMERS_MOST_BUGS)
List<String> getProgrammersMostBugs(@RequestParam(name=N_PROGRAMMERS) int nProgrammers){
	
	
	List<String> result = bugsReporter.getProgrammersMostBugs(nProgrammers);
	result.forEach(ec->LOG.debug("programmer: {}:",ec));
	return result ;
	
} 

@GetMapping(PROGRAMMERS_LEAST_BUGS)
List<String> getProgrammersLeastBugs(@RequestParam(name=N_PROGRAMMERS) int nProgrammers){
	
	
	List<String> result = bugsReporter.getProgrammersLeastBugs(nProgrammers);
	result.forEach(ec->LOG.debug("programmer: {};",ec));
	return result ;
	
}
@PostMapping(BUGS_ARTIFACT)
ArtifactDto addArtifact(@Valid @RequestBody ArtifactDto artifactDto) {
	ArtifactDto res = bugsReporter.addArtifact(artifactDto);
	LOG.debug("addArtifact - saved artifact with id = {}", res.getArtifactId());
	return res;
}
@PutMapping(BUGS_CLOSE)
void closeBug(@Valid @RequestBody CloseBugData closeDate) {
	bugsReporter.closeBug(closeDate);
}

@GetMapping(BUGS_SERIOUSNESS_COUNT)
List<SeriousnessBugCount> getSeriousnessBugsCount(){
	List<SeriousnessBugCount> result = bugsReporter.getSeriousnessBugCounts();
	result.forEach(ec->LOG.debug("seriousness: {}; count: {}",ec.getSeriousness(), ec.getCount()));
	return result ;
	
}

@GetMapping(BUGS_SERIOUSNESS_MOST_COUNT)
List<Seriousness> getSeriousnessTypesWithMostBugs(@RequestParam(name=N_TYPE) int nType){
	List<Seriousness> result = bugsReporter.getSeriousnessTypesWithMostBugs(nType);
	result.forEach(ec->LOG.debug("seriousness: {}",ec));
	return result ;
	
}

}
