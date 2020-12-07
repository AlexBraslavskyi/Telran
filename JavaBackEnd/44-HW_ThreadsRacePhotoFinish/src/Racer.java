public class Racer extends Thread {
private int threadId;
private final Race race;



public Racer(int id, Race race) {
	super();
	this.threadId = id;
	this.race = race;
}
public int getThreadId() {
	return threadId;
}
public void setThreadId(int id) {
	this.threadId = id;
}


@Override
public void run() {
	int sleepDelta = race.maxSleep - race.minSleep + 1;
	for(int i = 0; i < race.distance; i++) {
		
		try {
			sleep((long) (race.minSleep + Math.random() * sleepDelta));
		} catch (InterruptedException e) {
			
		}
		System.out.println(threadId);
	}

		race.photoFinish.compareAndSet(0, threadId);
}

}
