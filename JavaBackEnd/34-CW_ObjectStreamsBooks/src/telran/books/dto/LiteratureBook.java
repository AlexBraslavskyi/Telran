package telran.books.dto;

import java.time.LocalDate;

public class LiteratureBook extends Book {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Genre genre;
	public LiteratureBook() {
		
	}
	public LiteratureBook(long isbn, String title, String author, int pages, Cover cover, LocalDate publishDate,
			Genre genre) {
		super(isbn, title, author, pages, cover, publishDate);
		this.genre = genre;
	}
	
	public Genre getGenre() {
		return genre;
	}
	@Override
	public String toString() {
		return "LiteratureBook [genre=" + genre + ", isbn=" + isbn + ", title=" + title + ", author=" + author
				+ ", pages=" + pages + ", cover=" + cover + ", publishDate=" + publishDate + "]";
	}
	

}
