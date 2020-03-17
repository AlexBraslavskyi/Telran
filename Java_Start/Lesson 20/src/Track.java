
public class Track extends Car{

	private int tonnage;
	
	public Track(){
		
	}

	public Track(String model, int year, String color, int tonnage){
		super(model, year, color);
		this.setTonnage(tonnage);
		
	}
	public int getTonnage() {
		return tonnage;
	}

	public void setTonnage(int tonnage) {
	
		if(tonnage>0)
		this.tonnage = tonnage;
		else 
			System.out.println("Error");
	}
	
	public void display (){
		super.display();
		System.out.print(" Tonnage - " + tonnage);
		
	}
	
	
	
}
