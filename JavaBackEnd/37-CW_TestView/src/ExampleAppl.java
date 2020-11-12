
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class ExampleAppl {

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
			
		Menu menu = new Menu("Main manu", 
		new Menu("Calc", 
					Item.of("Sum", ExampleAppl::Sum), 
					Item.of("Sub", ExampleAppl::Sub),
					Item.of("Mult", ExampleAppl::Mult), 
					Item.of("Div", ExampleAppl::Div), 
					Item.exit()),
		new Menu("Date", 	
					Item.of("Add days", ExampleAppl::AddDays), 
					Item.of("Sub days", ExampleAppl::SubDays),
					Item.of("Days between", ExampleAppl::DaysBetween), 
					Item.exit()),
		new Menu("String", 
					Item.of("Join", ExampleAppl::JoinString), 
					Item.of("IsAnagram", ExampleAppl::Anagram),
					Item.exit()),
		Item.exit());

		menu.perform(io);
	}

	static void Sum(InputOutput ioParam) {

		Integer firstNumb = ioParam.readInteger("Enter first number");
		Integer secondNumber = ioParam.readInteger("Enter second number");
		ioParam.writeLn("Result " + (firstNumb + secondNumber));
	}

	static void Sub(InputOutput ioParam) {

		Integer firstNumb = ioParam.readInteger("Enter first number");
		Integer secondNumber = ioParam.readInteger("Enter second number");
		ioParam.writeLn("Result " + (firstNumb - secondNumber));
	}

	static void Mult(InputOutput ioParam) {

		Integer firstNumb = ioParam.readInteger("Enter first number");
		Integer secondNumber = ioParam.readInteger("Enter second number");
		ioParam.writeLn("Result " + (firstNumb * secondNumber));
	}

	static void Div(InputOutput ioParam) {

		Integer firstNumb = ioParam.readInteger("Enter first number");
		Integer secondNumber = ioParam.readInteger("Enter second number");
		ioParam.writeLn("Result " + ((double) firstNumb / (double) secondNumber));
	}

	static void AddDays(InputOutput ioParam) {
		String pattern = "yyyy-MM-dd";
		LocalDate date = ioParam.readDate("Enter date in formate " + pattern , pattern);
		Integer days = ioParam.readInteger("Enter number of days");
		ioParam.writeLn("Result " + date.plusDays(days));
	}

	static void SubDays(InputOutput ioParam) {
		String pattern = "yyyy-MM-dd";
		LocalDate date = ioParam.readDate("Enter date in formate " + pattern , pattern);
		Integer days = ioParam.readInteger("Enter number of days");
		ioParam.writeLn("Result " + date.minusDays(days));
	}

	static void DaysBetween(InputOutput ioParam) {
		String pattern = "yyyy-MM-dd";
		LocalDate from = ioParam.readDate("Enter date in formate " + pattern , pattern);
		LocalDate to = ioParam.readDate("Enter date in formate " + pattern , pattern);
		ioParam.writeLn("Result " + (int) ChronoUnit.DAYS.between(from, to) + " days");
	}

	static void JoinString(InputOutput ioParam) {
		List<String> options = new ArrayList<String>();
		options.add("Java");
		options.add("C");
		options.add("JS");
		String option = ioParam.readOption("Enter one of options (Java, C, JS)", options);
		String prompt = ioParam.readString("Enter text");
		ioParam.writeLn("Result - " + (prompt + "  " + option));
	}

	static void Anagram(InputOutput ioParam) {
		String word = ioParam.readString("Enter first word");
		String anagram = ioParam.readString("Enter second word");
		boolean isAnagram = false;
		if (isAnagram(word, anagram)) {
			isAnagram = true;
		}
		ioParam.writeLn("Is anagram - " + isAnagram);
	}

	public static boolean isAnagram(String word, String anagram) {
		if (word.isEmpty() || word.length() != anagram.length() || word.equals(anagram)) {
			return false;
		}
		char[] wordChars = word.toCharArray();
		char[] anagramChars = anagram.toCharArray();
		int[] helper = new int[123];

		for (int i = 0; i < wordChars.length; i++) {
			helper[wordChars[i]]++;
		}
		for (int i = 0; i < anagramChars.length; i++) {
			helper[anagramChars[i]]--;
			if (helper[anagramChars[i]] < 0) {
				return false;
			}
			if (i == anagramChars.length - 1) {
				return true;
			}
		}
		return false;

	}
	
}
