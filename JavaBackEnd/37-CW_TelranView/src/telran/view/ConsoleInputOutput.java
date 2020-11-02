package telran.view;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput {

	Scanner scanner = new Scanner(System.in);
	
	
	@Override
	public String readString(String prompt) {
	
		writeLn(prompt + ">");
		String res = scanner.nextLine();
		return res;
	}

	@Override
	public void writeObject(Object obj) {
		System.out.print(obj.toString());

	}
	@Override
	public  void consolClear() {
		// Clears Screen in java
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {
		}

	}

}
