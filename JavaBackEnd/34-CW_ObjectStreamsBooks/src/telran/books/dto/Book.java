package telran.books.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	long isbn;
	String title;
	String author;
	int pages;
	Cover cover;
	LocalDate publishDate;
	public Book(long isbn, String title, String author, int pages, Cover cover, LocalDate publishDate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.cover = cover;
		this.publishDate = publishDate;
	}
	public Book() {
		
	}

	public long getIsbn() {
		return isbn;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public int getPages() {
		return pages;
	}
	public Cover getCover() {
		return cover;
	}
	public LocalDate getPublishDate() {
		return publishDate;
	}
	
}
