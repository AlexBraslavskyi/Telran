import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NumberTest {

	@Test
	void test() {
		assertTrue(isNumber("1234567"));
		assertTrue(isNumber("13e-10"));
		assertFalse(isNumber("12.12.3"));
		assertFalse(isNumber("111+11111111111111111111111111111111111111111111111"));
		
	}

	private boolean isNumber(String str) {
	boolean res = false;
	try {
		Double.parseDouble(str);
		res = true;
	}catch(Exception e) {
		
	}
	return res;	
	}

}
