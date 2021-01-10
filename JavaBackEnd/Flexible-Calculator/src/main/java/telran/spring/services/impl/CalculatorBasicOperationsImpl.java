package telran.spring.services.impl;

import static telran.spring.api.ApiConstants.ADD;
import static telran.spring.api.ApiConstants.DIV;
import static telran.spring.api.ApiConstants.MULT;
import static telran.spring.api.ApiConstants.SUB;

import org.springframework.stereotype.Service;

import telran.spring.services.interfaces.Calculator;
@Service()
public class CalculatorBasicOperationsImpl implements Calculator {
	@Override
	public int calculate(int op1, int op2, String operation) {
	
		int res = 0;
		
		switch(operation) {
		case ADD: res = op1+op2;break;
		case SUB: res = op1-op2;break;
		case DIV: res = op1/op2;break;
		case MULT: res = op1*op2;break;
		default:throw new RuntimeException("Unknown operation");
		}
		return res;
	}

	@Override
	public String [] getOperation() {
		return new String []{ADD,SUB,DIV,MULT};
	}

}
