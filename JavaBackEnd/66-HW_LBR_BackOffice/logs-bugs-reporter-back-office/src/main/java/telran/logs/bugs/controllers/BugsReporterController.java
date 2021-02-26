package telran.logs.bugs.controllers;

import static telran.logs.bugs.api.LogsInfoApi.ADD_PROGRAMMER;
import static telran.logs.bugs.api.LogsInfoApi.ASSIGN_BUG;
import static telran.logs.bugs.api.LogsInfoApi.GET_BUGS_PROGRAMMER;
import static telran.logs.bugs.api.LogsInfoApi.OPEN_AND_ASSIGN_BUG;
import static telran.logs.bugs.api.LogsInfoApi.OPEN_BUG;
import static telran.logs.bugs.api.LogsInfoApi.PROGRAMMER_ID;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.logs.bugs.dto.AssignBugData;
import telran.logs.bugs.dto.BugAssignDto;
import telran.logs.bugs.dto.BugDto;
import telran.logs.bugs.dto.BugResponseDto;
import telran.logs.bugs.dto.ProgrammerDto;
import telran.logs.bugs.interfaces.BugsReporter;

@RestController

public class BugsReporterController {
	static Logger LOG = LoggerFactory.getLogger(BugsReporterController.class);
	@Autowired
	BugsReporter bugsReporter;
	
	
	@PostMapping(value=ADD_PROGRAMMER)
ProgrammerDto addProgrammer(@RequestBody @Valid ProgrammerDto programmerDto) {
		ProgrammerDto result = bugsReporter.addProgrammer(programmerDto);
		LOG.debug("Programmer {} added", programmerDto);
	return result;
}
	@PostMapping(value=OPEN_BUG)
	BugResponseDto openBug(@RequestBody @Valid BugDto bugDto) {
		BugResponseDto result = bugsReporter.openBug(bugDto);
		LOG.debug("Bug {} opened", bugDto);
	return result;
}
	
	@PostMapping(value=OPEN_AND_ASSIGN_BUG)
	BugResponseDto openAndAssignBug(@RequestBody @Valid BugAssignDto bugDto) {
		BugResponseDto result = bugsReporter.openBug(bugDto);
		LOG.debug("Bug {} opened and assigned", bugDto);
	return result;
}
	
	@PutMapping(value=ASSIGN_BUG)
	void assignBug(@RequestBody @Valid AssignBugData assignDate) {
		bugsReporter.assingBug(assignDate);
		LOG.debug("Bug {} assigned", assignDate.bugId);
}
	
	@GetMapping(value=GET_BUGS_PROGRAMMER+PROGRAMMER_ID)
	List<BugResponseDto> getBugsProgramme(@PathVariable(name="id") long programmerId) {
		List<BugResponseDto>  result = bugsReporter.getBugsProgrammer(programmerId);
		LOG.debug("Bugs by programmer {} recived", programmerId);
	return result;
}
	
}
