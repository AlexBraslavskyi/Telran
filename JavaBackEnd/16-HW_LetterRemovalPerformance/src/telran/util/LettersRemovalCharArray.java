package telran.util;

public class LettersRemovalCharArray implements LettersRemovalInterface {

	@Override
	public String removeLetter(String string, char letter) {
		if (string.length() == 0) {
			return "";
		}

		char[] charArray = string.toCharArray();
//		int occurrences = 0;
//		for (char c : charArray) {
//			if (c == letter) {
//				occurrences++;
//			}
//		}
//		char newArrayChar[] = new char[charArray.length - occurrences];
//
//		int index = 0;
//		for (char c : charArray) {
//			if (c != letter) {
//				newArrayChar[index] = c;
//			}
		int j = 0;
		char newArrayChar[] = new char[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] != letter) {
				newArrayChar[j] = charArray[i];
				j++;

			}
		}
//		return new String(newArrayChar);
		return new String(newArrayChar, 0, j);
	}

}
