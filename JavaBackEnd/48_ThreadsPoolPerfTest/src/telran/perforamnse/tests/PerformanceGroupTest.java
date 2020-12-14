package telran.perforamnse.tests;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import telran.perforamnse.PerformanceTest;
import telran.util.OneOneGroupSum;

public class PerformanceGroupTest extends PerformanceTest {
	int nGroups;
	int nNumbersInGroup;
	int nThreads;
	private static final Random gen = new Random();
	FutureTask<Long> tasks[];

	public PerformanceGroupTest(int nThreads, int nGroups, int nNumbersInGroup, String testName, int nRuns) {
		super(testName, nRuns);
		this.nGroups = nGroups;
		this.nNumbersInGroup = nNumbersInGroup;
		this.nThreads = nThreads;
		this.tasks = getTasks(getRandomGroups(nGroups, nNumbersInGroup));
	}

	@Override
	protected void runTest() {
		startTasksExecutor(tasks, nThreads);
	}

	private int[][] getRandomGroups(int nGroups, int nNumbersInGroup) {

		return Stream.generate(() -> getGroup(nNumbersInGroup)).limit(nGroups).toArray(int[][]::new);
	}

	private int[] getGroup(int nNumbersInGroup) {
		return gen.ints(nNumbersInGroup, 1, Integer.MAX_VALUE - 1).toArray();
	}

	private void startTasksExecutor(FutureTask<Long>[] tasks, int nThreads) {
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		for (FutureTask<Long> task : tasks) {
			executor.execute(task);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// noop
		}

	}

	private FutureTask<Long>[] getTasks(int[][] groups) {
		@SuppressWarnings("unchecked")
		FutureTask<Long>[] result = new FutureTask[groups.length];
		for (int i = 0; i < groups.length; i++) {
			result[i] = new FutureTask<>(new OneOneGroupSum(groups[i]));
		}
		return result;
	}

}
