package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.LinkedList;

class LinkedListTest {

	int [] arrayInt = {10, -7, 20, 9, 13, 18};
	String [] arrayStr = {"abc", "def", "www"};
	LinkedList<Integer> listInt;
	LinkedList<String>listStr;
	@BeforeEach
	void setUp() {
		listInt = new LinkedList<>();
		listStr = new LinkedList<>();
for(int i = 0; i < arrayStr.length; i++) {
	listStr.add(arrayStr[i]);
	
}
for(int i = 0; i < arrayInt.length; i++) {
	listInt.add(arrayInt[i]);
	
}

	}
	
	@Test
	void testAddGet() {
	assertEquals(arrayInt.length, listInt.size());
	assertEquals(arrayStr.length, listStr.size());
	for(int i = 0; i < arrayInt.length; i++) {
		assertEquals(arrayInt[i], listInt.get(i));
	}
	for(int i = 0; i < arrayStr.length; i++) {
		assertEquals(arrayStr[i], listStr.get(i));
	}
	}
	@Test
	void testAddOnIndex() {
		
		int expArrayInt []=  {15, 10, -7, 20, 9, 13, 18};
		String expArrayStr [] = {"abc", "def", "Hi", "www"};
		boolean check = listInt.add(0, 15);
		if(check!=false) {
		assertEquals(expArrayInt.length, listInt.size());
		for(int i = 0 ; i < expArrayInt.length; i++) {
			assertEquals(expArrayInt[i], listInt.get(i));
		}
		}else {
			System.out.println("Wrong parameter index");
		}
		check = listStr.add(2, "Hi");
		if(check!=false) {
		assertEquals(expArrayStr.length, listStr.size());
			for(int i = 0; i < expArrayStr.length; i++) {
				assertEquals(expArrayStr[i], listStr.get(i));
				
		}
		}else {
			System.out.println("Wrong parameter index");
	}
	

}
	@Test
	void testRemoveOnIndex() {
		
		int expArrayInt []=  {10, 20, 9, 13, 18};
		String expArrayStr [] = {"abc", "www"};
	
		int index = 1;
		Integer removedElementInt = listInt.remove(index);
		if(removedElementInt!=null) {
			assertEquals(-7,removedElementInt);
			assertEquals(expArrayInt.length, listInt.size());
			for(int i = 0; i < expArrayInt.length; i++) {
			assertEquals(expArrayInt[i], listInt.get(i));
		}
		}else {
			System.out.println("Wrong parameter index");
		}
		
		String removedElementStr = listStr.remove(index);
		if(removedElementStr!=null)  {
			assertEquals("def",removedElementInt);
			assertEquals(expArrayStr.length, listStr.size());
		
			for(int i = 0; i < expArrayStr.length; i++) {
				assertEquals(expArrayStr[i], listStr.get(i));
				
		}
		}else {
			System.out.println("Wrong parameter index");
	

}
	}
	}
