
public class Car {
	private String model;
	private int year;
	private String color;
	private int vin;
	
	public Car() {
		
	}
	public Car (String model, int year, String color, int vin) {
	setModel(model);
	setYear(year);
	setColor(color);
	setVin(vin);
	
	}
	
	public void setModel(String s) {
		if(s!=null)
			model = s;
		else 
			System.err.println("Error: wronge model");
	}
	public void setYear(int i) {
		if(i!=0)
			year = i;
		else 
			System.err.println("Error: wronge year");
	}
	public void setColor(String s) {
		if(s!=null)
			color = s;
		else 
			System.err.println("Error: wronge color");
	}
	public void setVin(int i) {
		if(i!=0)
			vin = i;
		else 
			System.err.println("Error: wronge vin");
	}
	public String getModel() {
		return model;
	}
	public int getYear() {
		return year;
	}
	public String getColor() {
		return color;
	}
	public int getVin() {
		return vin;
	}
	public void print(){
		System.out.println("Model:" + model);
		System.out.println("Year:" + year);
		System.out.println("Color:" + color);
		System.out.println("VIN:" + vin);
		
	}
}
