package telran.numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import static telran.numbers.ArithmeticExpressions.compute;
import static telran.numbers.ArithmeticExpressions.isValid;;


public class ArithmeticExpressionsAppl {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("********************************");
			System.out.println("Enter expression, or type \"exit\"");
			String expInput = scanner.nextLine();
			
			if(expInput.equals("exit")) {
				break;
			}		
			if(!isValid(expInput)) {
				System.out.println("Wrong expression format ");
				continue;
			}
			double result = compute(expInput);
			BigDecimal bdRes = new BigDecimal(result);
			bdRes = bdRes.setScale(3,RoundingMode.HALF_DOWN);
			System.out.println("Result = "+bdRes);
			

		}
	}
}
