import java.util.Random;

import telran.games.GuessGame;

public class BullsCowsGameImpl implements GuessGame{
	
	
	static String random = new Random().ints(1, 10).distinct().limit(4)
			.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	
	public static String getResult(String input, boolean flag) {
		int bulls = 0;
		int cows = 0;
		if (random == input) {
			return "You win, your number correct!!!";
		}

		int[] randArr = new int[10];
		int[] inputArr = new int[10];

		for (int i = 0; i < random.length(); i++) {

			// get the current characters
			char charRand = random.charAt(i);
			char charInput = input.charAt(i);

			if (charRand == charInput)
				bulls++;
			else {
				// otherwise increment our arrays
				randArr[charRand - '0']++;
				inputArr[charInput - '0']++;
			}
		}

		for (int i = 0; i < 10; i++) {

			cows += Math.min(randArr[i], inputArr[i]);
		}

		if (bulls == 4) {
			return "You win, your number correct!!!";
		}
		if(flag) {
			BullsCowsGameImpl b = new BullsCowsGameImpl();
			return "Your result " + bulls + " Bulls " + cows + " Cows " + b.startGame();
		}
		return "Your result " + bulls + " Bulls " + cows + " Cows " ;
	}

	
	
	
	@Override
	public String startGame() {
		return " Secret number " +random;
	}
	@Override
	public String prompt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String move(String userInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFineshed() {
		// TODO Auto-generated method stub
		return false;
	}



}
