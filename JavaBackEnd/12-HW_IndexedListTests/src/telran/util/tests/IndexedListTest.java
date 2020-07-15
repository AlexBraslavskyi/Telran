package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class IndexedListTest {

	int[] arrayInt = { 10, -7, 20, 9, 13, 18 };
	String[] arrayStr = { "abd", "lm", "hello" };
	IndexedList<Integer> listInt;
	IndexedList<String> listStr;

	@BeforeEach
	void setUp() {
//		listInt = new Array<>();
//		listStr = new Array<>();

		listInt = new LinkedList<>();
		listStr = new LinkedList<>();
		for (int i = 0; i < arrayInt.length; i++) {
			listInt.add(arrayInt[i]);
		}
		for (int i = 0; i < arrayStr.length; i++) {
			listStr.add(arrayStr[i]);
		}

	}

	@Test
	void testAddGet() {
		assertEquals(arrayInt.length, listInt.size());
		assertEquals(arrayStr.length, listStr.size());
		for (int i = 0; i < arrayInt.length; i++) {
			assertEquals(arrayInt[i], listInt.get(i));
		}
		for (int i = 0; i < arrayStr.length; i++) {
			assertEquals(arrayStr[i], listStr.get(i));
		}
	}

	@Test
	void testAddIndex() {
		int[] expected = { 10, -7, 20, 9, 22, 13, 18 };
		assertTrue(listInt.add(4, 22));
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], listInt.get(i));
		}
		assertFalse(listInt.add(-4, 22));
		assertFalse(listInt.add(400, 22));

	}

	@Test
	void testAddIndexHead() {
		int[] expected = { 22, 10, -7, 20, 9, 13, 18 };
		assertTrue(listInt.add(0, 22));
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], listInt.get(i));
		}
		assertFalse(listInt.add(-4, 22));
		assertFalse(listInt.add(400, 22));

	}

	@Test
	void testRemoveIndex() {
		int[] expected = { 10, -7, 9, 13, 18 };
		assertEquals(20, listInt.remove(2));
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], listInt.get(i));
		}
		assertNull(listInt.remove(-4));
		assertNull(listInt.remove(400));

	}

	@Test
	void testRemoveIndexHead() {
		int[] expected = { -7, 20, 9, 13, 18 };
		assertEquals(10, listInt.remove(0));
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], listInt.get(i));
		}
		assertNull(listInt.remove(-4));
		assertNull(listInt.remove(400));

	}

	@Test
	void testRemoveIndexTail() {
		int[] expected = { 10, -7, 20, 9, 13 };
		assertEquals(18, listInt.remove(5));
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], listInt.get(i));
		}
		assertNull(listInt.remove(-4));
		assertNull(listInt.remove(400));

	}

	@Test
	void testIndexOf() {
		assertEquals(2, listInt.indexOf(20));
		listInt.add(2, 20);
		assertEquals(3, listInt.lastIndexOf(20));
		assertEquals(-1, listInt.indexOf(100));
		assertEquals(-1, listInt.lastIndexOf(100));
	}

	@Test
	void testReverseEven() {
		int[] expected = { 18, 13, 9, 20, -7, 10 };
		listInt.reverse();
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], listInt.get(i));
		}

	}

	@Test
	void testReverseOdd() {
		listInt.remove(5);
		int[] expected = { 13, 9, 20, -7, 10 };
		listInt.reverse();
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], listInt.get(i));
		}

	}

	@Test
	void testRemoveObject() {
		int expected[] = { 10, 20, 9, 13, 18 };
		assertEquals(-7, listInt.remove((Integer) (-7)));
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], listInt.get(i));
		}
		assertNull(listInt.remove((Integer) (-7)));

	}

	@Test
	void testRemoveAll() {

		assertFalse(listInt.removeAll(new Array<Integer>()));
		assertTrue(listInt.removeAll(listInt));
		assertEquals(0, listInt.size());

	}

	@Test
	void testRetainAll() {
		assertFalse(listInt.retainAll(listInt));
		assertTrue(listInt.retainAll(new Array<Integer>()));

		assertEquals(0, listInt.size());

	}
	@Test
	void testContains() {
		assertTrue(listInt.contains(-7));
		assertFalse(listInt.contains(7));
	}

}
