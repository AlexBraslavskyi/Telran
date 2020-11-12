
public class Printer extends Thread {
char symbol;
int nRuns;
public Printer(char symbol, int nRuns) {
	super();
	this.symbol = symbol;
	this.nRuns = nRuns;
}
@Override
public void run() {
	for (int i = 0; i < nRuns; i++) {
		System.out.println(symbol);
		try {
			sleep(10);
		} catch (InterruptedException e) {
			
		}
	}
}
}
