import telran.util.Timer;

public class TimerTestAppl {

	public static void main(String[] args) throws InterruptedException {
	
		Timer timer = new Timer();
		timer.start();
		
		///Doing something
		
		Thread.sleep(5000);
		timer.interrupt();
		//continue doing something
		Thread.sleep(5000);

	}

}
