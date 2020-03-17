
public class Car {

	
	private String model;
	private int year;
	private String color;

	public Car (){
		
	}
	
	public Car (Car otherCar){
		this.model = otherCar.model;
		this.year = otherCar.year;
		this.color = otherCar.color;
	}
	public Car (String model, int year, String color){
		this.setModel(model);
		setYear(year);
		setColor(color);
	}
	public void setColor(String color) {
		if (color!=null)
		this.color = color;
		else 
			System.out.println("Error");
	}
	public void setModel(String model) {
		if (model!=null)
			this.model = model;
		else
			System.out.println("Error");
	}
	public void setYear(int year) {
		if (year!=0)
		this.year = year;
		else
		System.out.println("Error");
	}
	public String getModel() {
		return model;
	}
	public String getColor() {
		return color;
	}
	public int getYear() {
		return year;
	}
	
	public void display(){
		System.out.print("Model - " + this.model + " Year - " + this.year+ " Color - " + this.color);
		
	}
}
