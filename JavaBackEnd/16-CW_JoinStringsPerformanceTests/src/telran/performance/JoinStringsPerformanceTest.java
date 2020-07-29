package telran.performance;

import telran.util.JoinStringsInterface;

public class JoinStringsPerformanceTest extends PerformanceTest {

	private String strings[];
	private JoinStringsInterface joinStrings;
	
	public JoinStringsPerformanceTest(int nStrings, JoinStringsInterface joinStrings,
			String testName, int nRuns) {
		
		super(testName, nRuns);
		strings = new String[nStrings];
		for(int i = 0; i < nStrings; i++) {
			strings[i] = "string";
			
		}
		this.joinStrings = joinStrings;
	}

	@Override
	protected void runTest() {
		joinStrings.join(strings, "<br>");

	}

}
