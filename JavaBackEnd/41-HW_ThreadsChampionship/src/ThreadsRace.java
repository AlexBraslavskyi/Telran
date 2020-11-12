import java.time.Instant;

public class ThreadsRace  extends Thread{
	
	int number;
	int distance;
	int timeOut;
	long finishedTime;
	
	public ThreadsRace(int number, int distance, int timeOut) {
		super();
		this.number = number;
		this.distance = distance;
		this.timeOut = timeOut;
	
		
	}
	@Override
	public void run() {
		int step = 1;
		
		for (int i = 0; i < distance; i++) {
			System.out.printf("%d %d \n",number,step++); 
			try {
				sleep(timeOut);
				if(i == distance-1) {
					finishedTime = Instant.now().getNano();
					System.out.println(number +" fihised "+finishedTime);
				}
			} catch (InterruptedException e) {
				
			}
		}
	}
}
