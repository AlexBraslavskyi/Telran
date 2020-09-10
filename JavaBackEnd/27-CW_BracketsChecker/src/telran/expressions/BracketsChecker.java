package telran.expressions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class BracketsChecker {

	HashMap<String, String> closeOpenBrackets = new HashMap<>();
	ArrayList<Character> expressionSymbol = new ArrayList<Character>();
	Set<String> openBrackets = new HashSet<>();

	TreeSet<String> listBrackets = new TreeSet<String>
	(Comparator.comparing(String::length).reversed().thenComparing(String::compareTo));


	public BracketsChecker(String[][] bracketsData) {
		for (String[] brackets : bracketsData) {
			closeOpenBrackets.put(brackets[1], brackets[0]);
			openBrackets.add(brackets[0]);
			listBrackets.add(brackets[0]);
			listBrackets.add(brackets[1]);
		}
		System.out.println(listBrackets);
	}

	public boolean bracketsCheck(String expression) {
	
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
		for (char a : expression.toCharArray()) {
			expressionSymbol.add(a);
		}
		ArrayList<String> res = new ArrayList<>();
		String temp = "";
		for (String bracket : listBrackets) {
			char[] bracketChar = bracket.toCharArray();
			for (int i = 0; i < bracketChar.length; i++) {
				if (bracketChar.length == 1 && expressionSymbol.contains(bracketChar[i])) {
					res.add(bracketChar[i] + "");
					expressionSymbol.remove(expressionSymbol.indexOf(bracketChar[i]));
				} else if (expressionSymbol.contains(bracketChar[i])) {
					temp = temp + bracketChar[i];
					expressionSymbol.remove(expressionSymbol.indexOf(bracketChar[i]));
					if (i == bracketChar.length - 1) {
						res.add(temp);
						temp = "";
					}
				}
			}
		}
		System.out.println();
		res.forEach(System.out::print);
		System.out.println();
	
	
		TreeSet<String> result = new TreeSet<String>
		(Comparator.comparing(String::length).thenComparing(String::compareTo));
		for(String r:res) {
			result.add(r);
		}
		System.out.println();
		result.forEach(System.out::print);
		System.out.println();
		return result.toArray(new String[0]);
	}

	public boolean parenthesesCheck(String expression) {
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
