package telran.numbers.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.numbers.ArithmeticExpressions.*;

import org.junit.jupiter.api.Test;

class ArithmeticExpressionTest {

	@Test
	void testIsValid() {
		assertTrue(isValid("25"));
		assertTrue(isValid("25 +3/2"));
		assertTrue(isValid("25 +3/2-2"));
		assertFalse(isValid("25 +"));
		assertFalse(isValid("25 ++3"));
	}

	
	@Test
	void testCompute() {
		assertEquals(14, compute(" 25 +3 /2"));
		assertEquals(10, compute("10 * 2 / 2 + 5 - 5"));
	}
}
