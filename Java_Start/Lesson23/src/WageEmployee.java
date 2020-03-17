
public class WageEmployee extends Employee {

	private double hours;
	private double wage;
	
	
	public WageEmployee() {
		
	}
	public WageEmployee(String name, int id, String position, double baseSalary, double hours, double wage) {
		super(name, id, position, baseSalary);
		setHours(hours);
		setWage(wage);
		
	}
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		this.wage = wage;
	}
	@Override
	public double calculateSalary() {
	
		return hours*wage;
	}

	@Override
	public String toString() {
		return super.toString()+" Hours: "+ hours+" Wage: "+ wage;
	}
	
}
