package telran.numbers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SetOperationsTest {

	Integer[] array = { 1, 2, 2, 3, 3, 4, 5 };
	int[] array1 = { 1, 2, 3, 5, 4 };
	int[] array2 = { 1, 2, 9, 3, 4, 8};

	@Test
	void testRemoveRepeated() {
		Integer[] expected = { 1, 2, 3, 4, 5 };
		Integer[] newArr = SetOperations.removeRepeated(array);
		assertEquals(expected.length, newArr.length);
		for (int i = 0; i < newArr.length; i++) {
			assertEquals(expected[i], newArr[i]);
		}

	}

	@Test
	void testIntersection() throws Exception {
		int[] expected = { 1, 2, 3, 4 };
		try {
			int[] newArr = SetOperations.intersection(array1, array2);
			assertEquals(expected.length, newArr.length);
			for (int i = 0; i < newArr.length; i++) {
				assertEquals(expected[i], newArr[i]);
			}
		} catch (Exception e) {
			System.out.println("Intersection method " + e);
		}
	}

	@Test
	void testHasRepeated() {
		int ar[] = { 1, 2, 2, 3};
		assertTrue(SetOperations.hasRepeated(ar));
		assertFalse(SetOperations.hasRepeated(array1));
	}

	@Test
	void testUnion() throws Exception{
		int[] expected = { 1, 2, 3, 5, 4, 9, 8};
		try {
			int[] newArr = SetOperations.union(array1, array2);
			assertEquals(expected.length, newArr.length);
			for (int i = 0; i < newArr.length; i++) {
				assertEquals(expected[i], newArr[i]);
			}
		} catch (Exception e) {
			System.out.println("Union method " + e);
		}
	}

	@Test
	void testSubtraction()throws Exception {
		int[] expected = { 5 };
		try {
			int[] newArr = SetOperations.subtraction(array1, array2);
			assertEquals(expected.length, newArr.length);
			for (int i = 0; i < newArr.length; i++) {
				assertEquals(expected[i], newArr[i]);
			}
		} catch (Exception e) {
			System.out.println("Subtraction method " + e);
		}
	}
	@Test
	void testSum() {
		int[] ar = { 1, 2, 3, 4, 5 };
		int num = 9;
	
		assertTrue(SetOperations.isSumOfTwoElements(ar, num));

	}
}
