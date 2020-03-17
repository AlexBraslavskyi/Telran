
public class MainClass {

	public static void main(String[] args) {
		int [] array = {19,23,4,5,7,9,-8,0,78};
		System.out.println("Source array");
		ArrayMethods.printArray(array);
		System.out.println("Reverse array");
		ArrayMethods.printArrayReverse(array);
		System.out.println();
		int res = ArrayMethods.arrayEvensSum(array);
		System.out.println("Sum evens in array = "+ res);
		System.out.println("Sort array min to end");
		ArrayMethods.sortBubbleDec(array);
		res = ArrayMethods.searchMax(array);
		System.out.println("Max in array - " + res);
	}
}
