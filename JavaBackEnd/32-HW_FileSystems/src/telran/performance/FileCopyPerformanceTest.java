package telran.performance;

import java.io.File;

import telran.util.FileCopyInterface;

public class FileCopyPerformanceTest extends PerformanceTest{
private File source;
private File dest;
private int bufferSize;
private FileCopyInterface fileCopy;
	public FileCopyPerformanceTest(String testName, int nRuns, FileCopyInterface filecopy, File source, File dest) {
		super(testName, nRuns);
		this.fileCopy = filecopy;
		this.source = source;
		this.dest = dest;
		
		
	}

	@Override
	protected void runTest() {
		try {
			fileCopy.copyFile(source, dest);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
