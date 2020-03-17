
public class Food extends Goods {

	private boolean isKosher;
	

	public Food() {
		
	}

	public Food(int price, String name, String megItem, boolean isKosher) {
		super(price, name, megItem);
		setKosher(isKosher);
	}

	public boolean isKosher() {
		return isKosher;
	}

	public void setKosher(boolean isKosher) {
		this.isKosher = isKosher;
	}

	@Override
	public String toString() {
		
		return super.toString()+" Is Kosher: "+isKosher;
	}
	
	
}
