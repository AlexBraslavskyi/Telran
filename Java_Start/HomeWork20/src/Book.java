
public class Book {

	
	private String autor;
	private String title;
	private int pages;
	
	public Book () {
		
	}
	
	public Book (String autor, String title, int pages) {
		this.setAutor(autor);
		setTitle(title);
		setPages(pages);
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		if(autor!= null)
		this.autor = autor;
		else 
			System.out.println("Error: wronge autor");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title != null)
		this.title = title;
		else
			System.out.println("Error: wronge title");
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		if(pages>10)
		this.pages = pages;
		else
			System.out.println("Error: wrong number pages");
	}
	
	public void display() {
		System.out.print("Autor - "+this.autor +" Title - "+this.title+" Pages - "+this.pages);
	}
	
}
