
import java.util.ArrayList;
import java.util.Arrays;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class MiniCalcAppl {
static InputOutput io = new ConsoleInputOutput();
	
	public static void main(String[] args) {
	Item arItems[] = {
			Item.of("Add two numbers", MiniCalcAppl::addNumbers),
			Item.exit()
	};
	Menu menu = new Menu("Mini Calc", new ArrayList <Item>(Arrays.asList(arItems)));
	
	menu.perform(io);
	}
	static void addNumbers(InputOutput ioParam) {
		
		Integer firstNumb = io.readInteger("Enter first number");
		Integer secondNumber = io.readInteger("Enter second number");
		io.writeLn(firstNumb + secondNumber);
	}

}
