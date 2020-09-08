package telran.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class BracketsChecker {

	HashMap<String, String> closeOpenBrackets = new HashMap<>();
	String[][] bracketsData;
	Set<String> openBrackets = new TreeSet<>();

	public BracketsChecker(String[][] bracketsData) {
		this.bracketsData = bracketsData;
		for (String[] brackets : bracketsData) {
			closeOpenBrackets.put(brackets[1], brackets[0]);
			openBrackets.add(brackets[0]);
		}

	}

	public boolean bracketsCheck(String expression) {
		System.out.println(expression);
		String[] brackets = getBrackets(expression);

		return check(brackets);
	}

	private boolean check(String[] brackets) {

		Stack<String> stackBrackets = new Stack<>();
		for (String bracket : brackets) {
			if (openBrackets.contains(bracket)) {
				stackBrackets.push(bracket);
			} else {
				if (stackBrackets.isEmpty()) {
					return false;
				}
				if (closeOpenBrackets.get(bracket).equals(stackBrackets.peek())) {
					stackBrackets.pop();
				} else {
					return false;
				}
			}
		}
		return stackBrackets.isEmpty();
	}

	private String[] getBrackets(String expression) {
//		TreeSet <Character> treeOfChars = new TreeSet<>();
//		for(String brackets[]:bracketsData) {
//			for(String bracket:brackets) {
//				for (char a:bracket.toCharArray()) {
//					treeOfChars.add(a);
//				}
//			}
//		}
//		
//			System.out.println(treeOfChars);

//		    char[] chars = prefix.toCharArray();
//		    if(chars.length>0) {
//		        chars[chars.length-1] = (char) (chars[chars.length-1]+1);
//		    }
//		}
//		return treeOfWords.subSet(prefix, true, chars.toString(), true).toArray(new String[0]);
//		return null;

		ArrayList<Character> exp = new ArrayList<Character>();
		for (char a : expression.toCharArray()) {
			exp.add(a);
		}
		ArrayList<String> res = new ArrayList<>();
		String temp = "";
		for (String brackets[] : bracketsData) {
			for (String bracket : brackets) {
				char[] bracketChar = bracket.toCharArray();
				for (int i = 0; i < bracketChar.length; i++) {
					if (bracketChar.length == 1 && exp.contains(bracketChar[i])) {
						res.add(bracketChar[i] + "");
						exp.remove(exp.indexOf(bracketChar[i]));
					} else if (exp.contains(bracketChar[i])) {
						temp = temp + bracketChar[i];
						exp.remove(exp.indexOf(bracketChar[i]));
//						break;
					} else if (bracketChar.length == 1 && exp.contains(bracketChar[i])) {
						temp = temp + bracketChar[i];
						exp.remove(exp.indexOf(bracketChar[i]));
						if (i == bracketChar.length - 1) {
							res.add(temp);
							temp = "";
						}
					}
//		char[] exp = expression.toCharArray();
//		
////		System.out.println(exp);
//		ArrayList <String> res = new ArrayList<>();
//
//		String temp = "";
//		for(String brackets[]:bracketsData) {
//			for(String bracket:brackets) {
//			char []bracketChar = bracket.toCharArray();
////			System.out.print(bracketChar);
//for(int i = 0, j = 0; i < exp.length;i++) {
//	if(bracketChar.length == 1 && exp[j]==bracketChar[i]) {
//		 res.add(bracketChar[i]+"");
//		 exp[j] = 0;
//		 System.out.println(exp);
//		 break;
//		 
//	}else if(exp[j] == bracketChar[i]){
//		 temp = temp + bracketChar[i];
//		 exp[j] = 0;
//		 break;
//		 }
//if(exp[j] == bracketChar[i]&&i <= bracketChar.length-2 && exp[j+1] != bracketChar[i+1]||i == bracketChar.length-1) {
////	System.out.println(temp);
//	res.add(temp);
//	temp = "";
//	exp[j] = 0;
//}
//if(i == bracketChar.length-1) {
//	i = 0;

				}

			}
		}
		System.out.println();
		res.forEach(System.out::print);
		System.out.println();
		System.out.println();
		return res.toArray(new String[0]);

	}

	// ()
	public static boolean parenthessesCheck(String expression) {
		int count = 0;
		for (char sym : expression.toCharArray()) {
			if (sym == '(') {
				count++;
			} else if (sym == ')') {
				count--;
				if (count < 0) {
					return false;
				}
			}
		}
		return count == 0;
	}

}
