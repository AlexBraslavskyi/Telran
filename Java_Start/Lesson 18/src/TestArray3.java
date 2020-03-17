
public class TestArray3 {

	public static void main(String[] args) {
int[] ar = {9,1,5,2,7,4};
ArrayMethods.sortBubble(ar);
ArrayMethods.printArray(ar);
int res = ArrayMethods.arraySum(ar);
	System.out.println("Sum array = " + res);
	}

}
