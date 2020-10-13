package telran.performance;

public class PerformanceWriteFileTest extends PerformanceTest {
long nLines;
IWriteFile writeFile;
	

	public PerformanceWriteFileTest(String testName, int nRuns, long nLines, IWriteFile writeFile) {
	super(testName, nRuns);
	this.nLines = nLines;
	this.writeFile = writeFile;
}


	@Override
	protected void runTest() {
		writeFile.writeToFile(nLines);

	}

}
