import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class WordsTest {
	
	@Test
	void test() {
		Words wordsTree = new Words();
		try {
			wordsTree.addWord("abC");
			wordsTree.addWord("aBcde");
			wordsTree.addWord("abC");
			wordsTree.addWord("Abc");
			wordsTree.addWord("ABCde");
			wordsTree.addWord("bc");
			wordsTree.addWord("ab");
			wordsTree.addWord("abcwee");
			wordsTree.addWord("acff");
			wordsTree.addWord("def");
			wordsTree.addWord("a b c");
		} catch (Exception e) {
			System.out.println("You try add word in wrong format " + e);
			System.out.println(e.getStackTrace()[1]);
		}
		String[] expected = {"abc", "aBcde", "abcwee"};
		String[] expectedAll = {"ab","abc", "aBcde", "abcwee", "acff", "bc", "def"};
		try {
		assertEquals(expected.length, wordsTree.getWordsStartWith("abc").length);
		assertArrayEquals(expected, wordsTree.getWordsStartWith("abc"));
		assertEquals(expectedAll.length, wordsTree.getWordsStartWith("").length);
		assertArrayEquals(expectedAll, wordsTree.getWordsStartWith(""));
		System.out.println(Arrays.toString(wordsTree.getWordsStartWith("-56")));
		} catch (Exception e) {
			System.out.println("Your prefix in wrong format " + e);
			System.out.println(e.getStackTrace()[1]);
		}
	}

}
