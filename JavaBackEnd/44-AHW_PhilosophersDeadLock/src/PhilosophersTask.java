public class PhilosophersTask {
	private static final int PHILOSOPHERS_COUNT = 5;

	public static void main(String[] args) throws Exception {

		Philosopher[] philosophers = new Philosopher[PHILOSOPHERS_COUNT];
		Object[] forks = new Object[philosophers.length];

		for (int i = 0; i < forks.length; i++) {
			forks[i] = new Object();
		}

		for (int i = 0; i < philosophers.length; i++) {
			Object leftFork = forks[i];
			Object rightFork = forks[(i + 1) % forks.length];
			
//			philosophers[i] = new Philosopher(leftFork, rightFork);
			/////////////////
			if(i == philosophers.length-1){
                philosophers[i] = new Philosopher(leftFork,rightFork);
            }else{
                philosophers[i] = new Philosopher(rightFork,leftFork);
            }
			////////////////


			Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
			t.start();
		}
	}

	static class Philosopher implements Runnable {

		private final Object leftFork;
		private final Object rightFork;

		public Philosopher(Object leftFork, Object rightFork) {
			this.leftFork = leftFork;
			this.rightFork = rightFork;
		}

		private void doAction(String action) {
			System.out.println(Thread.currentThread().getName() + " " + action);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// noop
			}
		}

		@Override
		public void run() {
			
			while (true) {
				doAction(System.nanoTime() + ": Thinking");
				synchronized (leftFork) {
					doAction(System.nanoTime() + ": Picked up left fork");
					synchronized (rightFork) {
						doAction(System.nanoTime() + ": Picked up right fork - eating");
					}
					doAction(System.nanoTime() + ": Put down right fork");
				}
				doAction(System.nanoTime() + ": Put down left fork.");
			}
		}
	}
}
