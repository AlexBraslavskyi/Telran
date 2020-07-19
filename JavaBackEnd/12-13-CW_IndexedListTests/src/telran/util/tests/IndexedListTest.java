package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.*;

class IndexedListTest {

	int[] arrayInt = { 10, -7, 20, 10, 9, 13, 18 };
	int[] arrayTest = {1,3,2,3,3,5,4};
	String[] arrayStr = { "abc", "def", "www", "qww", "www", "eee"};
	int[] arrayWillEmpty = { 1 };
	IndexedList<Integer> listWillEmpty;
	IndexedList<Integer> listIntFirst;
	IndexedList<Integer> listInt;
	IndexedList<Integer> listIntMid;
	IndexedList<String> listStr;
	IndexedList<Integer> patternInt;
	IndexedList<String> patternStr;
	IndexedList<Integer> testList;
	@BeforeEach
	void setUp() {
		//for array
//		listWillEmpty = new Array<>();
//		listIntFirst = new Array<>();
//		listInt = new Array<>();
//		listIntMid = new Array<>();
//		listStr = new Array<>(); 
//		patternInt = new Array<>();
//		patternStr = new Array<>();
//		testList = new Array<>();
		//for linkedList
		listWillEmpty = new LinkedList<>();
		listIntFirst = new LinkedList<>();
		listInt = new LinkedList<>();
		listIntMid = new LinkedList<>();
		listStr = new LinkedList<>(); 
		patternInt = new LinkedList<>();
		patternStr = new LinkedList<>();
		testList = new LinkedList<>();
		
		for (int i = 0; i < arrayStr.length; i++) {
			listStr.add(arrayStr[i]);

		}
		for (int i = 0; i < arrayTest.length; i++) {
			testList.add(arrayTest[i]);

		}
		for (int i = 0; i < arrayInt.length; i++) {
			listIntFirst.add(arrayInt[i]); 
			listInt.add(arrayInt[i]);
			listIntMid.add(arrayInt[i]);

		}
		for (int i = 0; i < arrayWillEmpty.length; i++) {
			listWillEmpty.add(arrayWillEmpty[i]);

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
		int expArrayIntMid[] = { 10, -7, 20, 10, 9, 15, 13, 18 };
		int expArrayIntFirst[] = { 15, 10, -7, 20, 10, 9, 13, 18 };
		String expArrayStr[] = { "Hi", "abc", "def", "www", "qww", "www", "eee" };
		listIntMid.add(5, 15);
		listIntFirst.add(0, 15);
		assertFalse(listIntFirst.add(-1, 10));
		assertEquals(expArrayIntMid.length, listIntMid.size());
		for (int i = 0; i < expArrayIntMid.length; i++) {
			assertEquals(expArrayIntMid[i], listIntMid.get(i));
			assertEquals(expArrayIntFirst[i], listIntFirst.get(i));

		}

		listStr.add(0, "Hi");
		assertFalse(listStr.add(100, "qqq"));
		assertEquals(expArrayStr.length, listStr.size());
		for (int i = 0; i < expArrayStr.length; i++) {
			assertEquals(expArrayStr[i], listStr.get(i));

		}
		assertFalse(listIntMid.add(-1, 10));
		assertFalse(listInt.add(100, 10));

	}

	@Test
	void testRemoveOnIndex() {
	
		int expListEmpty[] = {};
		int expListIntMid[] = { 10, -7, 20, 10, 13, 18 };
		int expListIntFirst[] = { -7, 20, 10, 9, 13, 18 };
		int expListIntLast[] = { 10, -7, 20, 10, 9, 13 };
		String expListStr[] = { "abc", "def", "qww", "www","eee" };


		int index = 2;
		listIntFirst.remove(0); 
		listInt.remove(6);
		assertEquals(9, listIntMid.remove(4));
		assertEquals(expListIntMid.length, listIntMid.size());
		for (int i = 0; i < expListIntMid.length; i++) {
			assertEquals(expListIntMid[i], listIntMid.get(i));
			assertEquals(expListIntFirst[i], listIntFirst.get(i));
			assertEquals(expListIntLast[i], listInt.get(i));

		}
		assertNull(listStr.remove(-10)); 
		assertEquals("www", listStr.remove(index));
		assertEquals(expListStr.length, listStr.size());
		for (int i = 0; i < expListStr.length; i++) {
			assertEquals(expListStr[i], listStr.get(i));
		}
		assertNull(listIntMid.remove(-1));
		assertNull(listIntFirst.remove(555));

	}

	@Test
	void testReverse() {
		int expArrayInt[] = { 18, 13, 9, 10, 20, -7, 10 };
		String expArrayStr[] = { "eee", "www", "qww", "www", "def", "abc" };
		listInt.reverse();
		listStr.reverse();
		assertEquals(arrayInt.length, listInt.size());
		assertEquals(arrayStr.length, listStr.size());
		for (int i = 0; i < arrayInt.length; i++) {
			assertEquals(expArrayInt[i], listInt.get(i));
		}
		for (int i = 0; i < arrayStr.length; i++) {
			assertEquals(expArrayStr[i], listStr.get(i)); 
		}
	}


	@Test
	void testRemoveOnValue() {
		int expListEmpty[] = {};
		int expListIntMid[] = { 10, -7, 20, 10, 13, 18 };
		int expListIntFirst[] = { -7, 20, 10, 9, 13, 18 };
		int expListIntLast[] = { 10, -7, 20, 10, 9, 13 };
		String expListStr[] = { "abc", "def", "qww", "www","eee" };

		listIntFirst.remove((Object)(10)); 
		listInt.remove((Object)(18));
		assertEquals(9,listIntMid.remove((Object)(9)));
		assertEquals(expListIntMid.length, listIntMid.size());
		for (int i = 0; i < expListIntMid.length; i++) {
			assertEquals(expListIntMid[i], listIntMid.get(i));
//			System.out.print(" " +listIntMid.get(i));
			assertEquals(expListIntFirst[i], listIntFirst.get(i));
			assertEquals(expListIntLast[i], listInt.get(i));

		}
		assertNull(listStr.remove(-10)); 
		assertEquals("www", listStr.remove("www"));
		assertEquals(expListStr.length, listStr.size());
		for (int i = 0; i < expListStr.length; i++) {
			assertEquals(expListStr[i], listStr.get(i));
		}
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
		int expListInt[] = {-7, 9, 20};
		String expListStr[] = {"def", "qww", "eee"};
		assertTrue(listInt.removeAll(patternInt));
		 
		assertEquals(expListInt.length, listInt.size());
		
		for (int i = 0; i < listInt.size(); i++) {
			assertEquals(expListInt[i], listInt.get(i));
//			System.out.print(" " +listInt.get(i));
		}
		assertTrue(listStr.removeAll(patternStr));
		assertEquals(expListStr.length, listStr.size());
		for (int i = 0; i < expListStr.length; i++) {
			assertEquals(expListStr[i], listStr.get(i));
		}
		assertTrue(listInt.removeAll(listInt));
		assertFalse(listInt.removeAll(new Array<Integer>()));
 
	}
	@Test
	void testRetainAll() {
		int expListInt[] = {10, 10, 13, 18};
		String expListStr[] = {"abc", "www", "www"};
		listInt.add(1, 2);
		assertTrue(listInt.retainAll(patternInt));
		assertEquals(expListInt.length, listInt.size());
		for (int i = 0; i < expListInt.length; i++) {
			assertEquals(expListInt[i], listInt.get(i));
//			System.out.print(" " +listInt.get(i));
		}

		assertTrue(listStr.retainAll(patternStr));
		assertEquals(expListStr.length, listStr.size());
		for (int i = 0; i < expListStr.length; i++) {
			assertEquals(expListStr[i], listStr.get(i));
		}
		assertTrue(listInt.removeAll(listInt));
		assertFalse(listInt.removeAll(new Array<Integer>()));
	}
	@Test
	void testSorting() {
		
		for(int i = 0; i < 10; i++) {
			listInt.add((int) (Math.random() * 1000));
			
		}
		listInt.sort(new IntegerComparator());
		int size = listInt.size()-1;
		for(int i = 0; i < size; i++) {
//	
			assertTrue(listInt.get(i) <= listInt.get(i + 1));//i+1 next.next
		}
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
		for(int i = 0; i < 5; i++) {
			listInt.add(3, 2);
//			listInt.add(2, -7);
//			listInt.add(1, 20);
		}
		listInt.sort(new IntegerComparator());
		testList.sort(new IntegerComparator());
		assertEquals(2, testList.indexOf(3));
		assertEquals(4, testList.lastIndexOf(3));
		assertEquals(1, listInt.indexOf(2));
		assertEquals(5, listInt.lastIndexOf(2));
//		assertEquals(0, listInt.indexOf(-7));
//		assertEquals(5, listInt.lastIndexOf(-7));
//		assertEquals(6, listInt.indexOf(20));
//		assertEquals(11, listInt.lastIndexOf(20));
		listStr.add("abc");
		int size = listInt.size(); 
		listStr.sort(new StringComparator());
		for(int i = 0; i < size; i++) {
			System.out.print(" " + listInt.get(i));;
		}
		
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
	void testIterator(){
		System.out.println();
		testArrayList(arrayInt,listInt);
		
		
	}

	private void testArrayList(int[] expected, IndexedList<Integer> list) {
		int [] actual = new int[list.size()];
		int index = 0;
		for(int num: list) {
			actual[index++] = num;
//			System.out.print(" " + num);
		}
		assertArrayEquals(expected,actual);
		
	}
}

