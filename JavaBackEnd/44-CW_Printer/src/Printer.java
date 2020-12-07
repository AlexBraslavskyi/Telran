public class Printer extends Thread {
 

	private  String symbols;
private volatile boolean running = true;
	public Printer(String symbols) {
		this.symbols = symbols;
	}
	
	public void stopRun(){
		running = false;
	}
	@Override
	public void run() {
		int index = 0;
		int length = symbols.length();

		while(running) {
			System.out.println(symbols.charAt(index));
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				index++;
				if(index == length) {
					index = 0;
				}
			}
		}
	}
}

