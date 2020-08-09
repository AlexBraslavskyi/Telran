package telran.numbers;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;

public class DividerRule implements Rule {
	private int divider;

	public DividerRule(int divider) {
		super();
		this.divider = divider;
	}

	@Override
	public void checkRule(int number, int min, int max) throws RuleException, RangeException {
		// first step: find minimal "good" and maximal "good" values 
				int remainderOfMin = min % divider;
				int remainderOfMax = max % divider;
//				int minDivisible = (remainderOfMin == 0) ? min : min - remainderOfMin + divider;
//				int maxDivisible = (remainderOfMax == 0) ? max : max - remainderOfMax;
				
				int minDivisible = min + (divider - min % divider) % divider; 	// optimized: nearest_divisible >= min
				int maxDivisible = max - max % divider;				// optimized: nearest_divisible <= max
				
				// check Rule is valid: at least one "good" number must exist
				if (minDivisible >  maxDivisible) {
					throw new RangeException(String.format("No divisibles by %d in Range [%d, %d]", divider, min,max));
				}
				
				// now validate number:
				// case 1: number is out of "good" numbers range to the left
				if (number < minDivisible) {
					throw new RuleException(minDivisible-number);
				}
				// case 2: number is out of "good" numbers range to the right
				if (number > maxDivisible) {
					throw new RuleException(maxDivisible-number);
				}
				// case 3: number is in the "good" numbers range
				int remainder = number % divider;
				if (remainder != 0) {
//					int delta = (remainder < divider / 2.) ? -remainder : divider - remainder; 
		            int delta = Math.round((float)number/divider)*divider - number;	// optimized
					throw new RuleException(delta);
				}
				// number is "good"
//		int remainder = number % divider;
//		if (number >= min && number <= max) {
//			if (remainder != 0) {
//				if ((number - remainder) >= min) {
//					throw new RuleException(-remainder);
//				}
//				int good = number - remainder + divider;
//				if (good > max) {
//					throw new RangeException("No divisibles in range");
//				}
//				throw new RuleException(good - number);
//			}			
//		} else if (number < min) {
////			remainder = min % divider;
////			if (remainder == 0) {
////				throw new RuleException(min);
////			}
//			int good =divider - min /divider + (min-number);
//			if (number + good < max) {
//				throw new RuleException(good);
//			}
//				
//				
//				
////				for (int i = 1; i < max; i++) {
////					if ((min + i) % divider == 0) {
////						throw new RuleException(min + i - number);
////					}
//				
//					throw new RangeException("No divisibles in range");
//		}else {
//			remainder = max % divider;
//			if (remainder == 0) {
//				throw new RuleException(max - number);
//			} else if (remainder != 0) {
//				for (int i = 1; i < max; i++) {
//					if (((max - i) % divider) == 0) {
//						throw new RuleException(max - i - number);
//					}
//				}
//			
//					throw new RangeException("No divisibles in range");
//			}
//		}
	}
}
