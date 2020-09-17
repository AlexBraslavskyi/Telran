import java.util.Arrays;

public class RecursionTestAppl {

	public static void main(String[] args) {
	
//		System.out.println(factorial(7));
//System.out.println(multiply(10,20));
//		System.out.println(sum(new int[] {1,2,3,4,5}));
//		displayArrInReverse(new int[] {1,2,3,4,5});
		 int[]ar =  {1,2,3,4,5};
		 reverse(ar);
		System.out.println(Arrays.toString(ar));
	}

	private static int multiply(int a, int b) {
		if(b ==0) {
			return 0;
		}
		if(b < 0) {
			return -multiply(a, - b);
		}
		return a + multiply(a, b - 1);
	}

	private static long factorial (int n) {
		if(n < 0) {
			throw new IllegalAccessError("n can't be negativ number");
		}
		if(n == 0) {
			return 1;
		}
		
		return n * factorial(n-1);	
		
	}
	
	/* a 
	 * b positive numb
	 * a^b
	 * throw Illigal
	 * limitation : no *, /, loop, additional functions 
	 */
	public static long pow (int a, int b) {
		//TODO
		return 0;
	}
	
	/**
	 * 
	 * @param x
	 * @return x^2
	 * limitation no *,/, additional function, no loop
	 */
	public static int square (int x) {
		//TODO
		return 0;
	}
	
	public static int sum(int[] ar) {
		
		return sum(0, ar);
	}

	private static int sum(int index, int[] ar) {

		if(index == ar.length) {
			return 0;
		}
		
		return ar[index] + sum(index + 1, ar);
	}
	
	public static void displayArrInReverse(int ar[]) {
	
			printIndex(0, ar);	
				
	}

	private static void printIndex(int index, int[] ar) {

		if(index < ar.length) {
			printIndex(index +1, ar);
		System.out.print(" " + ar[index]);
	}
	}
	
	public static void reverse (int ar[]) {
		reverse(0, ar.length-1,ar);
		
		
	}

	private static void reverse(int left, int right, int[] ar) {
		if(left < right) {
			int tmp = ar[right];  // sum = left + right
			ar[right] = ar[left];
			ar[left] = tmp;
			reverse(left + 1, right -1, ar);
		}
		
	}
}
