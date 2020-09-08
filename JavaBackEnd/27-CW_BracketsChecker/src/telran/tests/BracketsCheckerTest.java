package telran.tests;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import telran.expressions.BracketsChecker;

class BracketsCheckerTest {
	String [][]brackets = {{"{", "}"},{"<table>", "</table>"},{"[","]"},{"/*", "*/"}, {"<", ">"}};
	BracketsChecker bracketsChecker = new BracketsChecker(brackets);
//		@Test
//		void testParenthesesTrue() {
//			assertTrue(parenthessesCheck("abcc(hhhh)"));
//			assertTrue(parenthessesCheck("(((())))"));
//			assertTrue(parenthessesCheck("((c(ab(lm))))"));
//		}
//		@Test
//		void testParenthesesFalse() {
//			assertFalse(parenthessesCheck("abcc(hh(hh)"));
//			assertFalse(parenthessesCheck("(((()))"));
//			assertFalse(parenthessesCheck("((c(ab)(lm))))"));
//			assertFalse(parenthessesCheck(")("));
//		}
		@Test 
		void testBracketsTrue() {
			assertTrue(bracketsChecker.bracketsCheck("<table>[(hhhh]</table>"));
			assertTrue(bracketsChecker.bracketsCheck("<table>aaaaaa[(hhhh]</table>"));
			assertTrue(bracketsChecker.bracketsCheck("<table>[{hhhh}]</table>"));
			assertTrue(bracketsChecker.bracketsCheck("/*{uuuu}[{;;;;;}]*/"));
			assertTrue(bracketsChecker.bracketsCheck("<<table>[{hhhh}]</table>>"));
		}
		@Test 
		void testBracketsFalse() {
			assertFalse(bracketsChecker.bracketsCheck("<table>[{hhhh]</table>"));
			assertFalse(bracketsChecker.bracketsCheck("<table>[{hhhh)]</table>"));
			assertFalse(bracketsChecker.bracketsCheck("/*{uu*/uu}[{;;;;;}]*/"));
		}
//String [][] brackets = {{"{","}"},{"<table>","</table>"},{"[","]"},{"/*","*/"}};
//BracketsChecker bracketsChecker = new BracketsChecker(brackets);
//	@Test
//	void testParenthessesTrue() {
//	assertTrue(parenthessesCheck("abc(uui)"));
//	assertTrue(parenthessesCheck("()hhhh"));
//	assertTrue(parenthessesCheck("a(bc())hhhh()"));
//	assertTrue(parenthessesCheck("((()))"));
//	}
//	@Test
//	void testParenthessesFalse() {
//		assertFalse(parenthessesCheck("abc(uui))"));
//		assertFalse(parenthessesCheck("abcuui))"));
//		assertFalse(parenthessesCheck("abc(uui))"));
//		assertFalse(parenthessesCheck("ab(c(()"));
//		assertFalse(parenthessesCheck(")("));
//		assertFalse(parenthessesCheck("(()))("));
//	}
//	@Test
//	void testBracketsTrue() {
//	assertTrue(bracketsChecker.bracketsCheck("{qqq}{}{}"));
//	assertTrue(bracketsChecker.bracketsCheck("<table>[/*r*/]r{r}r</table>"));
//	assertTrue(bracketsChecker.bracketsCheck("aaaa<table>[/*r*/r{r}r]</table>"));
//	}
//	@Test
//	void testBracketsFalse() {
//		assertFalse(bracketsChecker.bracketsCheck("{qqq{}{}"));
//		assertFalse(bracketsChecker.bracketsCheck("<table>/*r*/r{r}r]table>"));
//	}
}
