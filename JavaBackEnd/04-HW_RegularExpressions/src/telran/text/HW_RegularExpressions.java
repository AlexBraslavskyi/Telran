package telran.text;

public class HW_RegularExpressions {
	public static String israelMobilePhone() {
		
		String regex = "(\\+972-?|0)5[02-8](-?\\d){7}";
		return regex;
		
	}
public static String emailAddress(){
		
	String firstPart = "[^\\s,]+";
	String domein = "[a-zA-Z]-?+[a-zA-Z]+";
//			+ "(\\.[a-zA-Z]-?+[a-zA-Z]+){1,3}";
		String regex = String.format("%1$s@%2$s(\\.%2$s){1,3}", firstPart,domein);
		return regex;
		
	}

}
