
public class MainClass {

	public static void main(String[] args) {
		Book b = new Book();
		b.setAutor("Knut");
		b.setTitle("Java");
		b.setPages(500);
		b.setIsbn(465456456);
		System.out.println("Autor:" + b.getAutor());
		System.out.println("Title :"+b.getTitle());
		System.out.println("Pages :"+b.getPages());
		System.out.println("ISBN :"+b.getIsbn());
		System.out.println();
		
		Car c = new Car("Ford", 2013, "silver", 54984894);
		c.print();

	}

}
