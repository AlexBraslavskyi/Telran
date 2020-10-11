package telran.performance;

import java.io.File;

import telran.util.FileCopy;
import telran.util.FileCopyUsingChannel;
import telran.util.FileCopyUsingStream;

public class FileCopyPerformanceAppl {

	private static final int N_RUNS = 1;
	private static File SOURCE = new File("/Users/abraslik/Downloads/test.mkv");//4.8 Gb
	private static File DEST1 = new File("/Users/abraslik/Downloads/dest1.mkv");
	private static File DEST2 = new File("/Users/abraslik/Downloads/dest2.mkv");
	private static File DEST3 = new File("/Users/abraslik/Downloads/dest3.mkv");

	public static void main(String[] args) {

		FileCopyPerformanceTest testStream = new FileCopyPerformanceTest("Test Stream Copy", N_RUNS,
				new FileCopyUsingStream(), SOURCE, DEST1);
		FileCopyPerformanceTest testCopy = new FileCopyPerformanceTest("Test Copy", N_RUNS, new FileCopy(), SOURCE,
				DEST2);
		FileCopyPerformanceTest testChennel = new FileCopyPerformanceTest("Test Channel Copy", N_RUNS,
				new FileCopyUsingChannel(), SOURCE, DEST3);

		System.out.println("File size : "+ SOURCE.length() + " bytes");
		testStream.run();
		testChennel.run();
		testCopy.run();

	}
}
