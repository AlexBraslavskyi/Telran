
public class AnotherClass {

	public static double calculator (double num1, double num2, int action){
		
		switch(action){
		case 1:return num1+num2;
		case 2:return num1-num2;
		case 3:return num1*num2;
		case 4:return num1/num2;
		}
		
		return 0;
	}
		
	public static boolean hasDigit(int number, int digit) {
		int rem;
		do{
			rem = number%10;
			if(rem == digit)
				return true;
			number = number/10;
		}while(number!=0);
		
		return false;
	}
}
