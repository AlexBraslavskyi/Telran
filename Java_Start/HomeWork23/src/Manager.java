
public class Manager extends Employee {

	private int grade;

	
	public Manager() {
		
	}

	public Manager(String name, int id, String position, double baseSalary, int grade) {
		super(name, id, position, baseSalary);
	setGrade(grade);
	calculateSalary();
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}



	@Override
	public  double calculateSalary() {
	
		return getBaseSalary()*grade;
	}
	@Override
	public String toString() {
		String gd = null;
		switch (grade){
			case 1:gd= "low";break;
			case 2:gd="middle";break;
			case 3:gd="high";break;
			case 4:gd="top";break;
		}
		return super.toString()+" Grade: " +gd+" Salary : " + calculateSalary();
	}
}
