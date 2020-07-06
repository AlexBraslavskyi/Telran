package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayTestIndexOfReverse {

	int [] arrayInt = {10, -7, 20, 9, 13, 18};
	String [] arrayStr = {"abc", "def", "www"};
	double [] arrayDbl = {2.22, 3.33, 4.44, 5.55};
	Array<Integer> arInt;
	Array<String> arStr;
	Array<Double> arDbl;
	@BeforeEach
	void setUp() {
		arInt = new Array<>(3);
		arStr = new Array<>(2);
		arDbl = new Array<>();
for(int i = 0; i < arrayStr.length; i++) {
	arStr.add(arrayStr[i]);
	
}
for(int i = 0; i < arrayInt.length; i++) {
	arInt.add(arrayInt[i]);
	
}
for(int i = 0; i < arrayDbl.length; i++) {
	arDbl.add(arrayDbl[i]);
	
}	
	}
	@Test
	void testReverse() {
		int expArrayInt []=  {18, 13, 9, 20, -7, 10};
		String expArrayStr [] = {"www", "def", "abc"};
		arInt.reverse();
		arStr.reverse();
	assertEquals(arrayInt.length, arInt.size());
	assertEquals(arrayStr.length, arStr.size());
	for(int i = 0; i < arrayInt.length; i++) {
		assertEquals(arrayInt[i], arInt.get(i));
	}
	for(int i = 0; i < arrayStr.length; i++) {
		assertEquals(arrayStr[i], arStr.get(i));
	}
	}
	
//	@Test
//	void testAddGet() {
//	assertEquals(arrayInt.length, arInt.size());
//	assertEquals(arrayStr.length, arStr.size());
//	for(int i = 0; i < arrayInt.length; i++) {
//		assertEquals(arrayInt[i], arInt.get(i));
//	}
//	for(int i = 0; i < arrayStr.length; i++) {
//		assertEquals(arrayStr[i], arStr.get(i));
//	}
//	}
//
//	@Test
//	void testAddOnIndex() {
//		
//		int expArrayInt []=  {15, 10, -7, 20, 9, 13, 18};
//		String expArrayStr [] = {"abc", "def", "Hi", "www"};
//		double expArrayDbl[] = {2.22, 3.33, 4.44, 0.01, 5.55};
//		boolean check = arInt.add(0, 15);
//		if(check!=false) {
//		assertEquals(expArrayInt.length, arInt.size());
//		for(int i = 0 ; i < expArrayInt.length; i++) {
//			assertEquals(expArrayInt[i], arInt.get(i));
//		}
//		}else {
//			System.out.println("Wrong parameter index");
//		}
//		check = arStr.add(2, "Hi");
//		if(check!=false) {
//		assertEquals(expArrayStr.length, arStr.size());
//			for(int i = 0; i < expArrayStr.length; i++) {
//				assertEquals(expArrayStr[i], arStr.get(i));
//				
//		}
//		}else {
//			System.out.println("Wrong parameter index");
//	}
//		check = arDbl.add(3, 0.01);
//		if(check!=false) {
//		assertEquals(expArrayDbl.length, arDbl.size());
//			for(int i = 0; i < expArrayDbl.length; i++) {
//				assertEquals(expArrayDbl[i], arDbl.get(i));
//				
//		}
//		}else {
//			System.out.println("Wrong parameter index");
//	}
//
//}
//	@Test
//	void testRemoveOnIndex() {
//		
//		int expArrayInt []=  {10, 20, 9, 13, 18};
//		String expArrayStr [] = {"abc", "www"};
//		double expArrayDbl[] = {2.22, 4.44, 5.55};
//		int index = 1;
//		Integer j = arInt.remove(index);
//		assertEquals(-7,j);
//		if(j!=null) {
//			
//			
//		assertEquals(expArrayInt.length, arInt.size());
//		for(int i = 0; i < expArrayInt.length; i++) {
//			assertEquals(expArrayInt[i], arInt.get(i));
//		}
//		}else {
//			System.out.println("Wrong parameter index");
//		}
//		if(arStr.remove(index)!=null)  {
//		assertEquals(expArrayStr.length, arStr.size());
//		
//			for(int i = 0; i < expArrayStr.length; i++) {
//				assertEquals(expArrayStr[i], arStr.get(i));
//				
//		}
//		}else {
//			System.out.println("Wrong parameter index");
//	}
//		if(arDbl.remove(index)!=null)  {
//		assertEquals(expArrayDbl.length, arDbl.size());
//			for(int i = 0; i < expArrayDbl.length; i++) {
//				assertEquals(expArrayDbl[i], arDbl.get(i));
//				
//		}
//		}else {
//			System.out.println("Wrong parameter index");
//	}
//
//}
