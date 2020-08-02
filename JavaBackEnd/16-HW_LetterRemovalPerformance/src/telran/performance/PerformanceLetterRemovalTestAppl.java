package telran.performance;

import telran.util.LettersRemovalCharArray;
import telran.util.LettersRemovalReplaceAll;
import telran.util.LettersRemovalStringBuilder;

public class PerformanceLetterRemovalTestAppl {

	private static final int N_RUNS = 1000;
	private static final int LENGTH = 10000;

	public static void main(String[] args) {
		
		LettersRemovalPerformanceTest testReplaceAll = new LettersRemovalPerformanceTest
				(LENGTH, new LettersRemovalReplaceAll(), "ReplaceALL", N_RUNS);
		LettersRemovalPerformanceTest testCharArray = new LettersRemovalPerformanceTest
				(LENGTH, new LettersRemovalCharArray(), "CharArray", N_RUNS);
		LettersRemovalPerformanceTest testStringBuilder = new LettersRemovalPerformanceTest
				(LENGTH, new LettersRemovalStringBuilder(), "StringBuilder", N_RUNS);

		testReplaceAll.run();
		System.out.println();
		testCharArray.run();
		System.out.println();
		testStringBuilder.run();
	}

}
