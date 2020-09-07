package telran.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class BracketsChecker {
	
	HashMap <String, String> closeOpenBrackets = new HashMap<>();
	String [][] bracketsData;
	Set<String> openBrackets = new TreeSet<>();
	public BracketsChecker(String [][] bracketsData) {
		this.bracketsData = bracketsData;
	for(String[] brackets:bracketsData) {
		closeOpenBrackets.put(brackets[1], brackets[0]);
		openBrackets.add(brackets[0]);
	}
		
	}
	
	
	
	public boolean bracketsCheck(String expression) {
		
		String [] brackets = getBrackets(expression);
		
		return check(brackets);
	}
	private boolean check(String[] brackets) {

		Stack<String> stackBrackets = new Stack<>();
		for(String bracket:brackets) {
			if(openBrackets.contains(bracket)) {
				stackBrackets.push(bracket);
			}else {
				if(stackBrackets.isEmpty()) {
					return false;
				}
				if(closeOpenBrackets.get(bracket).equals(stackBrackets.peek())) {
					stackBrackets.pop();
				}else {
					return false;
				}
			}
		}
		return stackBrackets.isEmpty();
	}



	private String[] getBrackets(String expression) {
		ArrayList <String> res = new ArrayList<>();
		for(String [] brackets:bracketsData) {
			for(String brack:brackets) {
		if(expression.contains(brack)) {
			res.add(brack);
		}
//		for(String brack:bracketsData[1]) {
//			if(expression.contains(brack)) {
//				res.add(brack);
//			}
		}
		}
		res.forEach(System.out::println);
		return (String[]) res.toArray();
	}


	//()
	public static boolean parenthessesCheck(String expression) {
		int count = 0;
		for(char sym:expression.toCharArray()) {
			if(sym =='(') {
				count++;
			}else if(sym ==')') {
				count--;
				if(count < 0) {
					return false;
				}
			}
		}
		return count == 0;
	}
	
	
	
	

}
