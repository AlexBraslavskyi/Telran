package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class LetterRemovalJUnitTest {
	String str = "abcabcabcabc";
	String str2 = "";
   LettersRemovalReplaceAll raplaceAll = new LettersRemovalReplaceAll();
   LettersRemovalCharArray charArr = new LettersRemovalCharArray();
   LettersRemovalStringBuilder strBuilder = new LettersRemovalStringBuilder();
	@Test
	void test() {
String exp ="bcbcbcbc";
String exp2 ="";
String res = raplaceAll.removeLetter(str, 'a');
String res2 = charArr.removeLetter(str, 'a');
String res3 = strBuilder.removeLetter(str, 'a');
String res4 = raplaceAll.removeLetter(str, 'y');
String res5 = charArr.removeLetter(str2, ' ');
String res6 = charArr.removeLetter(str2, 'a');
		assertEquals(exp,res);
		assertEquals(exp,res2);
		assertEquals(exp,res3);
		assertNotEquals(exp,res4);
		assertEquals(exp2,res6);
	}

}
