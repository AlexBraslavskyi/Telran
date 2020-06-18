package telran.text;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegularExpressionsTest {

	@Test
	void testRegex() {
//assertTrue("ad".matches(RegularExpressions.getTestRegex()));
//assertTrue("ahghg".matches(RegularExpressions.getTestRegex()));
////assertTrue("ba".matches(RegularExpressions.getTestRegex()));
//assertFalse("b".matches(RegularExpressions.getTestRegex()));
//assertFalse("c12".matches(RegularExpressions.getTestRegex()));
//assertTrue("c1".matches(RegularExpressions.getTestRegex()));
		assertTrue("123456".matches(RegularExpressions.getTestRegex()));
		assertTrue("1-2221-7".matches(RegularExpressions.getTestRegex()));
		assertFalse("1-2-2221-".matches(RegularExpressions.getTestRegex()));
		

	}
	
	
	@Test
	void testJavaVarieble(){
		assertTrue("$".matches(RegularExpressions.javaVariable()));
		assertTrue("q".matches(RegularExpressions.javaVariable()));
		assertFalse("-".matches(RegularExpressions.javaVariable()));
		assertTrue("__".matches(RegularExpressions.javaVariable()));
		assertTrue("gsg11bb$".matches(RegularExpressions.javaVariable()));
		assertFalse("ggg 22".matches(RegularExpressions.javaVariable()));
	}
	@Test
	void testLess256() {
		
		assertTrue("0".matches(RegularExpressions.Less256()));
		assertTrue("000".matches(RegularExpressions.Less256()));
		assertTrue("012".matches(RegularExpressions.Less256()));
		assertTrue("255".matches(RegularExpressions.Less256()));
		assertFalse("256".matches(RegularExpressions.Less256()));
		assertFalse("-1".matches(RegularExpressions.Less256()));
		assertFalse("2 6".matches(RegularExpressions.Less256()));
		
		
	}
//Metacheracter
	//* - any number of symbols, >=0
	//+ - any number of symbols > 0 
	//. - any symbol
	//| - or
	//? - any one symbol
	//a{n} - element - a may be repeated n times
	//() - grouping (a.?){3} aba4a
	
	
//	Characters classes
//	 \d - one decimal digit
//	 \D - any symbols except decimal digit
//	 \w - [a-zA-Z0-9_]
//	 \W - any symbol [^a-zA-Z0-9_]-except
	
}