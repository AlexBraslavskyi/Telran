import java.util.concurrent.CopyOnWriteArrayList;

public class PrinterPortionsController {
	private static final int N_PRINTERS = 4;
	//numbers % partitions must be 0
	private static final int N_PORTIONS = 5;
	private static final int N_NUMBERS = 1000;

	public static void main(String[] args) throws InterruptedException {
		int nRuns = N_NUMBERS / N_PORTIONS;

		if (N_NUMBERS % N_PORTIONS != 0) {
			System.out.println("Wrong paramers of partiton or numbers (numbers % partitions must be 0)");
		} else {

			CopyOnWriteArrayList<PrinterPortions> printers = new CopyOnWriteArrayList<>();

			for (int i = 0; i < N_PRINTERS; i++) {

				printers.add(new PrinterPortions(i + 1, N_PORTIONS, N_NUMBERS));
				printers.get(i).start();

			}

			while (nRuns != 0) {
				for (int i = 0; i < printers.size(); i++) {
					printers.get(i).interrupt();
					Thread.sleep(1);
				}

				nRuns--;
				if (nRuns == 0) {

				}
			}
		}
	}

}
