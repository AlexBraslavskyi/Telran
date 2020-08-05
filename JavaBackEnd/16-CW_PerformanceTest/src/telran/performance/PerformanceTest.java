package telran.performance;

public abstract class PerformanceTest {
	
	private String testName;
	private int nRuns;
	
	public PerformanceTest(String testName, int nRuns) {
		super();
		this.testName = testName;
		this.nRuns = nRuns;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getnRuns() {
		return nRuns;
	}

	public void setnRuns(int nRuns) {
		this.nRuns = nRuns;
	}
	
	protected abstract void runTest();
	
	public void run() {
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < nRuns; i++) {
			runTest();
		}
		long finishTime = System.currentTimeMillis();
		displayResults(startTime, finishTime);
		
	}

	private void displayResults(long startTime, long finishTime) {
	System.out.printf("test: %s; number of runs: %d running time: %d ", 
			testName, nRuns, finishTime - startTime);
	System.out.println();
		
	}

}
