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


}
