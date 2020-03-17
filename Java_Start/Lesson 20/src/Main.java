
public class Main {

	public static void main(String[] args) {
	
		Car c = new Car();
		c.setModel("Ford");
		c.setYear(2016);
		c.setColor("red");
		System.out.println(c.getModel());

		Car c1 = new Car("BMW", 2013, "Black");
		c1.display();
		System.out.println();
		
		Car c3 = new Car(c1);
		c3.display();
		System.out.println();
		
		Track tr = new Track();
		tr.display();
		System.out.println();
		
		Track tr1 = new Track("Man", 2016, "black", 30);
		tr1.display();
		System.out.println();
		
		Ref r = new Ref("Daf", 2015, "white", 50, -50);
		r.display();
	}

}
