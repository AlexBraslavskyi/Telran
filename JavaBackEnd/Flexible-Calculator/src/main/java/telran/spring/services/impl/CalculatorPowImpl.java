package telran.spring.services.impl;

import static telran.spring.api.ApiConstants.POW;

import org.springframework.stereotype.Service;

import telran.spring.services.interfaces.Calculator;
@Service()
public class CalculatorPowImpl implements Calculator {
	@Override
	public int calculate(int op1, int op2, String operation) {
	
		if(!operation.equals(POW)) {
			throw new IllegalStateException("CalculatorBasicArithmeticsImp implies only operation POW");
		}
		return (int)Math.pow(op1,op2);
	}

	@Override
	public String []getOperation() {
		return new String []{POW};
	}

}
