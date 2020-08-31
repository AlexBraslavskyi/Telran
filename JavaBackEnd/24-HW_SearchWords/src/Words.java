
import java.util.TreeSet;

public class Words{

	TreeSet <String> treeOfWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	
	public void addWord(String word) {
		if(word.matches("[A-Za-z]+")){
			treeOfWords.add(word);
			}else {
				 throw new IllegalArgumentException();
		}
	}
	
	public String[] getWordsStartWith(String prefix) {
		if(prefix.length() == 0) {
			return treeOfWords.toArray(new String[0]);
		}
		if(prefix.matches("[A-Za-z]+")){
		    char[] chars = prefix.toCharArray();
		    if(chars.length>0) {
		        chars[chars.length-1] = (char) (chars[chars.length-1]+1);
		    }
		return treeOfWords.subSet(prefix, true, chars.toString(), true).toArray(new String[0]);
	}else {
		 throw new IllegalArgumentException();
	}
	}
}
