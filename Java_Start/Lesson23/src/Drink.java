
public class Drink extends Food {

	private boolean isSparkling;

	public Drink() {
	
	}

	public Drink(String name, double price, String mItem, boolean isKosher, boolean isSparkling) {
		super(name, price, mItem, isKosher);
	setSparkling(isSparkling);
	}

	public boolean isSparkning() {
		return isSparkling;
	}

	public void setSparkling(boolean isSparkling) {
		this.isSparkling = isSparkling;
	}

	@Override
	public boolean equals(Object obj) {
Drink dr = (Drink)obj;
if(super.equals(dr)==true&& isSparkling==dr.isSparkling)
	return true;
		return false;
	}

	@Override
	public String toString() {
		String sparkling = null;
		if(isSparkling == true)
			sparkling = "Sparkling";
			else
				sparkling = "Not sparkling";
		return super.toString()+sparkling;
	}

}
