package CarProject;

public class Garage extends Car {

	
	private String name;
	private String address;
	private Car [] cars;
	
	public Garage() {
	}
	public Garage(String name, String address, Car[]cars) {
		setName(name);
		setAddress(address);
		setCars(cars);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name!=null)
		this.name = name;
		else
			System.out.println("Error");
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		if(address != null)
		this.address = address;
		else
			System.out.println("Error");
	}
	public Car[] getCars() {
		return cars;
	}
	public void setCars(Car[] cars) {
		if(cars!=null)
		this.cars = cars;
		else
			System.out.println("Error");
	}
	@Override
	public String toString() {
String str = "Name: " + name+ " Address: "+ address+"\nCars in garage:\n";
for(int i =0;i<cars.length;i++)	
	str += cars[i]+"\n";

return str;
	}
	@Override
	public boolean equals(Object obj) {
		Garage g = (Garage)obj;
		if(this.name.equals(g.name)&&this.address.equals(g.address))
			return true;
		else
		return false;
	}
	
	
}
