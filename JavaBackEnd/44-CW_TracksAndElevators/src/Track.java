import java.util.concurrent.atomic.AtomicLong;

public class Track extends Thread {

	int load;
	int nLoads;
	static AtomicLong elevator1 = new AtomicLong(0);
	static AtomicLong elevator2 = new AtomicLong(0);

	public static long getElevator1() {
		return elevator1.get();
	}

	public static long getElevator2() {
		return elevator2.get();
	}

	public Track(int load, int nLoads) {
		this.load = load;
		this.nLoads = nLoads;
	}
	
	@Override
	public void run() {
	
for(int i = 0; i< nLoads; i++) {
	load1(load);
	load2(load);
}
	}

	static private void load2(int load) {
		elevator2.addAndGet(load);
	}

	static private void load1(int load) {
			elevator1.addAndGet(load);
	}
}
