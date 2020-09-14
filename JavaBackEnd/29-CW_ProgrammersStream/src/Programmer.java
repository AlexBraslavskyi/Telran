import java.util.Arrays;

public class Programmer {
int id;
String name;
int salary;
String[] technologies;

	
	
	
	public Programmer(int id, String name, int salary, String[] technologies) {
	super();
	this.id = id;
	this.name = name;
	this.salary = salary;
	this.technologies = technologies;
}
	

	@Override
	public String toString() {
		return "Programmer [id=" + id + ", name=" + name + ", salary=" + salary + ", technologies="
				+ Arrays.toString(technologies) + "]";
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public int getSalary() {
		return salary;
	}

	public String[] getTechnologies() {
		return technologies;
	}

	public static void main(String[] args) {
	

	}

}
