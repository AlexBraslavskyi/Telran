package telran.words;

import java.util.TreeSet;

public class Words{

	
	TreeSet<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	public boolean addWord(String word) {
		
		return set.add(word);
	}
	public String[] getWordsStartWith(String prefix) {
		String prefixLimit = getPrefixLimit(prefix);
		
		return set.subSet
				(prefix, true, prefixLimit, false)
				.toArray(new String[0]);
		
	}
	public boolean contains(String str) {
		return set.contains(str);
	}
	private String getPrefixLimit(String prefix) {
		char lastChar = prefix.charAt(prefix.length() - 1);
		char limitChar = (char) (lastChar + 1);
		return prefix.substring(0, prefix.length() - 1) + limitChar;

	}
}
