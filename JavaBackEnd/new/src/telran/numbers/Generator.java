package telran.numbers;

import telran.exceptions.RuleException;

public class Generator {
private final int min;
private final int max;
private Rule rule;
public Generator(int min, int max, Rule rule) {
	super();
	if (max <= min) {
		throw new IllegalArgumentException("Wrong range");
	}
	this.min = min;
	this.max = max;
	this.rule = rule;
}
public Rule getRule() {
	return rule;  
}
public void setRule(Rule rule) {
	this.rule = rule;
}
public int[] generate(int nNumbers) {
	int[] res = new int[nNumbers];
	for (int i = 0; i < nNumbers; i++) {
		res[i] = getNumberRule();
	}
	return res;
}
private int getNumberRule() {
	int res = getRandomNumber();
	try {
		rule.checkRule(res, min, max);
	} catch (RuleException e) {
		res = res + e.getDelta();
	}
	return res;
}
private int getRandomNumber() {
	return (int) (min + Math.random() * (max - min + 1));
}
}
