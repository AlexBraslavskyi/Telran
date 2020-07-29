package telran.util;

public class JoinStringsImmutable implements  JoinStringsInterface{
	
	@Override
	public String join(String[] strings, String delimiter) {
		String res = strings[0];
		for(int i = 0; i<strings.length; i++) {
			res = res + delimiter + strings[i];
		}
		return res;
	}

}
