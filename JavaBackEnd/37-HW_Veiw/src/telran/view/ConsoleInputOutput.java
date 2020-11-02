package telran.view;

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
		System.out.print(obj);

	}

}
