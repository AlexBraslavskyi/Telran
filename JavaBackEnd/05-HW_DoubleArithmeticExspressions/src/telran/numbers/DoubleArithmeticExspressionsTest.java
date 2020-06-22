package telran.numbers;

import static org.junit.jupiter.api.Assertions.*;
import static telran.numbers.DoubleArithmeticExspressions.compute;
import static telran.numbers.DoubleArithmeticExspressions.isValid;

import org.junit.jupiter.api.Test;

class DoubleArithmeticExspressionsTest {

	@Test
	void testIsValid() {
		assertTrue(isValid("25.3"));
		assertTrue(isValid("25.1 +3.2/2.1"));
		assertTrue(isValid("25.0 +3.4/2.8-2.3"));
		assertFalse(isValid("25.1 +"));
		assertFalse(isValid("25.4 ++3.6"));
	}

	@Test
	void testCompute() {
		assertEquals(15.0, compute(" 25.5 + 4.5/2.0"));
		assertEquals(10.0, compute("10.0 * 2.0 / 2.0 + 5.0 - 5.0"));
	}

}
