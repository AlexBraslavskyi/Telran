package telran.words;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static telran.words.Anagram.isAnagram;

import org.junit.jupiter.api.Test;

class AnagramTest {
String word = "yellow";
	@Test
	void testAnagramTrue() {
		assertTrue(isAnagram(word, "llowye"));
		assertTrue(isAnagram(word, "lowyel"));
		assertTrue(isAnagram(word, "olwlye"));
		assertTrue(isAnagram(word, "olwyle"));
	}
	@Test
	void testAnagramFalse() {
		assertFalse(isAnagram(word, "olwle"));
		assertFalse(isAnagram(word, "ollelwly"));
		assertFalse(isAnagram(word, ""));
		assertFalse(isAnagram("", ""));
		assertFalse(isAnagram(word, "ollelwly"));
		assertFalse(isAnagram(word, word));
}
}
