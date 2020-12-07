import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class Race {
public  int minSleep;
public  int maxSleep;
public int distance;
public List<Racer> results = new CopyOnWriteArrayList<>();
//Collections.synchronizedList(new ArrayList<Racer>());
public Instant start;
public Race(int minSleep, int maxSleep, int distance) {
	super();
	this.minSleep = minSleep;
	this.maxSleep = maxSleep;
	this.distance = distance;
}

}
