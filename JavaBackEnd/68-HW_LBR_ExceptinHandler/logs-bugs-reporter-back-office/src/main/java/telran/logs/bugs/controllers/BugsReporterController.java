package telran.logs.bugs.controllers;

import static telran.logs.bugs.api.BugsReporterApi.BUGS_ARTIFACTS;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_ASSIGN;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_CLOSE;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_OPEN;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_OPEN_ASSIGN;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_PROGRAMMERS;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_PROGRAMMERS_COUNT;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_PROGRAMMERS_LEAST;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_PROGRAMMERS_MOST;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_SERIOUSNESS_COUNT;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_SERIOUSNESS_MOST;
import static telran.logs.bugs.api.BugsReporterApi.BUGS_UNCLOSED;
import static telran.logs.bugs.api.BugsReporterApi.N_DAYS;
import static telran.logs.bugs.api.BugsReporterApi.N_PROGRAMMERS;
import static telran.logs.bugs.api.BugsReporterApi.N_TYPES;
import static telran.logs.bugs.api.BugsReporterApi.PROGRAMMER_ID;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Validated //make spring validate annotated parameters of GET requests 
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
@PostMapping(BUGS_ARTIFACTS)
ArtifactDto addArtifact(@Valid @RequestBody ArtifactDto artifactDto) {
	ArtifactDto res = bugsReporter.addArtifact(artifactDto);
	LOG.debug("addArtifact - saved artifact with id = {}", res.artifactId);
	return res ;
}
@PutMapping(BUGS_ASSIGN)
void assignBug(@Valid @RequestBody AssignBugData assignData) {
	bugsReporter.assignBug(assignData);
}
@PutMapping(BUGS_CLOSE) 
void closeBug(@Valid @RequestBody CloseBugData closeData) {
	bugsReporter.closeBug(closeData);
	LOG.debug("closeBug: bug {} closed", closeData.bugId);
}
@GetMapping(BUGS_PROGRAMMERS)
List<BugResponseDto> getBugsOfProgrammer(@RequestParam(name=PROGRAMMER_ID) @Min(1) long programmerId) {
	List<BugResponseDto> result = bugsReporter.getBugsProgrammer(programmerId);
	LOG.debug("found {} bugs", result.size());
	return result ;
}
@GetMapping(BUGS_PROGRAMMERS_COUNT)
List<EmailBugsCount> getEmailBugsCount() {
	List<EmailBugsCount> result = bugsReporter.getEmailBugsCounts();
	result.forEach(ec -> LOG.debug("email: {}; count: {}", ec.getEmail(),ec.getCount()));
	return result ;
}


@GetMapping(BUGS_UNCLOSED) 
List<BugResponseDto> getBugsUnclosed(@RequestParam(N_DAYS) @Min(1) int days) {
	
	List<BugResponseDto> res = bugsReporter.getUnClosedBugsMoreDuration(days);
	LOG.debug("getBugsUnclosed: {} unclosed bugs", res.size());
	return res ;
}
@GetMapping(BUGS_PROGRAMMERS_MOST)
List<String> getMostBugsProgrammers(@RequestParam(name=N_PROGRAMMERS, defaultValue = "2") @Min(1) int nProgrammers) {
	List<String> res = bugsReporter.getProgrammersMostBugs(nProgrammers);
	LOG.debug("getMostBugsProgrammers: list of programmers {}", res);
	return res ;
}
@GetMapping(BUGS_PROGRAMMERS_LEAST)
List<String> getLeastBugsProgrammers(@RequestParam(name = N_PROGRAMMERS, defaultValue = "2")@Min(1) int nProgrammers) {
	List<String> res = bugsReporter.getProgrammersLeastBugs(nProgrammers);
	LOG.debug("getMostBugsProgrammers: list of programmers {}", res);
	return res ;
}
@GetMapping(BUGS_SERIOUSNESS_COUNT)
List<SeriousnessBugCount> getSeriousnessBugsCounts() {
	List<SeriousnessBugCount> res = bugsReporter.getSeriousnessBugCounts();
	res.forEach(bc -> LOG.debug("seriousness: {}; count: {}", bc.getSeriousness(), bc.getCount())); 
	return res ;
}
@GetMapping(BUGS_SERIOUSNESS_MOST)
List<Seriousness> getSeriousnessBugsMost(@RequestParam (name = N_TYPES, defaultValue = "2")
@Min(1) int nTypes) {
	List<Seriousness> res = bugsReporter.getSeriousnessTypesWithMostBugs(nTypes);
	LOG.debug("List of seriousness types with most bugs {}; nTypes: {}", res, nTypes);
	return res;
}

}
