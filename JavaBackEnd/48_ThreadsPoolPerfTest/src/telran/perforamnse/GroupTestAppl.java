package telran.perforamnse;

import telran.perforamnse.tests.PerformanceGroupTest;

public class GroupTestAppl {
	private static final int N_RUNS = 10;
	private static final int N_GROUPS = 10000;
	private static final int N_NUMBERS_IN_GROUP = 100;
	private static final String COUNT_OF = "Count of Threads ";

	public static void main(String[] args) {
		PerformanceGroupTest[] groupTests = {
				new PerformanceGroupTest(1, N_GROUPS, N_NUMBERS_IN_GROUP, COUNT_OF + "1", N_RUNS),
				new PerformanceGroupTest(5, N_GROUPS, N_NUMBERS_IN_GROUP, COUNT_OF + "5", N_RUNS),
				new PerformanceGroupTest(20, N_GROUPS, N_NUMBERS_IN_GROUP, COUNT_OF + "20", N_RUNS),
				new PerformanceGroupTest(100, N_GROUPS, N_NUMBERS_IN_GROUP, COUNT_OF + "100", N_RUNS),
				new PerformanceGroupTest(300, N_GROUPS, N_NUMBERS_IN_GROUP, COUNT_OF + "300", N_RUNS),
				new PerformanceGroupTest(1000, N_GROUPS, N_NUMBERS_IN_GROUP, COUNT_OF + "1000", N_RUNS),
				new PerformanceGroupTest(N_NUMBERS_IN_GROUP, N_GROUPS, N_NUMBERS_IN_GROUP,
						COUNT_OF + N_NUMBERS_IN_GROUP, N_RUNS), };
		for (PerformanceGroupTest test : groupTests) {
			test.run();
		}
	}
}
