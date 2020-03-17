
public class LibraryBook extends Book{
	
	private int regNumb;
	
	public LibraryBook() {
		
	}
	
	public LibraryBook(String autor, String title, int pages, int regNumb) {
		super(autor, title, pages);
	this.regNumb = regNumb;
		
	}
	

public int getRegNumb() {
		return regNumb;
	}

	public void setRegNumb(int regNumb) {
		if(regNumb>0)
		this.regNumb = regNumb;
		else
			System.out.println("Error: wronge registration number");
	}

public void display() {
	super.display();
	System.out.print(" Registration number - "+this.regNumb);
}
}
