
public class KidsEducLibBook extends EducationLibBook {
	
	private int age;
	
	
	public KidsEducLibBook() {
		
	}
	public KidsEducLibBook (String autor, String title, int pages, int regNumb, String genre, String subjct, int age) {
		super(autor, title, pages, regNumb, genre, subjct);
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if(age>3&&age<16)
		this.age = age;
		else
			System.out.println("Error: wronge age");
	}
	public void display() {
		super.display();
		System.out.print(" Age - " + age);
	}
}
