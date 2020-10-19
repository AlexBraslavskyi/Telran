package telran.games;
import java.util.*;
import java.util.stream.Collectors;
public class BullsAndCowsGame {
/**
	 * 
	 */
Character[] number;

public Character[] getNumber() {
	return number;
}
public BullsAndCowsGame(){
	generateNumber();
}

private void generateNumber() {
number = new Random().ints('1', '9'+1).distinct()
			.limit(4).mapToObj(n -> (char)n).toArray(Character[]::new);
			
}
	/**
	 * 
	 * @param line - guess number from console
	 * @return array of numbers: first - bulls; second - cows 
	 */
	public Integer[] getMoveResult(String line) {
		Integer bulls=0;
		Integer cows=0;
		int length = line.length();
		int limit= length < 4?length:4;
		for(int i=0;i<limit;i++){
			int index = line.indexOf(number[i]);
			if (index >= 0) {
				if (index == i) {
					bulls++;
				} else {
					cows++;
				}
				
			}
		}
		return new Integer[]{bulls,cows};
	}

}
