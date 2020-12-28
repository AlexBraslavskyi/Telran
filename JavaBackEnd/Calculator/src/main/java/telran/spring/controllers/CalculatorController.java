package telran.spring.controllers;

import static telran.spring.api.ApiConstants.CALCULATOR_ADD;
import static telran.spring.api.ApiConstants.CALCULATOR_DIV;
import static telran.spring.api.ApiConstants.CALCULATOR_MULT;
import static telran.spring.api.ApiConstants.CALCULATOR_SUB;
import static telran.spring.api.ApiConstants.FIRST_PARAM;
import static telran.spring.api.ApiConstants.SECOND_PARAM;
import static telran.spring.service.interfaces.Calculator.ADD;
import static telran.spring.service.interfaces.Calculator.DIV;
import static telran.spring.service.interfaces.Calculator.MULT;
import static telran.spring.service.interfaces.Calculator.SUB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.spring.service.interfaces.Calculator;
@RestController
public class CalculatorController {
@Autowired //request for getting reference to bean of class implementing Calculator (DI)
Calculator calculator;
@GetMapping(value=CALCULATOR_ADD)
ResponseEntity<?> add(@RequestParam(name = FIRST_PARAM, required = true) int op1, 
		@RequestParam(name = SECOND_PARAM, defaultValue = "0")int op2) {
	return getResponse(op1, op2, ADD);
}

@GetMapping(value=CALCULATOR_SUB)
ResponseEntity<?> sub(@RequestParam(name = FIRST_PARAM, required = true) int op1, 
		@RequestParam(name = SECOND_PARAM, defaultValue = "0")int op2) {
	return getResponse(op1, op2, SUB);
}
@GetMapping(value=CALCULATOR_DIV)
ResponseEntity<?> div(@RequestParam(name = FIRST_PARAM, required = true) int op1, 
		@RequestParam(name = SECOND_PARAM, defaultValue = "1")int op2){
	return getResponse(op1, op2, DIV);
}
@GetMapping(value=CALCULATOR_MULT)
ResponseEntity<?> mult(@RequestParam(name = FIRST_PARAM, required = true) int op1, 
		@RequestParam(name = SECOND_PARAM, defaultValue = "1")int op2) {
	return getResponse(op1, op2, MULT);
}


private ResponseEntity<?> getResponse(int op1, int op2, String operation) {
	try {
		return ResponseEntity.ok(calculator.calculate(op1, op2, operation));
	} catch (Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
}
