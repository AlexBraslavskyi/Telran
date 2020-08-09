package telran.numbers;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;

public class DividerRule implements Rule {
	private final int divider;

	public DividerRule(int divider) {
		super();
		this.divider = divider;
	}

	@Override
	public void checkRule(int number, int min, int max) throws RuleException, RangeException {
		boolean flagExit = true;
		int remainder = number % divider;
		if (number >= min && number <= max) {
				if (remainder != 0) {
				if ((number - remainder) >= min) {
					throw new RuleException(-remainder);
				} else if ((number - remainder) < min) {
					for (int i = 1; i < max - number; i++) {
						if ((number + i) % divider == 0) {
							flagExit = false;
							throw new RuleException(i);
						}
					}
					if (flagExit) {
						throw new RangeException("No divisibles in range");
					}
				}
			}
		} else if (number < min) {
			remainder = min % divider;
			if (remainder == 0) {
				throw new RuleException(min - number);
			} else if (remainder != 0) {
				for (int i = 1; i < max; i++) {
					if ((min + i) % divider == 0) {
						flagExit = false;
						throw new RuleException(min + i - number);
					}
				} 
				if (flagExit) {
					throw new RangeException("No divisibles in range");
				}
			}
		} else if (number > max) {
			remainder = max % divider;
			if (remainder == 0) {
				throw new RuleException(max - number);
			} else if (remainder != 0) {
				for (int i = 1; i < max; i++) {
					if (((max - i) % divider) == 0) {
						flagExit = false;
						throw new RuleException(max - i - number);
					}
				}
				if (flagExit) {
					throw new RangeException("No divisibles in range");
				}
			}
		}
	}
}
