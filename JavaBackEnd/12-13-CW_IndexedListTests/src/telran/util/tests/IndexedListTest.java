package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.*;

class IndexedListTest {

	int[] arrayInt = { 10, -7, 20, 10, 9, 13, 18 };
	int[] arrayTest = { 1, 3, 2, 3, 3, 5, 4 };
	String[] arrayStr = { "abc", "def", "www", "qww", "www", "eee" };

	IndexedList<Integer> listIntFirst;
	IndexedList<Integer> listInt;
	IndexedList<Integer> listIntMid;
	IndexedList<String> listStr;
	IndexedList<Integer> patternInt;
	IndexedList<String> patternStr;

	@BeforeEach
	void setUp() {
		// for array
//		listIntFirst = new Array<>();
//		listInt = new Array<>();
//		listIntMid = new Array<>();
//		listStr = new Array<>(); 
//		patternInt = new Array<>();
//		patternStr = new Array<>();

		// for linkedList

		listIntFirst = new LinkedList<>();
		listInt = new LinkedList<>();
		listIntMid = new LinkedList<>();
		listStr = new LinkedList<>();
		patternInt = new LinkedList<>();
		patternStr = new LinkedList<>();

		for (int i = 0; i < arrayStr.length; i++) {
			listStr.add(arrayStr[i]);

		}

		for (int i = 0; i < arrayInt.length; i++) {
			listIntFirst.add(arrayInt[i]);
			listInt.add(arrayInt[i]);
			listIntMid.add(arrayInt[i]);

		}

		patternInt.add(10);
		patternInt.add(13);
		patternInt.add(18);
		patternStr.add("www");
		patternStr.add("abc");
	}

	@Test
	void testAddGet() {
		assertEquals(arrayInt.length, listInt.size());
		assertEquals(arrayStr.length, listStr.size());
		assertNull(listInt.get(-1));
		assertNull(listInt.get(500));
		for (int i = 0; i < arrayInt.length; i++) {
			assertEquals(arrayInt[i], listInt.get(i));
		}
		for (int i = 0; i < arrayStr.length; i++) {
			assertEquals(arrayStr[i], listStr.get(i));
		}
	}

	@Test
	void testAddOnIndex() {
		int expListIntMid[] = { 10, -7, 20, 10, 9, 15, 13, 18 };
		int expListIntFirst[] = { 15, 10, -7, 20, 10, 9, 13, 18 };
		String expListStr[] = { "Hi", "abc", "def", "www", "qww", "www", "eee" };
		listIntMid.add(5, 15);
		listIntFirst.add(0, 15);
		assertFalse(listIntFirst.add(-1, 10));
		assertEquals(expListIntMid.length, listIntMid.size());
		listStr.add(0, "Hi");
		assertFalse(listStr.add(100, "qqq"));
		assertEquals(expListStr.length, listStr.size());
		testArrayListInt(expListIntMid, listIntMid);
		testArrayListInt(expListIntFirst, listIntFirst);
		testArrayListStr(expListStr, listStr);
		assertFalse(listIntMid.add(-1, 10));
		assertFalse(listInt.add(100, 10));

	}

	@Test
	void testRemoveOnIndex() {
		int expListIntMid[] = { 10, -7, 20, 10, 13, 18 };
		int expListIntFirst[] = { -7, 20, 10, 9, 13, 18 };
		int expListIntLast[] = { 10, -7, 20, 10, 9, 13 };
		String expListStr[] = { "abc", "def", "qww", "www", "eee" };
		int index = 2;
		listIntFirst.remove(0);
		listInt.remove(6);
		assertEquals(9, listIntMid.remove(4));
		assertEquals(expListIntMid.length, listIntMid.size());
		assertNull(listStr.remove(-10));
		assertEquals("www", listStr.remove(index));
		assertEquals(expListStr.length, listStr.size());
		testArrayListInt(expListIntMid, listIntMid);
		testArrayListInt(expListIntFirst, listIntFirst);
		testArrayListInt(expListIntLast, listInt);
		testArrayListStr(expListStr, listStr);
		assertNull(listIntMid.remove(-1));
		assertNull(listIntFirst.remove(555));

	}

	@Test
	void testReverse() {
		int expListInt[] = { 18, 13, 9, 10, 20, -7, 10 };
		String expListStr[] = { "eee", "www", "qww", "www", "def", "abc" };
		listInt.reverse();
		listStr.reverse();
		assertEquals(arrayInt.length, listInt.size());
		assertEquals(arrayStr.length, listStr.size());
		testArrayListInt(expListInt, listInt);
		testArrayListStr(expListStr, listStr);
	}

	@Test
	void testRemoveOnValue() {
		int expListIntMid[] = { 10, -7, 20, 10, 13, 18 };
		int expListIntFirst[] = { -7, 20, 10, 9, 13, 18 };
		int expListIntLast[] = { 10, -7, 20, 10, 9, 13 };
		String expListStr[] = { "abc", "def", "qww", "www", "eee" };
		listIntFirst.remove((Object) (10));
		listInt.remove((Object) (18));
		assertEquals(9, listIntMid.remove((Object) (9)));
		assertEquals(expListIntMid.length, listIntMid.size());
		testArrayListInt(expListIntMid, listIntMid);
		testArrayListInt(expListIntFirst, listIntFirst);
		testArrayListInt(expListIntLast, listInt);
		assertNull(listStr.remove(-10));
		assertEquals("www", listStr.remove("www"));
		assertEquals(expListStr.length, listStr.size());
		testArrayListStr(expListStr, listStr);
		assertNull(listIntMid.remove(-1));
		assertNull(listIntFirst.remove(555));

	}

	@Test
	void testContains() {
		assertTrue(listInt.contains(10));
		assertTrue(listInt.contains(13));
		assertFalse(listInt.contains(100));
		assertFalse(listInt.contains(-20));
	}

	@Test
	void testRemoveAll() {
		listInt.sort(new IntegerComparator());
		int expListInt[] = { -7, 9, 20 };
		String expListStr[] = { "def", "qww", "eee" };
		assertTrue(listInt.removeAll(patternInt));
		assertEquals(expListInt.length, listInt.size());
		testArrayListInt(expListInt, listInt);
		assertTrue(listStr.removeAll(patternStr));
		assertEquals(expListStr.length, listStr.size());
		testArrayListStr(expListStr, listStr);
		assertTrue(listInt.removeAll(listInt));
		assertFalse(listInt.removeAll(new Array<Integer>()));

	}

	@Test
	void testRetainAll() {
		int expListInt[] = { 10, 10, 13, 18 };
		String expListStr[] = { "abc", "www", "www" };
		listInt.add(1, 2);
		assertTrue(listInt.retainAll(patternInt));
		assertEquals(expListInt.length, listInt.size());
		testArrayListInt(expListInt, listInt);
		assertTrue(listStr.retainAll(patternStr));
		assertEquals(expListStr.length, listStr.size());
		testArrayListStr(expListStr, listStr);
		assertTrue(listInt.removeAll(listInt));
		assertFalse(listInt.removeAll(new Array<Integer>()));
	}

	@Test
	void testIndexOf() {
		assertEquals(4, listInt.indexOf(9));
		assertEquals(2, listStr.indexOf("www"));
		assertEquals(-1, listStr.indexOf("Alex"));
		assertEquals(-1, listInt.indexOf(125));
		listInt.sort(new IntegerComparator());
		assertEquals(2, listInt.indexOf(10));
		assertEquals(-1, listInt.indexOf(121));
		for (int i = 0; i < 5; i++) {
			listInt.add(3, 2);
		}
		listInt.sort(new IntegerComparator());
		assertEquals(1, listInt.indexOf(2));
		assertEquals(5, listInt.lastIndexOf(2));
		listStr.add("abc");
		listStr.sort(new StringComparator());
		assertEquals(5, listStr.indexOf("www"));
		assertEquals(6, listStr.lastIndexOf("www"));
		assertEquals(0, listStr.indexOf("abc"));
		assertEquals(1, listStr.lastIndexOf("abc"));
	}

	@Test
	void testLastIndexOf() {

		assertEquals(3, listInt.lastIndexOf(10));
		assertEquals(4, listStr.lastIndexOf("www"));
		assertEquals(-1, listStr.lastIndexOf("Alex"));
		assertEquals(-1, listInt.lastIndexOf(125));
		listInt.sort(new IntegerComparator());
		assertEquals(3, listInt.lastIndexOf(10));
	}

	@Test
	void testSorting() {
		for (int i = 0; i < 10; i++) {
			listInt.add((int) (Math.random() * 1000));

		}
		listInt.sort(new IntegerComparator());
		Iterator<Integer> it = listInt.iterator();
		int prev = it.next();
		while (it.hasNext()) {
			int current = it.next();
			assertTrue(prev <= current);
			prev = current;
		}
	}

	@Test
	void testIterator() {
		testArrayListInt(arrayInt, listInt);
		testArrayListStr(arrayStr, listStr);
	}

	private void testArrayListInt(int[] expected, IndexedList<Integer> list) {
		int[] actual = new int[list.size()];
		int index = 0;
		for (int num : list) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
	}

	private void testArrayListStr(String[] expected, IndexedList<String> list) {
		String[] actual = new String[list.size()];
		int index = 0;
		for (String num : list) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);

	}
	
	@Test
	void testRemoveIf() {
		int nNumbers = 1000;
		for(int i = 0; i < nNumbers; i++) {
			listInt.add((int) (Math.random()*100));
		}
		Predicate<Integer> predicate = new EvenNumbersPredicate();
		assertTrue(listInt.removeIf(predicate));
		assertFalse(listInt.removeIf(predicate));
		
		for(int num: listInt) {
			assertTrue(num %2 != 0);
//			assertTrue(predicate.negate().test(num));
//			assertFalse(predicate.test(num));
		}
	}
	@Test
	void testRemoveIfExpected() {
		int expected[] = { -7, 9, 13, 18, 3 };
		listInt.add(15);
		listInt.add(15);
		listInt.add(3);
		listInt.add(20);

		Predicate<Integer> predicate = new DividorPredicate(5);
		listInt.removeIf(predicate);
		testArrayListInt(expected, listInt);
		}

}
