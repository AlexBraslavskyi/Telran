
public class Drinks extends Food {

	
	private boolean isSparkling;

	
	
	public Drinks() {
	
	}

	public Drinks(int price, String name, String megItem, boolean isKosher, boolean isSparkling) {
		super(price, name, megItem, isKosher);
		setSparkling(isSparkling);
		
	}



	

	public boolean isSparkling() {
		return isSparkling;
	}

	public void setSparkling(boolean isSparkling) {
		this.isSparkling = isSparkling;
	}

	@Override
	public String toString() {
		return super.toString()+" Is sparkling: "+isSparkling;
	}
	
	
}
