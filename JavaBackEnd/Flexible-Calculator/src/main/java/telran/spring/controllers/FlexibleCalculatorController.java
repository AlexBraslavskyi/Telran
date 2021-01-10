package telran.spring.controllers;

import static telran.spring.api.ApiConstants.CALCULATOR;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.spring.api.dto.CalculateData;
import telran.spring.services.interfaces.Calculator;

@RestController
public class FlexibleCalculatorController {
	Map<String, Calculator> calculators = new HashMap<>();
	@Autowired
	List<Calculator> list;

	@PostMapping(CALCULATOR)
	ResponseEntity<?> calculate(@RequestBody CalculateData calculateData) {
		Calculator calculator = calculators.get(calculateData.operation);
		if (calculators == null) {
			return ResponseEntity.badRequest().body("unknown operation");
		}
		try {
			return ResponseEntity
					.ok(calculator.calculate(calculateData.op1, calculateData.op2, calculateData.operation));
		} catch (IllegalStateException e) {
			return ResponseEntity.status(501).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostConstruct
	void fillMap() {
		System.out.println(list.size());
		for (Calculator calc : list) {
			String[] operations = calc.getOperation();
			for(String operation:operations) {
			calculators.put(operation, calc);
		}
	}
	}
	@PostConstruct
	void displaySupportedOperations() {
		System.out.print("Supported operations: " + calculators.keySet());
	}
}
