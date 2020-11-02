package telran.words;

public class Anagram {

	public static boolean isAnagram(String word, String anagram) {
		if (word.isEmpty() || word.length() != anagram.length() ||  word.equals(anagram)) {
			return false;
		}
//		HashMap<Character, Integer> helperMap = new HashMap<Character, Integer>();
//
//		for (char c : word.toCharArray()) {
//			helperMap.compute(c, (key, val)-> (val == null) ? - 1 : val + 1);
//		}
//		for (char c : anagram.toCharArray()) {
//			helperMap.merge(c, 1, (a,b) -> (a-b) == 0 ? null : a-b);
//			
//		}
//		
//			return helperMap.isEmpty();
//		}

//		 Variant lookUpTable
		char [] wordChars = word.toCharArray();
		char [] anagramChars = anagram.toCharArray();
		int [] helper = new int[123];

		for(int i = 0; i < wordChars.length; i++) {
			helper[wordChars[i]]++;
		}
		for(int i = 0; i < anagramChars.length; i++) {
			helper[anagramChars[i]]--;
			if(helper[anagramChars[i]] < 0) {
				return false;
			}
			if(i == anagramChars.length - 1) {
				return true;
			}
		}
		return false;

	}
}
