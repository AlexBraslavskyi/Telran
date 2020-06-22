package telran.numbers;
public class ArithmeticExpressions {
static public boolean isValid(String exprStr) {
	
	String operand = "\\d+";
	String operation = "[-+*/]";
	String regex = String.format("\\s*%1$s(\\s*%2$s\\s*%1$s)*\\s*", operand, operation) ;
	return exprStr.matches(regex);
}
static public Integer compute(String exprStr) {
	if(!isValid(exprStr)) {
		return null;
	}
	String [] operands = getOperands(exprStr);
	String [] operations = getOperations(exprStr);
	int result = Integer.parseInt(operands[0]);
	for(int i = 1; i < operands.length; i++) {
		result = comuteOneOperation(result, operands[i], operations[i]);
	}
	return result;
}
private static int comuteOneOperation(int result, String operandStr, String operation) {
	int operandNum = Integer.parseInt(operandStr);
	switch(operation) {
	case "+": return result + operandNum;
	case "-": return result - operandNum;
	case "*": return result * operandNum;
	case "/": return result / operandNum;
	}
	return 0;
}
private static String[] getOperations(String exprStr) {
	
	return exprStr.split("[\\d\\s]+");
}
private static String[] getOperands(String exprStr) {
	//trim() removes all leading and trailing spaces
	return exprStr.trim().split("\\D+");
}
}