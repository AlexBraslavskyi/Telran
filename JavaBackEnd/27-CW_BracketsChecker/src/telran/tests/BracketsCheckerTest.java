package telran.tests;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import telran.expressions.BracketsChecker;

class BracketsCheckerTest {
	String [][]brackets = {{"{", "}"},{"<table>", "</table>"},{"[","]"},{"/*", "*/"}, {"<", ">"},{"{{","}}"}};
	BracketsChecker bracketsChecker = new BracketsChecker(brackets);
		@Test
		void testParenthesesTrue() {
			assertTrue(bracketsChecker.parenthesesCheck("abcc(hhhh)"));
			assertTrue(bracketsChecker.parenthesesCheck("(((())))"));
			assertTrue(bracketsChecker.parenthesesCheck("((c(ab(lm))))"));
		}
		@Test
		void testParenthesesFalse() {
			assertFalse(bracketsChecker.parenthesesCheck("abcc(hh(hh)"));
			assertFalse(bracketsChecker.parenthesesCheck("(((()))"));
			assertFalse(bracketsChecker.parenthesesCheck("((c(ab)(lm))))"));
			assertFalse(bracketsChecker.parenthesesCheck(")("));
		}
		@Test 
		void testBracketsTrue() {
			assertTrue(bracketsChecker.bracketsCheck("<table>[(hhhh]</table>"));
			assertTrue(bracketsChecker.bracketsCheck("<table>aaaaaa[(hhhh]</table>"));
			assertTrue(bracketsChecker.bracketsCheck("<table>[{hhhh}]</table>"));
			assertTrue(bracketsChecker.bracketsCheck("/*[]*/"));
			assertTrue(bracketsChecker.bracketsCheck("<<table>[{hhhh}]</table>>"));
			assertTrue(bracketsChecker.bracketsCheck("{{{hhhh}hhh}}"));
			assertTrue(bracketsChecker.bracketsCheck("<tabl>"));
			assertTrue(bracketsChecker.bracketsCheck("<{table}>"));
		}
		@Test 
		void testBracketsFalse() {
			assertFalse(bracketsChecker.bracketsCheck("<table>[{hhhh]</table>"));
			assertFalse(bracketsChecker.bracketsCheck("<table>[{hhhh)]</table>"));
			assertFalse(bracketsChecker.bracketsCheck("/*{uu*/uu}[{;;;;;}]*/"));
			assertFalse(bracketsChecker.bracketsCheck("<<table>>[{hhhh}]</table>"));
			assertFalse(bracketsChecker.bracketsCheck("{{hhhh}hhh}"));
			assertFalse(bracketsChecker.bracketsCheck("<table>"));
		}

}
