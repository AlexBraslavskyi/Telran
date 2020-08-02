package telran.util;

public class LettersRemovalStringBuilder implements LettersRemovalInterface {

	@Override
	public String removeLetter(String string, char letter) {
		if (string.length() == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for(char cur : string.toCharArray()) {
			if(cur != letter) {
			builder.append(cur);
		}	
		}
		
	return builder.toString();
}
}