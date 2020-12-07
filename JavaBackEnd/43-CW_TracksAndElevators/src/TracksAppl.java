import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TracksAppl {

	private static final int N_Tracks = 1000;
	private static final int LOAD = 1;
	private static final int N_LOADS = 10000;

	public static void main(String[] args) throws InterruptedException {
		Track [] tracks = new Track[N_Tracks];
	
		Instant start = Instant.now();
	startLoads(tracks);
	waitForLoadsFinishing(tracks);
	System.out.printf("elevator1 is loaded with %d tonns\n"
			+ "elevator2 is loaded wits %d tones\n"
			+ "runing time is %d milliseconds", Track.getElevator1(), Track.getElevator2(),
			ChronoUnit.MILLIS.between(start, Instant.now()));

	}

	private static void waitForLoadsFinishing(Track[] tracks) throws InterruptedException {
		for(Track track:tracks) {
			track.join();
		}
		
	}

	private static void startLoads(Track[] tracks) {
	for(int i =0; i<tracks.length;i++) {
		tracks[i] = new Track(LOAD, N_LOADS);
		tracks[i].start();
	}
		
	}

}
