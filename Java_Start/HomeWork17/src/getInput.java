import java.util.Scanner;

public class getInput {

	
	public static int[] getInput () {
		int [] array;
		String s = "y";
		int num;
	       System.out.println("Enter number:");
	       for(array.length = 0; s!="n";array.length++) {
	       Scanner scan = new Scanner(System.in);
	       num = scan.nextInt();
	       array [array.length] = num;
	       System.out.println("Do you want to enter more nombers - y/n:");
	       Scanner scan = new Scanner(System.in);
	       s = scan.nextLine();
	       }
	       if(s == "n")
		return array;	
	}
}
