package CarProject;

public class MainClass {

	public static void main(String[] args) {
		
		Car c1 = new Car("Ford", 2013, "silver");
		Car c2 = new Car("Mazda", 2018, "black");
		Car c3 = new Car("Subaru", 2017, "white");
		Car[] cars = {c1,c2,c3};
		Garage g = new Garage("My garage", "Herzl 20", cars);
		
		System.out.println(g);
		Garage g2 = new Garage("My garage", "Herzl 20", cars);
		
		System.out.println(g2);
if(g.equals (g2))
	System.out.println("Garage equals");
else
	System.out.println("Garage not equals");

if(c1.equals (c3))
	System.out.println("Cars equals");
else
	System.out.println("Cars not equals");
	}
}
