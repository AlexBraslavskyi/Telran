
public class Track extends Thread {

	
	int load;
	int nLoads;
	static long elevator1;
	static long elevator2;
	static final Object mutex = new Object();
	public static long getElevator1() {
		return elevator1;
	}

	public static long getElevator2() {
		return elevator2;
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

	static synchronized private void load2(int load) {
	
		elevator2 += load;
	
		
	}

	static synchronized private void load1(int load) {
	
		synchronized (mutex) {
			elevator1 += load;
		}
	
		
	}
}
