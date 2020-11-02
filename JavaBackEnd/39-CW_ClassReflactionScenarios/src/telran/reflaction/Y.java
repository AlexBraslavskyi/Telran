package telran.reflaction;

public class Y implements Ix {

	int y;
	public Y(int y) {
		this.y = y;
	}
	@Override
	public void action() {
	System.out.println("Action of the class Y with y = " + y);

	}

}
