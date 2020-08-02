package telran.util;


public class LettersRemovalReplaceAll implements LettersRemovalInterface {

	@Override
	public String removeLetter(String string, char letter) {
		if (string.length() == 0) {
			return "";
		}
		String strLetter = "" + letter;
		
	  return string.replaceAll(strLetter, "");
	}

}
