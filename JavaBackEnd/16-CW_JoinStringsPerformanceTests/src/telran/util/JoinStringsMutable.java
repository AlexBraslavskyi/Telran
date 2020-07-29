package telran.util;

public class JoinStringsMutable implements JoinStringsInterface {

	@Override
	public String join(String[] strings, String delimiter) {
	StringBuilder builder = new StringBuilder(strings[0]);
	for(int i = 1; i<strings.length; i++) {
		builder.append(delimiter).append(strings[i]);
		
	}
		return builder.toString();
	}

}
