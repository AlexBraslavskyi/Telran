package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.LinkedList;

class LinkedListTest {

	int [] arrayInt = {10, -7, 20, 9, 13, 18};
	String [] arrayStr = {"abc", "def", "www"};
	LinkedList<Integer> listIntFirst;
	LinkedList<Integer> listIntLast;
	LinkedList<Integer> listIntMid;
	LinkedList<String>listStr;
	@BeforeEach
	void setUp() {
		listIntFirst = new LinkedList<>();
		listIntLast = new LinkedList<>();
		listIntMid = new LinkedList<>();
		listStr = new LinkedList<>();
for(int i = 0; i < arrayStr.length; i++) {
	listStr.add(arrayStr[i]);
	
}
for(int i = 0; i < arrayInt.length; i++) {
	listIntFirst.add(arrayInt[i]);
	listIntLast.add(arrayInt[i]);
	listIntMid.add(arrayInt[i]);

}

	}
	
	@Test
	void testAddGet() {
	assertEquals(arrayInt.length, listIntLast.size());
	assertEquals(arrayStr.length, listStr.size());
	for(int i = 0; i < arrayInt.length; i++) {
		assertEquals(arrayInt[i], listIntLast.get(i));
	}
	for(int i = 0; i < arrayStr.length; i++) {
		assertEquals(arrayStr[i], listStr.get(i));
	}
	}
	@Test
	void testAddOnIndex() {
		int expArrayIntMid []=   {10, -7, 20, 9, 15, 13, 18};
		int expArrayIntFirst []= {15, 10, -7, 20, 9, 13, 18};
//		int expArrayIntLast []=  {10, -7, 20, 9, 13, 18, 15};
		String expArrayStr [] = {"Hi", "abc", "def", "www"};
		boolean check1 = listIntMid.add(4, 15);
		boolean check2 = listIntFirst.add(0, 15);
//		boolean check3 = listIntLast.add(6, 15);
		if(check1 != false && check2 != false) {
		assertEquals(expArrayIntMid.length, listIntMid.size());
		assertEquals(expArrayIntFirst.length, listIntFirst.size());
//		assertEquals(expArrayIntLast.length, listIntLast.size());
		for(int i = 0 ; i < expArrayIntMid.length; i++) {
			assertEquals(expArrayIntMid[i], listIntMid.get(i));
//			assertEquals(expArrayIntLast[i], listIntLast.get(i));
			assertEquals(expArrayIntFirst[i], listIntFirst.get(i));

		}
		}
		else {
			System.out.println("Wrong parameter index");
		}
		check1 = listStr.add(0, "Hi");
		if(check1!=false) {
		assertEquals(expArrayStr.length, listStr.size());
			for(int i = 0; i < expArrayStr.length; i++) {
				assertEquals(expArrayStr[i], listStr.get(i));
		
		}
			assertFalse(listIntMid.add(-1, 10));
			assertFalse(listIntLast.add(100,10));
		}else {
			System.out.println("Wrong parameter index");
	}
	

}
	@Test
	void testRemoveOnIndex() {
		
		int expListIntMid []=  {10,-7, 20, 13, 18};
		int expListIntFirst []=  {-7, 20, 9, 13, 18};
		int expListIntLast []=  {10, -7, 20, 9, 13};
		String expListStr [] = {"abc", "def"};
	
		int index = 2;
		Integer removedElementIntMid = listIntMid.remove(3);
		listIntFirst.remove(0);
		listIntLast.remove(6);
		if(removedElementIntMid!=null) {
			assertEquals(9,removedElementIntMid);
			assertEquals(expListIntMid.length, listIntMid.size());
			for(int i = 0; i < expListIntMid.length; i++) {
//				System.out.print(" " + listIntMid.get(i));
			assertEquals(expListIntMid[i], listIntMid.get(i));
			assertEquals(expListIntFirst[i], listIntFirst.get(i));
			assertEquals(expListIntLast[i], listIntLast.get(i));
		
			
		}
		}else {
			System.out.println("Wrong parameter Int index");
		}

		String removedElementStr = listStr.remove(index);
		if(removedElementStr!=null)  {
			assertEquals("www",removedElementStr);
			assertEquals(expListStr.length, listStr.size());
			for(int i = 0; i < expListStr.length; i++) {
				assertEquals(expListStr[i], listStr.get(i));
		}
			assertNull(listIntMid.remove(-1));
			assertNull(listIntFirst.remove(555));
		}else {
			System.out.println("Wrong parameter Str index ");
	

}
	}
	}
