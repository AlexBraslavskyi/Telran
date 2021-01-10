package telran.spring.services.impl;

import static telran.spring.api.ApiConstants.PER;

import org.springframework.stereotype.Service;

import telran.spring.services.interfaces.Calculator;
@Service()//
public class CalculatorPerImpl implements Calculator {
	@Override
	public int calculate(int op1, int op2, String operation) {
		
		if(!operation.equals(PER)) {
			throw new IllegalStateException("CalculatorBasicArithmeticsImp implies only operation PER");
		}
		return op1 * 100 / op2;
//		return op1 * op2 / 100;
	}

	@Override
	public String[] getOperation() {
		
		return new String[] {PER};
	}

}
