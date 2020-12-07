import java.util.Scanner;

public class PrinterController {
public static void main(String[] args) throws InterruptedException {
String symbols = "abcdefg";
	Printer printer = new Printer(symbols);
	printer.start();
	Scanner scan = new Scanner(System.in);
	
	while(true) {
	String line = scan.nextLine();
	
	if(line.equalsIgnoreCase("q")) {
		break;
}
	printer.interrupt();
}
	printer.stopRun();
}
}
