package telran.numbers;

import telran.exceptions.RuleException;

public class DividerRule implements Rule {
	private int divider;
	@Override
	public void checkRule(int number, int min, int max) throws RuleException {
			int remainder = number % divider;
			if(remainder != 0) {
				throw new RuleException(-remainder);
			}
		
	}

	}
