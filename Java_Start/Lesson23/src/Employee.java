
public abstract class Employee {

	
	private String name;
	private int id;
	private String position;
	private double baseSalary;
	
	
	public Employee() {

	}
	public Employee(String name, int id, String position, double baseSalary) {
		this.name = name;
		this.id = id;
		this.position = position;
		this.baseSalary = baseSalary;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	@Override
	public boolean equals(Object obj) {
	Employee em = (Employee) obj;
	if(id==em.id)
		return true;
		return false;
	}

	@Override
	public String toString() {
		return " Name: "+ name+" ID: "+id+" Position: "+position+" Base Salary: "+baseSalary;
	}
	public abstract double calculateSalary();


}
