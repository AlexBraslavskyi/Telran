import java.util.HashMap;

public class QuickDictionary {
	HashMap<String, String> dicMap = new HashMap<>();
	
	public static void main(String[] args) {
		System.out.println(Math.pow(91, 20));
		put("abc", "sss");
		put("abc", "yyyuu");
	}
	

    public static String put(String key, String value) {
		
//		char [] wordChars = key.toCharArray();
	int length = key.length();
	HashMap<String, String> helper = new HashMap<>();

//		for(int i = 0; i < wordChars.length; i++) {
			helper.put(length+"", value);
//		}
		System.out.println(helper.toString());
//		for(int i = 0; i < anagramChars.length; i++) {
//			helper[anagramChars[i]]--;
//			if(helper[anagramChars[i]] < 0) {
//				return false;
//			}
//			if(i == anagramChars.length - 1) {
//				return true;
//			}
    	return value;

    }

    public static String get(String key) {
		
    	
    	return key;

    }
}

