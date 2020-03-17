
public class Company {

	private String name;
	private String address;
	private int id;
	
	
	public Company() {
	
	}

	public Company(String name, String address, int id){
		this.setId(id);
		this.setName(name);
		this.setAddress(address);
		
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
if(name!= null)
		this.name = name;
else
	System.out.println("Error");
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if(address!=null)
		this.address = address;
		else
			System.out.println("Error");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id>0)
		this.id = id;
		else
			System.out.println("Error");
	}
	public void display(){
		System.out.print("Name - "+this.name+" Address -"+this.address+" ID -"+this.id);
		System.out.println();
	}
	@Override
	public String toString(){
		String str = "Name - " + name +" Address -" + address + " ID -" + id;
		return str;
	}
	
	
}
