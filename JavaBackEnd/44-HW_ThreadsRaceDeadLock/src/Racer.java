public class Racer extends Thread {
private static Integer threadId;
private static Integer threadId2 = 0;
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
	synchronized (threadId) {
		photoFinish();
		synchronized (threadId2) {
			photoFinish();
	}
	}
	synchronized (threadId2) {
		photoFinish();
		synchronized (threadId) {
			photoFinish();
	}
	}	
}

private void photoFinish() {
	try {
		sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (race.photoFinish == 0) {
		race.photoFinish =  threadId;
	}
	
}

}
