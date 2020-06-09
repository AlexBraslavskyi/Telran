package telran.numbers;

public class Calculator {

	
	public static int sum(int n1, int n2) {
		
		return n1+n2;
	}
	public static int subtract(int n1, int n2) {
		return n1 -n2;
	}
	public static int divide(int n1, int n2) {
		
		return n1/n2;
	}
	public static int multiply(int n1, int n2) {
		
		return n1*n2;
	}
	
	public static int digits(int number) {
		int count;
		if (number == 0) {
			count = 1;
			return count;
		} else {
		for (count = 0; number != 0; count++)
			number = number / 10;
		return count;
		
	}
	}
	public static int pow(int a, int b) {
		int res = 0;
		if (a >= 0 && b == 0) {
			res = 1;
		return res;
	}else {
			for (res = 1; b > 0; b--)
				res = res * a;
	}
	
		return res;
	}

	
}
