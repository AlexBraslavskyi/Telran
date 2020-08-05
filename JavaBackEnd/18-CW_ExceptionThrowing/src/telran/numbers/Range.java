package telran.numbers;

public class Range {

	
	int min;
	int max;
	public Range(int min, int max) {
		if(max <= min) {
			throw new IllegalArgumentException
			(String.format("Argument value min: %d greater or equal to max: %d",min, max));
		}
		this.min = min;
		this.max = max;
	}
	
	public void checkRange(int number) throws NoNumberInRange {
		if(number < min) {
			throw new NoNumberInRange(min - number);
		}
		if(number > max) {
			throw new NoNumberInRange(max - number);
		}
		
	}
	
	
}
