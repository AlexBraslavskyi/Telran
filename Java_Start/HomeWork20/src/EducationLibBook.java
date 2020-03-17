
public class EducationLibBook extends LibraryBook {

	private String subject;
	
	public EducationLibBook() {
		
	}
	
	public EducationLibBook (String autor, String title, int pages, int regNumb, String genre, String subjct) {
		
		super(autor, title, pages, regNumb);
		this.subject = subjct;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		if(subject != null)
		this.subject = subject;
		else
			System.out.println("Error: wronge subject");
	}
	public void display() {
		super.display();
		System.out.print(" Subject - " + this.subject);
	}
	
}
