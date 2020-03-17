
public class Alcohol extends Drinks {

	private int alcohol;

	
	
	public Alcohol() {
	
	}

	public Alcohol(int price, String name, String megItem, boolean isKosher, boolean isSparkling, int alcohol) {
		super(price, name, megItem, isKosher, isSparkling);
setAlcohol(alcohol);
	}

	public int getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(int alcohol) {
		if(alcohol!=0)
		this.alcohol = alcohol;
		else
		System.out.println("Error: wrong persent alcohol");
	}

	@Override
	public String toString() {
		return super.toString()+" Persent alcohol: "+alcohol;
	}
	
	
}
