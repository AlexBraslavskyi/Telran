package telran.exceptions;

@SuppressWarnings("serial")
public class RuleException extends Exception {

	
	private int delta;

	public RuleException(int delta) {
		super();
		this.delta = delta;
	}

	public int getDelta() {
		return delta;
	}
}

