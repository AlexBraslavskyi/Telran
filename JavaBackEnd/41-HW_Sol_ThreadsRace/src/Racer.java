
public class Racer extends Thread {
private int threadId;
private Race race;



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
	int sleepDelta = race.max_sleep - race.min_sleep + 1;
	for(int i = 0; i < race.distance; i++) {
		System.out.println(threadId);
		try {
			sleep((long) (race.min_sleep + Math.random() * sleepDelta));
		} catch (InterruptedException e) {
			
		}
	}
	if (race.photoFinish == 0) {
		race.photoFinish = threadId;
	}
}

}
