
public class ArrayMethods {
	
	public static void printArrayReverse(int[] array) {
		for (int i = array.length - 1; i >= 0; i--)
			System.out.print("["+array[i]+"] ");

	}

	public static int arrayEvensSum(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 == 0) {
				sum += array[i];
			}
		}
		return sum;

	}

	public static void sortBubbleDec(int[] array) {
		int res;
		do {
			res = sortToMin(array);
		} while (res != 0);
		printArray(array);
	}

	static int sortToMin(int[] array) {
		int flag = 0, temp;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i + 1] > array[i]) {
				temp = array[i];
				array[i] = array[i + 1];
				array[i + 1] = temp;
				flag = 1;
			}
		}
		return flag;
	}

	public static int searchMax(int[] array) {
		int indMax = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[indMax]) {
				indMax = i;
			}
		}
		return array[indMax];
	}


public static void printArray(int [] array) {
	
	for(int i = 0;i<array.length;i++) {
		System.out.print("[" + array[i] + "] ");
	}
	System.out.println();
}

}
