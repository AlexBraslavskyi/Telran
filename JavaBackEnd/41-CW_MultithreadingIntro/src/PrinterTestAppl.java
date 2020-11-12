import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PrinterTestAppl {

	private static final int N_RUNS = 100;

	public static void main(String[] args) throws InterruptedException {
		Printer printer1 = new Printer('*', N_RUNS);
		Printer printer2 = new Printer('#', N_RUNS);
		Instant start = Instant.now();
		printer1.start();
		printer2.start();
		printer1.join(); //waiting for printer1 is finished
		printer2.join(); //waiting for printer2 is finished
		System.out.printf("Running time of all threads is %d\n",
				ChronoUnit.MILLIS.between(start, Instant.now()));
		

	}

}
