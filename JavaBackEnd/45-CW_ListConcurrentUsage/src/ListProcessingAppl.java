import java.util.ArrayList;
import java.util.List;

public class ListProcessingAppl {

	private static final int N_THREADS = 100;
	private static final int N_RUNS = 100;
	private static final int PROB_UPDATE = 80;
	private static final int N_NUMBERS = 1000;
static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws InterruptedException {
		ListProcessing threads[] = new ListProcessing[N_THREADS];
		fillList();
		startThreads(threads);
		weitThreads(threads);
		System.out.println("count of iterations in mode of locked list is " + ListProcessing.countLockIterations.get());

	}

	private static void weitThreads(ListProcessing[] threads) throws InterruptedException {
		for(ListProcessing thread:threads) {
			thread.join();
		}
		
	}

	private static void fillList() {
		for(int i =0; i< N_NUMBERS; i++) {
		list.add(i);
		}
	}

	private static void startThreads(ListProcessing[] threads) {
for(int i =0; i< threads.length; i++) {
			threads[i] = new ListProcessing(list, N_RUNS, PROB_UPDATE);
			threads[i].start();
			
		}
		
	}

}
