
public class FictionLibBook extends LibraryBook{

	private String genre;
	
	public FictionLibBook() {
		
	}
	public FictionLibBook(String autor, String title, int pages, int regNumb, String genre) {
		super(autor, title, pages, regNumb);
		this.genre = genre;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		if(genre != null)
		this.genre = genre;
		else
			System.out.println("Error: wronge genre");
	}
	public void display() {
		super.display();
		System.out.print(" Genre - " + this.genre);
	}
	
}
