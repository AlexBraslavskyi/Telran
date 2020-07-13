import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MaxNegativePatternTest {

	@Test
	void testSolution() {
		short [] array1 = {1, 10, -7, 3, 7, -3, -10};//10
		short [] array2 = {1, 10, -8, -3};//-1
		assertEquals(10, maxNegativePattern(array1));
		assertEquals(-1, maxNegativePattern(array2));
	}

	private int maxNegativePattern(short[] array) {
		// TODO Auto-generated method stub
		return -1;
	}

}
