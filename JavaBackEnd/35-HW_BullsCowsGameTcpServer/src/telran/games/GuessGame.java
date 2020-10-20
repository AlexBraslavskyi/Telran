package telran.games;

public interface GuessGame {

	String startGame();
	String prompt();
	String move(String userInput);
	boolean isFineshed();
	
}
