package telran.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CodeDecodeTest {

//	@Test
//	void tesClasswork() {
//		CodeDecode cd = new CodeDecode();
//		String resStr =  cd.code(123);
//		assertEquals(resStr, "123");
//		int resNum = cd.decode("123");
//		assertEquals(123, resNum);
//	}

	@Test
	void testHomework() {
		CodeDecode cd10 = new CodeDecode("*()+&^%$#-");
		String resStr = cd10.code(123);
		assertEquals("()+", resStr);
		CodeDecode cd2 = new CodeDecode(".-");
		assertEquals("-.-", cd2.code(5));
		assertEquals(10, cd10.decode("(*"));
	}
}
