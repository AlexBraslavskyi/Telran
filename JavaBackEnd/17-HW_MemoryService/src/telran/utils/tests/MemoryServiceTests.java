package telran.utils.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import telran.utils.MemoryService;

class MemoryServiceTests {

	byte ar[];

	@Test
	void testMaxNewMemory() {
		boolean flException = false;
		int maxSize = MemoryService.getMaxNewMemory();
		ar = new byte[maxSize];
		ar = null;
		try {
			ar = new byte[maxSize + 1];

		} catch (Throwable e) {
			flException = true;
		}
		assertTrue(flException);
		System.out.println("Max free memory: " + maxSize);
		System.out.println("Max runtime free memory: " + Runtime.getRuntime().freeMemory());
		System.out.println("Max runtime memory: " + Runtime.getRuntime().maxMemory());

	}

}
