
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class ExampleAppl {

	public static void main(String[] args) {
		
		
		
//		System.out.println("Enter");
//		Scanner scan = new Scanner(System.in);
//		String s = scan.nextLine();
//		
//		readDate(s, "YYYY-MM-dd");
//	}
//	public static LocalDate readDate(String prompt, String formate) {
			//Read string checked format and return localdate 
//		
//	     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formate);
//			     LocalDate date =  LocalDate.parse(prompt);
//
//			     if(prompt
//			    		 .equals(dateFormatter.format(date))) {
//			    	 System.out.println("Hi");
//			     }
//			    	 return date;
		
	
		
		InputOutput io = new ConsoleInputOutput();

		Item sum[] = { Item.of("Enter numbers", ExampleAppl::Sum), Item.exit() };
		Item sub[] = { Item.of("Enter numbers", ExampleAppl::Sub), Item.exit() };
		Item mult[] = { Item.of("Enter numbers", ExampleAppl::Mult), Item.exit() };
		Item div[] = { Item.of("Enter numbers", ExampleAppl::Div), Item.exit() };
		Item addDays[] = { Item.of("Enter numbers", ExampleAppl::AddDays), Item.exit() };
		Item subDays[] = { Item.of("Enter numbers", ExampleAppl::SubDays), Item.exit() };
		Item daysBetween[] = { Item.of("Enter numbers", ExampleAppl::DaysBetween), Item.exit() };
		Item joinString[] = { Item.of("Enter string", ExampleAppl::JoinString), Item.exit() };
		Item anagram[] = { Item.of("Enter string", ExampleAppl::Anagram), Item.exit() };
		
		
		Item arCalc[] = { Item.of("Sum", a -> {
			Menu menu = new Menu("Sum", new ArrayList<Item>(Arrays.asList(sum)));
			menu.perform(io);}), 
				Item.of("Sub", a -> {
			Menu menu = new Menu("Sub", new ArrayList<Item>(Arrays.asList(sub)));
			menu.perform(io);}),
				Item.of("Mult", a -> {
			Menu menu = new Menu("Mult", new ArrayList<Item>(Arrays.asList(mult)));
			menu.perform(io);}),
				Item.of("Div", a -> {
			Menu menu = new Menu("Div", new ArrayList<Item>(Arrays.asList(div)));
			menu.perform(io);}),
				Item.exit() };
		
		Item date[] = { Item.of("Add days", a -> {
			Menu menu = new Menu("Add days", new ArrayList<Item>(Arrays.asList(addDays)));
			menu.perform(io);}),
				Item.of("Sub days", a -> {
			Menu menu = new Menu("Sub days", new ArrayList<Item>(Arrays.asList(subDays)));
			menu.perform(io);}),
				Item.of("Days between", a -> {
			Menu menu = new Menu("Days between", new ArrayList<Item>(Arrays.asList(daysBetween)));
			menu.perform(io);}),
				Item.exit() };
		
		Item string[] = { Item.of("Join", a -> {
			Menu menu = new Menu("Join", new ArrayList<Item>(Arrays.asList(joinString)));
			menu.perform(io);}),
				Item.of("Anagram", a -> {
			Menu menu = new Menu("Anagram", new ArrayList<Item>(Arrays.asList(anagram)));
			menu.perform(io);}),
				Item.exit() };
		
		Item arItems[] = { Item.of("Calc", a -> {
			Menu menu = new Menu("Calc", new ArrayList<Item>(Arrays.asList(arCalc)));
			menu.perform(io);}),
				Item.of("Date", a -> {
			Menu menu = new Menu("Date", new ArrayList<Item>(Arrays.asList(date)));
			menu.perform(io);}),
				Item.of("String", a -> {
			Menu menu = new Menu("String", new ArrayList<Item>(Arrays.asList(string)));
			menu.perform(io);}), 
				Item.exit() };
		Menu menu = new Menu("Main manu", new ArrayList<Item>(Arrays.asList(arItems)));

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
//		System.out.println("Enter date in formate YYYY-MM-dd");
//		Scanner scan = new Scanner(System.in);
//		String s = scan.nextLine();
		LocalDate date = ioParam.readDate("Enter date in formate YYYY-MM-dd", "YYYY-MM-dd");
		Integer days = ioParam.readInteger("Enter number of days");
		ioParam.writeLn("Result " + date.plusDays(days));
	}
	static void SubDays(InputOutput ioParam) {

		//TODO
	}
	static void DaysBetween(InputOutput ioParam) {

		//TODO
	}
	static void JoinString(InputOutput ioParam) {

		//TODO
	}
	static void Anagram(InputOutput ioParam) {

		//TODO
	}
}
