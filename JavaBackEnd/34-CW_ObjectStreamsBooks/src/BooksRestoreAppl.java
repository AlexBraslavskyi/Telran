import java.io.FileInputStream;
import java.io.ObjectInputStream;

import telran.books.dto.Book;

public class BooksRestoreAppl {

	public static void main(String[] args) throws Exception{
	
		Book[] books;
		try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream("books.data"))){
			books = (Book[]) stream.readObject();
		}
for(Book book: books) {
	System.out.println(book);
}
	}

}
