import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class RaceController {
static InputOutput inputOutput = new ConsoleInputOutput();
	public static void main(String[] args) {
		Item[] items = {
				new StartRace(),
				Item.exit()
		};
		Menu menu = new Menu("Threads Race",items );
		menu.perform(inputOutput);
	}

}
