package telran.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;
import telran.numbers.DividerRule;
import telran.numbers.Generator;

class GeneratorRulesTests {
DividerRule divider10 = new DividerRule(10);
DividerRule divider3 = new DividerRule(3);
DividerRule divider7 = new DividerRule(7);

int min = 1, max = 10000, nNumbers = 100000;

	@Test
	void testGenerate() {

		Generator generator = null;
		generator = new Generator(min, max, divider10);
		int[] ar = generator.generate(nNumbers);
		assertEquals(nNumbers, ar.length);
		for(int num: ar) {
			assertTrue(num % 10 == 0 && num >= min && num <= max);
		}
		try {
			generator = new Generator(max, min, divider10);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {	
		}
	}
	@Test
	void testDivider()  {
		try {
			divider10.checkRule(44, 19, 29);
		} catch(RuleException e) {
			assertEquals(-24, e.getDelta());
		}
		try {
			divider10.checkRule(10, min, max);
		} catch(Exception e) {
			fail("Unexpected Exception");
		}
		try {
			divider10.checkRule(12, min, max);
			fail("Expected RuleException");
		}catch (RuleException e) {
			assertEquals(-2, e.getDelta());
		}
		try {
			divider10.checkRule(12, 11, max);
			fail("Expected RuleException");
		}catch (RuleException e) {
			assertEquals(8, e.getDelta());
		}
		try {
			divider10.checkRule(12, 11, 19);
			fail("Expected RangeException");
		}catch (RangeException e) {
			
		}catch (RuleException e) {
			fail("Unexpected Exception");
		}
		try {
			divider10.checkRule(84, 19, 29);
			fail("Expected RuleException");
		}catch (RuleException e) {
			assertEquals(-64, e.getDelta());
		}
	}
		@Test
		void testDividerDaniel() {
			try {
				divider10.checkRule(10, 19, 21);
				fail("Expected RuleException");
			} catch (RuleException e) {
				assertEquals(10, e.getDelta()); // nearest matched in range is 20 
			}
			try {
				divider10.checkRule(30, 19, 21);
				fail("Expected RuleException");
			} catch (RuleException e) {
				assertEquals(-10, e.getDelta()); // nearest matched in range is 20
			}
			try {
				divider10.checkRule(10, 2, 4);
				fail("Expected RangeException"); 
			}catch (RangeException e) {
				// test success					// no divisibles to 10 in range [2,4]
			}catch (RuleException e) {
				fail("Unexpected Exception");    
			}
			try {
				divider7.checkRule(7, 13, 100);
				fail("Expected RuleException");
			} catch (RuleException e) {
				assertEquals(7, e.getDelta()); 
			}
			try {
				divider3.checkRule(30, 19, 21);
				fail("Expected RuleException");
			} catch (RuleException e) {
				assertEquals(-9, e.getDelta());
			}
			try {
				divider7.checkRule(6, min, 71);
				fail("Expected RuleException");
			} catch (RuleException e) {
				assertEquals(1, e.getDelta()); 
			}
		}
//		@Test
		void testDivider7_2() {
			try {
				divider7.checkRule(68, min, 75);
				fail("Expected RuleException");
			} catch (RuleException e) {
				assertEquals(2, e.getDelta()); 
			}
			try {
				divider7.checkRule(45, min, max);
				fail("Expected RuleException");
			} catch (RuleException e) {
				assertEquals(4, e.getDelta()); 
			}
		}
		@Test
		void testDivider7_3() {
			try {
				divider7.checkRule(49, min, max);
			} catch(Exception e) {
				fail("Unexpected Exception");
			}
		}
}

