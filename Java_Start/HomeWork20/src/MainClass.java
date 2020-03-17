
public class MainClass {

	public static void main(String[] args) {
	Book b = new Book("Knut", "Art of Computer Programming", 300);
	b.display();
	System.out.println();
	LibraryBook l = new LibraryBook("Tolstoy", "War and Peace", 500, 5548);
	l.display();
	System.out.println();
	FictionLibBook f = new FictionLibBook("Tolkien", "The lord of the Rings", 500, 4565, "fiction");
	f.display();
	System.out.println();
	EducationLibBook e = new EducationLibBook("Knut", "Art of Computer Programming", 300, 56487, "Education", "Programming");
	e.display();
	System.out.println();
	KidsEducLibBook k = new KidsEducLibBook("Pupkin", "Start read", 50, 56454, "Education", "scool", 6);
	k.display();
	}

}
