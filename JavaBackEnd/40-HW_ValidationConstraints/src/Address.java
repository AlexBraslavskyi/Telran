import telran.validation.constraints.Min;
import telran.validation.constraints.NotEmpty;

public class Address {
	@NotEmpty(message = "Field city can't be empty")
    private String city;
	private String street;
	@Min(value = 1, message = "number can't be less than " + 1)
	private int building;	
	private int apartment;

	public Address(String city, String street, int building, int apartment) {
		super();
		this.city = city;
		this.street = street;
		this.building = building;
		this.apartment = apartment;
	
	}
	public Address() {
	
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", street=" + street + ", building=" + building + ", apartment=" + apartment
				+ "]";
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getBuilding() {
		return building;
	}

	public void setBuilding(int building) {
		this.building = building;
	}

	public int getApartment() {
		return apartment;
	}

	public void setApartment(int apartment) {
		this.apartment = apartment;
	}

}

