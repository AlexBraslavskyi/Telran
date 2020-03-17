
public class MainPerson {

	public static void main(String[] args) {
		
		Person pr = new Person();
		pr.setName("Elisey");
		pr.setSurName("King");
		pr.setAge(19);
		pr.setId(232656);
		pr.display();
		
		Person pr1 = new Person();
		pr1.setName("Eseniya");
		pr1.setSurName("Queen");
		pr1.setAge(18);
		pr1.setId(54654564);
		System.out.println(pr1.getName());
		System.out.println(pr1.getSurName());
		System.out.println(pr1.getAge());
		System.out.println(pr1.getId());
		
		Person pr3 = new Person("Ben", "Gurion", 80, 23245545);
		System.out.println(pr3.getName());
		System.out.println(pr3.getSurName());
		System.out.println(pr3.getAge());
		System.out.println(pr3.getId());
	}
}
