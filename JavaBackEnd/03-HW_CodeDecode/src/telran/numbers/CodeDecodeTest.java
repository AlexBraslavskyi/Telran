package telran.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CodeDecodeTest {

	@Test
	void tesClasswork() {
		String key = "0123456789";
		CodeDecode cd = new CodeDecode(key);
		String resStr =  cd.code(123);
		assertEquals(resStr, "123");
		int resNum = cd.decode("123");
		assertEquals(123, resNum);
	}

	@Test
	void testHomework() {
		CodeDecode cd10 = new CodeDecode("*()+&^%$#-");
		String resStr = cd10.code(123);
		assertEquals("()+", resStr);
		CodeDecode cd2 = new CodeDecode(".-");
		assertEquals("-.-", cd2.code(5));
		assertEquals(10, cd10.decode("(*"));
	}
	
	@Test 
	void testGetKey() {
	String key = "$Gft%56&8(+=-_90hjgre3@1<>?/mnHIoph";
	CodeDecode cd = new CodeDecode(key);
	assertEquals(key, getKeyString(cd));
	}
	
	String getKeyString(CodeDecode cd) {
		String res =	"";
		int digits = 0;
		String code = cd.code(0);
		do {
			res	+=	code;
			digits++;
			code = cd.code(digits);
		}while(code.length() == 1);
	return res;
	}
}
