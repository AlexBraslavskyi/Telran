import telran.numbers.NoNumberInRange;
import telran.numbers.Range;

public class RangeTestAppl {

	private static final int MIN_RANGE = 10;
	private static final int MAX_RANGE = 40;

	public static void main(String[] args) {

		Range range = new Range(MIN_RANGE, MAX_RANGE); //(MAX_RANGE, MIN_RANGE);

		try {
			range.checkRange(50);
		} catch (NoNumberInRange e) {
			System.out.println(e.getDelta());
		}
	}

}
