package telran.performance.tests;

import telran.performance.JoinStringsPerformanceTest;
import telran.util.JoinStringsMutable;
import telran.util.JoinStringsImmutable;

public class JoinStringPerformanceApp {

	private static final int N_STRINGS = 1000;
	private static final int N_RUNS = 1000;

	public static void main(String[] args) {
		JoinStringsPerformanceTest testMutable = new JoinStringsPerformanceTest(N_STRINGS, new JoinStringsMutable(), 
				"Join Strings Mutable", N_RUNS);
		JoinStringsPerformanceTest testImmutable = new JoinStringsPerformanceTest(N_STRINGS, new JoinStringsImmutable(), 
				"Join Strings Mutable", N_RUNS);
		testMutable.run();
		testImmutable.run();
	}

}
