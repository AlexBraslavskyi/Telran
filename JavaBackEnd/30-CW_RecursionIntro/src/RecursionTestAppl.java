public class RecursionTestAppl {

	public static void main(String[] args) {

//		System.out.println(factorial(7));
//      System.out.println(multiply(100,100));
//		System.out.println(sum(new int[] {1,2,3,4,5}));
//		displayArrInReverse(new int[] {1,2,3,4,5});
//		 int[]ar =  {1,2,3,4,5};
//		 reverse(ar);
//		System.out.println(Arrays.toString(ar));
//		System.out.println(pow(6,7));
//		System.out.println(square(5));
		System.out.println(isSubstring("hello", "hel"));
	}

	private static long multiply(long a, long b) {
		if (b == 0) {
			return 0;
		}
		if (b < 0) {
			return -multiply(a, -b);
		}
		return a + multiply(a, b - 1);
	}

	private static long factorial(int n) {
		if (n < 0) {
			throw new IllegalAccessError("n can't be negativ number");
		}
		if (n == 0) {
			return 1;
		}

		return n * factorial(n - 1);

	}

	public static int sum(int[] ar) {

		return sum(0, ar);
	}

	private static int sum(int index, int[] ar) {

		if (index == ar.length) {
			return 0;
		}

		return ar[index] + sum(index + 1, ar);
	}

	public static void displayArrInReverse(int ar[]) {

		printIndex(0, ar);

	}

	private static void printIndex(int index, int[] ar) {

		if (index < ar.length) {
			printIndex(index + 1, ar);
			System.out.print(" " + ar[index]);
		}
	}

	public static void reverse(int ar[]) {
		reverse(0, ar.length - 1, ar);

	}

	private static void reverse(int left, int right, int[] ar) {
		if (left < right) {
			int tmp = ar[right]; // sum = left + right
			ar[right] = ar[left];
			ar[left] = tmp;
			reverse(left + 1, right - 1, ar);
		}

	}

	// Additional task: write function boolean isSubstring (String string, String
	// substring)
//	that returns true if a given 'substring' is indeed the substring of a given 'string'. 
//	Challenges: 1. To apply only following methods of the class String: charAt(int ind); 
//	String substring(int ind); int length(); 
//	2. No cycles; 
//	3. No static fields
	/**
	 * 
	 * @param string
	 * @param substring
	 * @return true if the second parameter is a substring of the string given in
	 *         the first parameter limitations: You may apply the following methods
	 *         of the class charAt(int ind); String substring(int ind); int
	 *         length(); 2. No cycles; 3. No static fields
	 */

	public static boolean isSubstring(String string, String substr) {

		if (substr.length() == 0)
			return true;
		if (string.length() < substr.length())
			return false;
		if (string.charAt(0) == substr.charAt(0)&&isPrefix(string, substr))
			return true;
					
		return isSubstring(string.substring(1), substr);
	}

	private static boolean isPrefix(String s1, String s2) {
		if (s2.length() == 0)
			return true;
		if (s1.charAt(0) != s2.charAt(0))
			return false;
		return isPrefix(s1.substring(1), s2.substring(1));
	}

	/**
	 * 
	 * @param a
	 * @param b positive number
	 * @return a^b throws IllegalArgumentException if b is a negative limitations:
	 *         no operator *; no operator /; no cycles; no additional functions
	 *         applying above limitations; no standard functions
	 */
	public static long pow(int a, int b) {
		if (b == 0) {
			return 1;
		}
		if (b < 0) {
			throw new IllegalAccessError("b can't be negativ number");
		}
		return multiply(a, (int) pow(a, (b - 1)));

	}

	/**
	 * 
	 * @param x
	 * @return x^2 limitations: no *, no /, no additional functions, no cycles
	 */
	public static int square(int x) {
		if (x == 0) {
			return 0;
		}
		if (x < 0) {
			return -(square(-x - 1) + (-(x + x) - 1));
		}
		return square(x - 1) + (x + x) - 1;
	}
}
