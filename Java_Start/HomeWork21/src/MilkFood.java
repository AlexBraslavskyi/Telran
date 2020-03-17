
public class MilkFood extends Food {

	private String milkType;
	private int fat;
	
	
	
	public MilkFood() {
	
	}
	public MilkFood(int price, String name, String megItem, boolean isKosher, String milkType, int fat) {
		super(price, name, megItem, isKosher);
		setFat(fat);
		setMilkType(milkType);
	}
	public String getMilkType() {
		return milkType;
	}
	public void setMilkType(String milkType) {
		if(milkType!=null)
		this.milkType = milkType;
		else 
			System.out.println("Error: wronge milk type");
	}
	public int getFat() {
		return fat;
	}
	public void setFat(int fat) {
		if(fat!=0)
		this.fat = fat;
		else
			System.out.println("Error: wronge fat");
	}
	@Override
	public String toString() {
		return super.toString()+" Milk type: " + milkType+" Fat: "+fat;
	}
	
	
}
