package telran.spring.service.impl;

import org.springframework.stereotype.Service;

import telran.spring.service.interfaces.Calculator;
@Service      // ~ == @Component, Configuration, Repository
public class CalculatorSimpleImpl implements Calculator {

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

}
