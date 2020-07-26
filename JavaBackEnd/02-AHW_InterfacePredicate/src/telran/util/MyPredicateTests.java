package telran.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MultipleOfTwoPredicate implements MyPredicate<Integer> {
	@Override
	public boolean test(Integer obj) {
		return obj % 2 == 0;
	}
}

class MultipleOfThreePredicate implements MyPredicate<Integer> {
	@Override
	public boolean test(Integer obj) {
		return obj % 3 == 0;
	}
}

class MyPredicateTests {
	final MyPredicate<Integer> m2 = new MultipleOfTwoPredicate();
	final MyPredicate<Integer> m3 = new MultipleOfThreePredicate();

	@Test
	void testNegate() {
		assertFalse(m2.negate().test(2));
		assertTrue(m2.negate().negate().test(2));
	}

	@Test
	void testAnd() {
		MyPredicate<Integer> andP = m2.and(m3);
		assertTrue(andP.test(2 * 3));
		assertFalse(andP.test(2));
		assertFalse(andP.test(3));
		assertFalse(andP.test(1));
	}

	@Test
	void testOr() {
		MyPredicate<Integer> orP = m2.or(m3);
		assertTrue(orP.test(2 * 3));
		assertTrue(orP.test(2));
		assertTrue(orP.test(3));
		assertFalse(orP.test(1));
	}
}