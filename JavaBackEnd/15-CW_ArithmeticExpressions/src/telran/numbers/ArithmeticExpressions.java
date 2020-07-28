package telran.numbers;

import java.util.*;
import java.util.function.BinaryOperator;

public class ArithmeticExpressions {
	static List<String> operations = Arrays.asList("+", "*", "-", "/", "%");
	static List<BinaryOperator<Integer>> operators = Arrays.asList((a, b) -> a + b, 
			(a, b) -> a * b, (a, b) -> a - b,
			(a, b) -> a / b, (a, b) -> a * b / 100);

	static public boolean isValid(String exprStr) {

		String operand = "\\d+";
		String operation = "[-+*/%]";
		String regex = String.format("\\s*%1$s(\\s*%2$s\\s*%1$s)*\\s*", operand, operation);
		return exprStr.matches(regex);
	}

	static public Integer compute(String exprStr) {
		if (!isValid(exprStr)) {
			return null;
		}
		String[] operands = getOperands(exprStr);
		String[] operations = getOperations(exprStr);
		int result = Integer.parseInt(operands[0]);
		for (int i = 1; i < operands.length; i++) {
			result = comuteOneOperation(result, operands[i], operations[i]);
		}
		return result;
	}

	private static int comuteOneOperation(int result, String operandStr, String operation) {
		int operandNum = Integer.parseInt(operandStr);

		int index = operations.indexOf(operation);
		BinaryOperator<Integer> operator = operators.get(index); // operator matching the operation
		return operator.apply(result, operandNum);

	}

	private static String[] getOperations(String exprStr) {

		return exprStr.split("[\\d\\s]+");
	}

	private static String[] getOperands(String exprStr) {
		// trim() removes all leading and trailing spaces
		return exprStr.trim().split("\\D+");
	}
}