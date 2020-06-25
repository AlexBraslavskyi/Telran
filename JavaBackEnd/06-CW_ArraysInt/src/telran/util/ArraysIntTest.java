package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArraysIntTest {

	@Test
	void testAddAtEnd() {
		int ar[] = {10,2,7};
		int num = 100;
		int exp[] = {10,2,7,num};
		assertArrayEquals(exp, ArraysInt.add(ar,num));
	}

	@Test
	void testAddAtIndex() {
		int ar[] = {10,2,7};
		int num = 100;
		int index = 1;
		int exp[] = {10,num,2,7};
		assertArrayEquals(exp, ArraysInt.add(ar, num, index));
	}
	@Test
	void testRemoveLast() {
		int ar[] = {10,2,7};
        int exp[] = {10,2};
		assertArrayEquals(exp, ArraysInt.remove(ar));
	}
	@Test
	void testRemoveAtIndex() {
		int ar[] = {10,2,7};
		int index = 1;
        int exp[] = {10,7};
		assertArrayEquals(exp, ArraysInt.remove(ar,index));
	}

}
