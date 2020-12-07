public class PrinterPortions extends Thread {

	private int number;
	private int partion;
	private int nNumbers;

	public PrinterPortions(int number, int partion, int nNumbers) {
		setDaemon(true);
		this.number = number;
		this.partion = partion;
		this.nNumbers = nNumbers;
	}

	@Override
	public void run() {
		int total = 0;
		int stop = nNumbers;
		while (true) {
//if(interrupted()) {
//	int count = 0;
//	for (int i = 0; i < partion; i++) {
//		System.out.print(number);
//		count++;
//	}
//	total = total+count;
//	stop = stop - partion;
//	System.out.println();
//	if (stop == 0) {
//		System.out.println("-- Number " + number + " finished, printed - " + total + " times --");
//	}
//}
			try {
				sleep(1);
			} catch (InterruptedException e) {
int count = 0;
				for (int i = 0; i < partion; i++) {
					System.out.print(number);
					count++;
				}
				total = total+count;
				stop = stop - partion;
				System.out.println();
				if (stop == 0) {
					System.out.println("-- Number " + number + " finished, printed - " + total + " times --");
					break;
				}
			}

		}
	}
}
