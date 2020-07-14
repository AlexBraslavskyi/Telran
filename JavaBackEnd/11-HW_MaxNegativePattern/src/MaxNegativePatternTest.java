import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MaxNegativePatternTest {

	@Test
	void testSolution() {
		short [] array1 = {1, -32767, 10, -7, 3, 7, -3, -10, 32767};
		short [] array2 = {1, 10, 10, -8, -3};
		assertEquals(32767, maxNegativePattern(array1));
		assertEquals(-1, maxNegativePattern(array2));
	}

	private int maxNegativePattern(short[] array) {
		short [] checkArray = new short [Short.MAX_VALUE+1];
		short maxNegPattern = -1;
		for(int i = 0; i < array.length; i++) {
			if(checkArray[Math.abs(array[i])] == -array[i] && Math.abs(array[i]) > maxNegPattern) {
				maxNegPattern = (short)Math.abs(array[i]);
			}else {
			checkArray[Math.abs(array[i])] = array[i];
		}
		}
return maxNegPattern;
}
}
