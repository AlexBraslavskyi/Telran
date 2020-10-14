package telran.books.dto;

import java.time.LocalDate;

public class TechnicalBook extends Book {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String subject;
	public TechnicalBook() {
		
	}
	public TechnicalBook(long isbn, String title, String author, int pages, Cover cover, LocalDate publishDate,
			String subject) {
		super(isbn, title, author, pages, cover, publishDate);
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}
	@Override
	public String toString() {
		return "TechnicalBook [subject=" + subject + ", isbn=" + isbn + ", title=" + title + ", author=" + author
				+ ", pages=" + pages + ", cover=" + cover + ", publishDate=" + publishDate + "]";
	}
	
}
