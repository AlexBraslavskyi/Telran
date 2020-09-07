package telran.tests;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static telran.expressions.BracketsChecker.parenthessesCheck;

import org.junit.jupiter.api.Test;

import telran.expressions.BracketsChecker;

class BracketsCheckerTest {
String [][] brackets = {{"{","}"},{"<table>","</table>"},{"[","]"},{"/*","*/"}};
BracketsChecker bracketsChecker = new BracketsChecker(brackets);
	@Test
	void testParenthessesTrue() {
	assertTrue(parenthessesCheck("abc(uui)"));
	assertTrue(parenthessesCheck("()hhhh"));
	assertTrue(parenthessesCheck("a(bc())hhhh()"));
	assertTrue(parenthessesCheck("((()))"));
	}
	@Test
	void testParenthessesFalse() {
		assertFalse(parenthessesCheck("abc(uui))"));
		assertFalse(parenthessesCheck("abcuui))"));
		assertFalse(parenthessesCheck("abc(uui))"));
		assertFalse(parenthessesCheck("ab(c(()"));
		assertFalse(parenthessesCheck(")("));
		assertFalse(parenthessesCheck("(()))("));
	}
	@Test
	void testBracketsTrue() {
	assertTrue(bracketsChecker.bracketsCheck("{qqq}{}{}"));
	assertTrue(bracketsChecker.bracketsCheck("<table>[/*r*/]r{r}r</table>"));
	assertTrue(bracketsChecker.bracketsCheck("aaaa<table>[/*r*/r{r}r]</table>"));
	}
	@Test
	void testBracketsFalse() {
		assertFalse(bracketsChecker.bracketsCheck("{qqq{}{}"));
		assertFalse(bracketsChecker.bracketsCheck("<table>/*r*/r{r}r]table>"));
	}
}
