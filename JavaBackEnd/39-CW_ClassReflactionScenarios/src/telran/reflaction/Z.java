package telran.reflaction;

public class Z implements Ix {
int z;

public Z(int z) {
	this.z = z;
}
	
	@Override
	public void action() {
	System.out.println("Action of the class Z with z = " +z);

	}

}
