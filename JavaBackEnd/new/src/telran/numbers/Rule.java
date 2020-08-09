package telran.numbers;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;

public interface Rule {

	void checkRule(int number, int min, int max) throws RuleException, RangeException;
}
