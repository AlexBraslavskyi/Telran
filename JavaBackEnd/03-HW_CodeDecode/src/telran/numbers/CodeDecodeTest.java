package telran.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CodeDecodeTest {

	@Test
	void tesClasswork() {
		String key = "0123456789";
		CodeDecode cd = new CodeDecode(key);
		String resStr =  cd.code(1203);
		assertEquals(resStr, "1203");
		int resNum = cd.decode("1203");
		assertEquals(1203, resNum);
	}

	@Test
	void testHomework() {
		CodeDecode cd10 = new CodeDecode("*()+&^%$#-");
		String resStr = cd10.code(12340);
		assertEquals("()+&*", resStr);
		CodeDecode cd2 = new CodeDecode(".-");
		assertEquals("-.-", cd2.code(5));
		assertEquals(10, cd10.decode("(*"));
	}
	
	@Test 
	void testGetKey() {
	String key = "$Gft%56&8(+=-_90hjgre3@1<>?/mnHIop";
	CodeDecode cd = new CodeDecode(key);
	assertEquals(key, getKeyString(cd));
	}
	
	String getKeyString(CodeDecode cd) {
//		int length = cd.getBase();
		int length = cd.toString().length();
//		int length = String.valueOf(cd).length();
//		System.out.println(length);
		String str = "";
	String res = null;
	for(int i=0;i<length;i++) {
	
		str = str + cd.code(i);
	}
	res = str;
//	System.out.println(res);
	return res;
	}
}
