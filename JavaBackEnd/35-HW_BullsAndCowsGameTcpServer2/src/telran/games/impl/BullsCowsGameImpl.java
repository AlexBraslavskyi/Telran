package telran.games.impl;

import java.util.Arrays;
import java.util.Random;

import telran.games.interfaces.GuessGame;

public class BullsCowsGameImpl implements GuessGame {
	private Character[] number;
	private boolean isFinished;
 
	

	private void generateNumber() {
	number = new Random().ints('1', '9'+1).distinct()
				.limit(4).mapToObj(n -> (char)n).toArray(Character[]::new);
				
	}
	@Override
	public String startGame() {
		isFinished = false;
		generateNumber();
		return Arrays.toString(number);
	}

	@Override
	public String prompt() {
		
		return "Enter number of 4 unrepeated digits [1-9]";
	}

	@Override
	public String move(String userInput) {
		Integer bulls=0;
		Integer cows=0;
		int length = userInput.length();
		int limit= length < 4?length:4;
		for(int i=0;i<limit;i++){
			int index = userInput.indexOf(number[i]);
			if (index >= 0) {
				if (index == i) {
					bulls++;
				} else {
					cows++;
				}
				
			}
		}
		if (bulls == 4) {
			isFinished = true;
		}
		return String.format("bulls: %d; cows: %d", bulls, cows);
	}

	@Override
	public boolean isFinished() {
		
		return isFinished;
	}

}
