package telran.text;
public class RegularExpressions {
public static String getTestRegex() {
	
//	TODO
//	return "a.+|c.?";
	//1-2 12 (3)  ----    1-2345-7
	return "(\\d-?\\d){3}";
}
public static String javaVariable() {
	String regex = "[A-Za-z$][A-Za-z_$0-9]*|_[A-Za-z_$0-9]+";
	return regex;
}
public static String Less256() {
	String regex = "\\d|\\d?|[0-1]\\d\\d|2[0-4]\\d|25[0-5]";
	return regex;
}
}
