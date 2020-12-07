import telran.view.*;

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
