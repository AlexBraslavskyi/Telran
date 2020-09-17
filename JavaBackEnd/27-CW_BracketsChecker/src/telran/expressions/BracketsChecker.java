package telran.expressions;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import telran.words.Words;;
public class BracketsChecker {

	//fields
	HashMap<String, String> closeOpenBrackets = new HashMap<>();
	//consider to use class Words from HW #24 for extracting all existing in an expression brackets
	Words allBrackets = new Words();
	ArrayList<String> res = new ArrayList<>();
	String prefix = "";
	String bracket = null;
	HashSet<String> openBrackets = new HashSet<>();
	public BracketsChecker(String[][] bracketsData) {
		for(String [] brackets: bracketsData) {
			closeOpenBrackets.put(brackets[1], brackets[0]);
			openBrackets.add(brackets[0]);
			allBrackets.addWord(brackets[0]);
			allBrackets.addWord(brackets[1]);
		}
	}
public static boolean parenthesesCheck(String expression) {
	
	int count = 0;
	for(char sym: expression.toCharArray()) {
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
public boolean bracketsCheck(String expression) {
	String []brackets = getBrackets(expression);
	
	
	return check(brackets);
}
private boolean check(String[] brackets) {
	Stack<String> stackBrackets = new Stack<>();
	for (String bracket: brackets) {
		if (openBrackets.contains(bracket)) {
			stackBrackets.push(bracket);
		} else {
			if(stackBrackets.isEmpty()) {
				return false;
			}
			if(closeOpenBrackets.get(bracket).equals(stackBrackets.peek())) {
				stackBrackets.pop();
			} else {
				return false;
			}
		}
	}
	return stackBrackets.isEmpty();
}
private String[] getBrackets(String expression) {
	 start();
	for (char symb: expression.toCharArray()) {
		prefix += symb;
		int nBrackets =  allBrackets.getWordsStartWith(prefix).length;
		if(nBrackets == 0) {
			moveToNextBracketSearch(symb);
			
		} else if(allBrackets.contains(prefix)) {
			bracket = prefix;
		}
		
	}
	finish();
	return res.toArray(new String[0]);
}
private void moveToNextBracketSearch(char symb) {
	if (bracket != null) {
		res.add(bracket);
		bracket = null;
		updatePrefix(symb);
	} else{
		prefix = "";
	}
}
private void start() {
	res = new ArrayList<>();
	prefix = "";
	bracket = null;
}
private void finish() {
	if(allBrackets.contains(prefix)) {
		bracket = prefix;
	}
	if(bracket != null) {
		res.add(bracket);
	}
}
private void updatePrefix(char symb) {
	prefix = "" + symb;
	if(allBrackets.contains(prefix)) {
		bracket = prefix;
	} else if(allBrackets.getWordsStartWith(prefix).length == 0){
		prefix = "";
	}
}
}
