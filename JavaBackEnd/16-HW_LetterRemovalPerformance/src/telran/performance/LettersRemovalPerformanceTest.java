package telran.performance;

import telran.util.LettersRemovalInterface;

public class LettersRemovalPerformanceTest extends PerformanceTest {

	private String string;
	private char letter;
	private LettersRemovalInterface lettersRemoval;
	
	private char getRandomLetter() {
		return (char) ('a' + Math.random() * ('z' - 'a' + 1));
	}
	
	public LettersRemovalPerformanceTest(int stringLength, LettersRemovalInterface lettersRemoval, String testName,
			int nRuns) {

		super(testName, nRuns);
		char[] letters = new char[stringLength];
		for (int i = 0; i < stringLength; i++) {
			letters[i] = getRandomLetter();
		
		
		}
		string = new String(letters);
		this.lettersRemoval = lettersRemoval;
		letter = getRandomLetter();
	}

	

	@Override
	protected void runTest() {
	
		lettersRemoval.removeLetter(string, letter);


	}

}
