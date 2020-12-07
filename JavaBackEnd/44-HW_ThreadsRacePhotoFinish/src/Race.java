import java.util.concurrent.atomic.AtomicInteger;

public class Race {
public  int minSleep;
public  int maxSleep;
public int distance;
public AtomicInteger photoFinish = new AtomicInteger(0);
public Race(int minSleep, int maxSleep, int distance) {
	super();
	this.minSleep = minSleep;
	this.maxSleep = maxSleep;
	this.distance = distance;
}

}
