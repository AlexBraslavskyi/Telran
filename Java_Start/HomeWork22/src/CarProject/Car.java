package CarProject;

public class Car {

	private String model;
	private int year;
	private String colour;
	
	public Car() {
	}
	
	public Car(String model, int year, String colour) {
		this.setModel(model);
		this.setYear(year);
		this.setColour(colour);
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		if(model!=null)
		this.model = model;
		else
			System.out.println("Error");
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		if(year!=0)
		this.year = year;
		else
		System.out.println("Error");
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		if(colour!=null)
		this.colour = colour;
		else
			System.out.println("Error");	
	}
	
	
	@Override
	public boolean equals(Object obj) {
		Car c = (Car)obj;
		if(this.model.equals(c.model)&&this.year==c.year&&this.colour.equals(colour))
			return true;
		else
		return false;
	}

	@Override
	public String toString() {
		
		return "Model: "+model+" Year: " +year+" Colour: " +colour;
	}
}
