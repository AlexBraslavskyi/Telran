import java.time.Instant;

public class Racer extends Thread {
	private int threadId;
	private static Race race;
	private Instant finishTime;

	public Racer(int id, Race race) {
		super();
		this.threadId = id;
		Racer.race = race;

	}

	public Instant getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Instant finishTime) {
		this.finishTime = finishTime;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int id) {
		this.threadId = id;
	}

	@Override
	public void run() {
		int sleepDelta = race.getMax_sleep() - race.getMin_sleep() + 1;
		for (int i = 0; i < race.getDistance(); i++) {

			System.out.print(threadId);
			try {
				sleep((long) (race.getMin_sleep() + Math.random() * sleepDelta));
			} catch (InterruptedException e) {

			}
		}

			this.setFinishTime(Instant.now());
			Race.getWinners().add(this);
	}

}
