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
		int count = 0;
		do {
			number /= 10;
			count++;
		}while(number!=0);
		return count;
		
	}
	public static int pow(int a, int b) {
		int res = 1;

			for (int i = 0; i<b; i++) {
				res *= a;
	}
		return res;
	}
}
