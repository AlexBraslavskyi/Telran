package telran.numbers.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.numbers.Calculator;

class CalcTest {

	@Test
	void testSum() {
		assertEquals(15, Calculator.sum(10, 5));
		assertEquals(-15,Calculator.sum(-10, -5));
	
	}

	@Test
	void testSubtract() {
		assertEquals(5,Calculator.subtract(10, 5));
		assertEquals(-5,Calculator.subtract(5, 10));
	}

	@Test
	void testDivide() {
		assertEquals(2,Calculator.divide(10, 5));
		assertEquals(-2,Calculator.divide(-10, 5));
	}

	@Test
	void testMultiply() {
		assertEquals(10,Calculator.multiply(2, 5));
		assertEquals(-10,Calculator.multiply(-2, 5));
	}
	@Test
	void testDigits() {
		assertEquals(4,Calculator.digits(1234));
		assertEquals(1,Calculator.digits(5));
		assertEquals(1,Calculator.digits(0));
	}
	@Test
	void testPow() {
		assertEquals(25,Calculator.pow(5,2));
		assertEquals(1000,Calculator.pow(10,3));

	}
}