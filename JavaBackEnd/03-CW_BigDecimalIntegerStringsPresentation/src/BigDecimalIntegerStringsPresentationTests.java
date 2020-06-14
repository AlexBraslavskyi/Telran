import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class BigDecimalIntegerStringsPresentationTests {

	@Test
	void test() {
		char a = 'A';
		int c = 10+20;
		int d = -c;
		System.out.println((int)a);//casting primitives
	}
	
	@Test
	void bigInteger() {
		BigInteger bi123 = new BigInteger("123");
		BigInteger bi20 = new BigInteger("20");
		String str123 = new String ("123");
		String str20 = new String ("20");
		System.out.println(bi123.add(bi20));
		System.out.println(str123+str20);
		
	}
	
	@Test
	void bigDecimal() {
		
		BigDecimal bd1 = BigDecimal.valueOf(314,2);
		BigDecimal bd2 = new BigDecimal(new BigInteger("314"),2);
		assertEquals(bd1, bd2);
		double d1 = 3.14;
		System.out.println(bd1.add(new BigDecimal(2)));
		System.out.println(d1+2);
	}
	@Test
	void stringPresentation (){
		
		int a = 36236327;
		int b = 36236327;
		
		assertEquals(a, b);
		
		String strA = Integer.toString(a,16);
		String strB = Integer.toString(b,8);
//		assertEquals(strA, strB);
		System.out.println(strA);
		System.out.println(strB);
	}
}
