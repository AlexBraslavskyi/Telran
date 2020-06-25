package telran.numbers;

public class ArithmeticExpressions {
static public boolean isValid(String exprStr) {
		
		String operand = "\\d+(\\.\\d+)?";
		String operation = "[-+*/]";
		String regex = String.format("\\s*%1$s(\\s*%2$s\\s*%1$s)*\\s*", operand, operation) ;
		return exprStr.matches(regex);
	}
	static public Double compute(String exprStr) {
		if(!isValid(exprStr)) {
			System.out.println("Wrong format");
			return null;
		}
		String [] operands = getOperands(exprStr);
		String [] operations = getOperations(exprStr);
		double result = Double.parseDouble(operands[0]);
		for(int i = 1; i < operands.length; i++) {
			result = comuteOneOperation(result, operands[i], operations[i]);
		}
		return result;
	}
	private static double comuteOneOperation(double result, String operandStr, String operation) {
		double operandNum = Double.parseDouble(operandStr);
		switch(operation) {
		case "+": return result + operandNum;
		case "-": return result - operandNum;
		case "*": return result * operandNum;
		case "/": return result / operandNum;
		}
		return 0;
	}
	private static String[] getOperations(String exprStr) {
		String []res = exprStr.split("[\\d.\\s]+");
		return res;
	}
	private static String[] getOperands(String exprStr) {
		//trim() removes all leading and trailing spaces
		String []res = exprStr.trim().split("[^\\d.]+");
		return res;
	}

	}

