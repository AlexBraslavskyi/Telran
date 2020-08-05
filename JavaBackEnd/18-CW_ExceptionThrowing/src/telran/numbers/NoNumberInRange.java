package telran.numbers;

@SuppressWarnings("serial")
public class NoNumberInRange extends Exception {

	int delta;

	public NoNumberInRange(int delta) {
		super();
		this.delta = delta;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}
	
}
