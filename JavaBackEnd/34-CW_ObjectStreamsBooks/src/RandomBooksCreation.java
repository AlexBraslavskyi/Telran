import static telran.util.RandomData.chance;
import static telran.util.RandomData.getRandomDate;
import static telran.util.RandomData.getRandomElement;
import static telran.util.RandomData.getRandomInt;
import static telran.util.RandomData.getRandomLong;
import static telran.util.RandomData.getRandomName;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.stream.Stream;

import telran.books.dto.Book;
import telran.books.dto.Cover;
import telran.books.dto.Genre;
import telran.books.dto.LiteratureBook;
import telran.books.dto.TechnicalBook;

public class RandomBooksCreation {

	private static final int TECHNICAL_BOOK_PROB = 30;
	private static final int MIN_PAGES = 50;
	private static final int MAX_PAGES = 100;
	private static final int MIN_PUB_YEAR = 1900;
	private static final int MAX_PUB_YEAR = 2020;
	private static final long MIN_ISBN = 1_000_000_000_000L;
	private static final long MAX_ISBN = 9_000_000_000_000L;
	private static final long N_BOOKS = 100;

	public static void main(String[] args) throws Exception {
		Book[] books = getRandomBooks();
try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("books.data"))){
	stream.writeObject(books);
	
}
	}

	private static Book[] getRandomBooks() {
		
		
		return Stream.generate(()->{
			return chance() <= TECHNICAL_BOOK_PROB ? getTechnicalBook() : getLiterature();
		}).limit(N_BOOKS).toArray(Book[]::new);
	}

	private static Book getTechnicalBook() {
		long isbn = getRandomISBN();
		return new TechnicalBook(isbn, getRandomTitle(isbn), getRandomAuthor(), getRandonPages(), getRandomCover(), getRandomPublishDate(), 
				getRandomSubject());
	}

	private static String getRandomSubject() {
	String subjects[] = {"FullStack Development", "Java Programing", "Front-End", "Networking", "Back-end Microservice", "DB"};
		return getRandomElement(subjects);
	}

	private static Book getLiterature() {
	
		
		long isbn = getRandomISBN();
		return new LiteratureBook(isbn, getRandomTitle(isbn), getRandomAuthor(), getRandonPages(), getRandomCover(), getRandomPublishDate(), 
				getRandomGenre());
	}

	private static Genre getRandomGenre() {
	
		return getRandomElement(Genre.values());
	}

	private static LocalDate getRandomPublishDate() {

		return getRandomDate(MIN_PUB_YEAR, MAX_PUB_YEAR);
	}

	private static Cover getRandomCover() {
		
		return getRandomElement(Cover.values());
	}

	private static int getRandonPages() {
		
		return getRandomInt(MIN_PAGES, MAX_PAGES);
	}

	private static String getRandomAuthor() {
		// TODO Auto-generated method stub
		return getRandomName();
	}

	private static String getRandomTitle(long isbn) {
		
		return "title" + isbn;
	}

	private static long getRandomISBN() {
	
		return getRandomLong(MIN_ISBN, MAX_ISBN);
	}


}
