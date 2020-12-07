import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Race {
	private int minSleep;
	private int maxSleep;
	private int distance;
	private static List<Racer> winners = new CopyOnWriteArrayList<>();
//			Collections.synchronizedList(new ArrayList<Racer>());

	public Race(int distance, int minSleep, int maxSleep) {
		super();
		this.minSleep = minSleep;
		this.maxSleep = maxSleep;
		this.distance = distance;

	}

	public static List<Racer> getWinners() {
		return winners;
	}

	public int getMin_sleep() {
		return minSleep;
	}

	public int getMax_sleep() {
		return maxSleep;
	}

	public int getDistance() {
		return distance;
	}

	

}
