import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.Stream;

public class SumGroupsAppl {

	private static final int N_GROUPS = 1000;
	private static final int N_NUMBERS_IN_GROUP = 10000;
	private static Random gen = new Random();
	public static void main(String[] args) {
		
		int [][] groups = getRandomGroups(N_GROUPS, N_NUMBERS_IN_GROUP);
		long sum = Arrays.stream(groups).parallel().flatMapToInt(Arrays::stream).asLongStream().sum();
		System.out.println(sum);
		
		FutureTask<Long> tasks [] = getTasks(groups);
		startTasks(tasks);
		sum = calculateSum(tasks);
		System.out.println(sum);
		startTasksExecutor(tasks);
		sum = calculateSum(tasks);
		System.out.println(sum);
		
	}
private static void startTasksExecutor(FutureTask<Long>[] tasks) {
	
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for(FutureTask<Long> task:tasks) {
			executor.execute(task);
		}
		executor.shutdown();
	}
private static FutureTask<Long>[] getTasks(int[][] groups) {
	FutureTask<Long> result[] = new FutureTask[groups.length];
		for(int i = 0; i < groups.length; i++) {
			result[i] = new FutureTask<Long>(new OneGroupSum(groups[i]));
		}
		return result;
	}
private static void startTasks(FutureTask<Long>[] tasks) {
		for(FutureTask<Long> task: tasks) {
			new Thread(task).start();
		}
		
	}
private static long calculateSum(FutureTask<Long>[] tasks) {
	
		return Arrays.stream(tasks).mapToLong(t->{
			try {
			return t.get();
			}catch(InterruptedException|ExecutionException e) {
				System.out.println("error:" + e.getMessage());
				return 0;
				}
		}).sum();
	}


	private static int[][] getRandomGroups(int nGroups, int nNumbersInGroup) {
		
		return Stream.generate(()->getGroup(nNumbersInGroup)).limit(nGroups).toArray(int [][]::new);
	}

	private static int[]  getGroup(int nNumbersInGroup) {
		
		return gen.ints(nNumbersInGroup, 1, Integer.MAX_VALUE).toArray();
	}

}
