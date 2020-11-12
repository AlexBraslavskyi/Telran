import java.util.concurrent.CopyOnWriteArrayList;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class ThreadsRaceAppl {
	private static final int MAX_TIMEOUT = 5;
	private static final int MIN_TIMEOUT = 2;

	static int firstFinishedThread;
	static long[] finTime;

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();

		Menu menu = new Menu("Threads Championship", Item.of("Start game", ThreadsRaceAppl::runGame), Item.exit());
		menu.perform(io);
	}

	static void runGame(InputOutput ioParam) {

		Integer numThreads = ioParam.readInteger("Enter number of threads 3-10", 3, 10);
		Integer distance = ioParam.readInteger("Enter distance in range 100-3500", 100, 3500);
		Integer randomTimeout = (int) ((Math.random() * ((MAX_TIMEOUT - MIN_TIMEOUT) + 1)) + MIN_TIMEOUT);
		CopyOnWriteArrayList<ThreadsRace> threads = new CopyOnWriteArrayList<>();
		finTime = new long[numThreads];

		for (int i = 0; i < numThreads; i++) {
			threads.add(new ThreadsRace(i + 1, distance, randomTimeout));
			threads.get(i).start();

		}

//		while (true) {
//
//			int i = 0;
//			for (; i < threads.size(); i++) {
//
//				if (!threads.get(i).isAlive()) {
//					for (int j = 0; j < threads.size(); j++) {
//						try {
//							threads.get(j).join();
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//					ioParam.writeLn(
//							"Thread " + threads.get(i).number + " finished first" + threads.get(i).finishedTime);
//					return;
//				}
//
//			}
//		}
//	}
		// Variant finish time
		int i = 0;
		int count = 0;
		while (count==numThreads) {
			if (threads.get(i).finishedTime > 0) {
				try {
					threads.get(i).join();
					finTime[threads.get(i).number - 1] = threads.get(i).finishedTime;
					threads.remove(i);
					count++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			i++;
				if (i-1 == numThreads-1) {
					i = 0;
				}
			}

		
			long min = finTime[0];
			for (int j = 1; j < finTime.length; j++) {
				if (finTime[j] < min) {
					min = finTime[j];
					firstFinishedThread = j + 1;

				}
			}
			ioParam.writeLn("Thread " + firstFinishedThread + " finished first " + finTime[firstFinishedThread]);
			return;
		}
	}

