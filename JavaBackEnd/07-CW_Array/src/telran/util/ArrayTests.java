package telran.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayTests {

	int [] arrayInt = {10,-7,20,9,13,18};
	String [] arrayStr = {"abc","def","www"};
	Array<Integer> arInt;
	Array<String> arStr;
	@BeforeEach
	void setUp() {
		arInt = new Array<>(3);
		arStr = new Array<>(2);
for(int i =0; i<arrayStr.length;i++) {
	arStr.add(arrayStr[i]);
	
}
for(int i =0; i<arrayInt.length;i++) {
	arInt.add(arrayInt[i]);
	
}
		
	}
	
	@Test
	void testAddGet() {
	assertEquals(arrayInt.length, arInt.size());
	assertEquals(arrayStr.length, arStr.size());
	for(int i = 0; i < arrayInt.length; i++) {
		assertEquals(arrayInt[i], arInt.get(i));
	}
	for(int i = 0; i < arrayStr.length; i++) {
		assertEquals(arrayStr[i], arStr.get(i));
	}
	}

	

}
