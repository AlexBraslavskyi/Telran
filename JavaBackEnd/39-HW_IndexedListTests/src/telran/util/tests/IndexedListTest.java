package telran.util.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Array;
import telran.util.IndexedList;

class IndexedListTest {

	int[] arrayInt = { 10, -7, 20, 10, 9, 13, 18 };
	String[] arrayStr = { "abc", "def", "www", "qww", "www", "eee"};
	int[] arrayWillEmpty = { 1 };
	static IndexedList<Integer> listWillEmpty;
	static IndexedList<Integer> listIntFirst;
	static IndexedList<Integer> listInt;
	static IndexedList<Integer> listIntMid;
	static IndexedList<String> listStr;
	static IndexedList<Integer> patternInt;
	static IndexedList<String> patternStr;
	static Constructor<?> constructor;
	

	@BeforeAll
	   static void Config() {
		String filePath = "src/TestConfig";
        String text = "";
		try {
			text = new String ( Files.readAllBytes( Paths.get(filePath) ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Class<?> clazz;
		try {
			clazz = Class.forName(text);
			constructor = clazz.getConstructor();
		      
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	@BeforeEach
	void setUp(){
		try {
			listWillEmpty = (IndexedList<Integer>) constructor.newInstance();
			listIntFirst = (IndexedList<Integer>) constructor.newInstance();
			listInt = (IndexedList<Integer>) constructor.newInstance();
			listIntMid = (IndexedList<Integer>) constructor.newInstance();
			listStr = (IndexedList<String>) constructor.newInstance();
			patternInt =(IndexedList<Integer>) constructor.newInstance();
			patternStr = (IndexedList<String>) constructor.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < arrayStr.length; i++) {
			listStr.add(arrayStr[i]);

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
	void testIndexOf() {
		assertEquals(4, listInt.indexOf(9));
		assertEquals(2, listStr.indexOf("www"));
		assertEquals(-1, listStr.indexOf("Alex"));
		assertEquals(-1, listInt.indexOf(125));
	}

	@Test
	void testLastIndexOf() {
		assertEquals(3, listInt.lastIndexOf(10));
		assertEquals(4, listStr.lastIndexOf("www"));
		assertEquals(-1, listStr.lastIndexOf("Alex"));
		assertEquals(-1, listInt.lastIndexOf(125));
	}
	@Test
	void testRemoveOnValue() {
		int expListEmpty[] = {};
		int expListIntMid[] = { 10, -7, 20, 10, 13, 18 };
		int expListIntFirst[] = { -7, 20, 10, 9, 13, 18 };
		int expListIntLast[] = { 10, -7, 20, 10, 9, 13 };
		String expListStr[] = { "abc", "def", "qww", "www","eee" };


//		Object patternMid = 9;
//		Object patternFirst = 10;
//		Object pattern = 18;
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
		int expListInt[] = {-7, 20, 9};
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
}
