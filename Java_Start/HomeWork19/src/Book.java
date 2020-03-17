
public class Book {

	
		private String autor;
		private String title;
		private int pages;
		private int isbn;
		
		public Book() {
			
	}
		public void setAutor(String s) {
		if (s !=null)
			autor = s;
		else
			System.err.println("Error: wronge autor");
		}
		public void setTitle(String s) {
			if (s !=null)
				title = s;
			else
				System.err.println("Error: wronge title");
			}
		public void setPages(int i) {
			if (i !=0)
				pages = i;
			else
				System.err.println("Error: wronge numbers peges");
			}
		
		public void setIsbn(int i) {
			if (i !=0)
				isbn = i;
			else
				System.err.println("Error: wronge number isbn");
			}
		
		public String getAutor() {
			return autor;
		}
		public String getTitle() {
			return title;
		}
		public int getPages() {
			return pages;
		}
		public int getIsbn() {
			return isbn;
		}
}
