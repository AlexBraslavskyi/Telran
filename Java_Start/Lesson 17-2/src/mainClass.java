
public class mainClass {

	public static void main(String[] args) {
	System.out.println("Main class");
	double res = AnotherClass.calculator (2.5,5.3,3);
	System.out.println("Res = " + res);
	boolean a = AnotherClass.hasDigit(23478,5);
	if(a==true)
	System.out.println("Found");
	else
		System.out.println("Not found");
	}

}
