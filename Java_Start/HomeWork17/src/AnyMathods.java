import java.util.Scanner;
public class AnyMathods {
	public static int findNumber(int number, int subNumber) {
		if (number < 0)
			number = -number;
		if (subNumber < 0)
			subNumber = -subNumber;
		int count = 0;
		int dCount = digitsCount(subNumber);
		int div = XpowerY(10, dCount);
		int rem = 0;
		do {
			rem = number % div;
			if (rem == subNumber)
				count++;
			number = number / 10;

		} while (number != 0);
		return count;
	}
	public static int XpowerY(int x, int y) {
		int res = 0;
		if (x >= 0 && y == 0)
			res = 1;
		else if (x < 0 || y < 0)
			System.out.println("Task without negative numbers\n");
		else
			for (res = 1; y > 0; y--)
				res = res * x;
		return res;
	}
	public static int digitsCount(int number) {
		int count;
		if (number == 0) {
			count = 1;
			return count;
		} else if (number < 0)
			number = -number;
		for (count = 0; number != 0; count++)
			number = number / 10;
		return count;
	}
	public static void printStars(int stars, int inRow) {
		int count = inRow;
		if (stars <= 0 || inRow <= 0)
			System.out.println("Error: number must be >0\n");
		else {
			while (stars > 0) {
				System.out.print("*");
				inRow--;
				stars--;
				if (inRow == 0) {
					System.out.println();
					inRow = count;
				}
			}
		}
	}
	public static void rectangle(int size) {

		int v, h;
		for (v = 0; v < size; v++) {
			for (h = 0; h < size; h++) {
				if (v == 0 || h == 0 || v == size - 1 || h == size - 1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	public static int getInput () {
		Scanner scan = new Scanner(System.in);
	       System.out.println("¬ведите число:");
	       int res = scan.nextInt();
		
		return res;	
	}
}

