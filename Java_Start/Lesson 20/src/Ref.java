
public class Ref extends Track {

	private int tempr;

	public Ref (){
		
	}
	
	public Ref (String model, int year, String color, int tonnage, int tempr){
		super(model,year,color,tonnage);
		this.tempr = tempr;
	}
	public int getTempr() {
		return tempr;
	}

	public void setTempr(int tempr) {
		this.tempr = tempr;
	}
	public void display(){
		super.display();
		System.out.print(" Temperature - " + tempr);
	}
	
	
}
