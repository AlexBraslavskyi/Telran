package telran.logs.bugs.interfaces;

import java.util.List;

import telran.logs.bugs.dto.ArtifactDto;
import telran.logs.bugs.dto.AssignBugData;
import telran.logs.bugs.dto.BugAssignDto;
import telran.logs.bugs.dto.BugDto;
import telran.logs.bugs.dto.BugResponseDto;
import telran.logs.bugs.dto.CloseBugDate;
import telran.logs.bugs.dto.EmailBugsCount;
import telran.logs.bugs.dto.ProgrammerDto;

public interface BugsReporter {
	ProgrammerDto addProgrammer(ProgrammerDto programmerDto);
	ArtifactDto addArtifact(ArtifactDto artifactDto);
	BugResponseDto openBug(BugDto bugDto);
	BugResponseDto openAndAssignBug(BugAssignDto bugDto);
	void assingBug(AssignBugData assignDate);
	List<BugResponseDto> getNonAssignBugs();
	void closeBug(CloseBugDate closeDate);
	List<BugResponseDto> getUnClosedBugsMoreDuration(int days);
	List<BugResponseDto> getBugsProgrammer(long programmerId);
	List<EmailBugsCount> getEmailBugsCount();
	List<String> getProgrammerMostBugs(int nProgrammers);
	List<String> getProgrammersLeastBugs(int nProgrammers);
	
}
