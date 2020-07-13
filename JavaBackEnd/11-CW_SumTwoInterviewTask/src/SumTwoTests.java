import static org.junit.jupiter.api.Assertions.*;

//import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Test;

class SumTwoTests {

	@Test
	void testSolution() {
		short [] array = {1, 2, 3, 4, 5, 5, 10};
		assertTrue(hasSumTwo(array,10));
		assertTrue(hasSumTwo(array,5));
		assertFalse(hasSumTwo(array,16));
		assertFalse(hasSumTwo(array,1));
 	}

	private Boolean hasSumTwo(short[] array, int sum) {
//	complexity O[N^2]
//		for(int i = 0; i < array.length; i++) {
//			for(int j = 0; j < array.length; j++) {
//				if(i != j && array[j] == sum - array[i]) {
//					System.out.println(array[i]+" + "+array[j] + " = " + sum);
//					return true;
//				}
//			}
//		}
//		return false;
		
//		complexity O[N]
		boolean []helper = new boolean [sum+1];//index of helper is short number from array,
		//value of helper is true (number matching the index exist) or false (other ways)
		//helper[10000] = true means array contain 10000
		for(int i = 0; i < array.length; i++) {
			int helperIndex = sum - array[i];
			if(helperIndex < 0) {
				continue;
			}
			if(helper[sum-array[i]]) {
				return true;
			}
			helper[array[i]] = true;
		}
		return false;
	}

}
