package telran.spring;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class SpringProj49Controller {
	
	//http://localhost:8080/calculator?operation=add&op1=10&op2=20
	
	@RequestMapping(method = RequestMethod.GET, value = "/calculator")
    public String calc(@RequestParam Map<String, String> customQuery) {
	
			switch (customQuery.values().toArray()[0].toString()) {
			case "add": return "Result:" + (Integer.parseInt(customQuery.get("op1"))+Integer.parseInt(customQuery.get("op2")));
			case "subtract": return "Result:" + (Integer.parseInt(customQuery.get("op1"))-Integer.parseInt(customQuery.get("op2")));
			case "divide": 
				if(customQuery.get("op2").equals("0")) {
					return "Error dividing on 0";
				}
				return "Result:" + (Integer.parseInt(customQuery.get("op1"))/Integer.parseInt(customQuery.get("op2")));
			case "multiply": return "Result:" +  (Integer.parseInt(customQuery.get("op1"))*Integer.parseInt(customQuery.get("op2")));
			default:return "Not correct format, "
					+ "must be http://localhost:8080/calculator?operation={add/subtract/divide/multiply}&op1={number}&op2={number} ";
		}
			
			
    }
	
	//Variant 2
	
	
	//http://localhost:8080/calculator/add&op1=10&op2=20
	
//	@GetMapping("/calculator/add")   
//	int sum(int op1, int op2) {
//		return op1+op2;
//	}
//	@GetMapping("/calculator/subtract")   
//	int sub(int op1, int op2) {
//		return op1-op2;
//	}
//	@GetMapping("/calculator/divide")   
//	int div(int op1, int op2) {
//		return op1/op2;
//	}
//	@GetMapping("/calculator/multiply")   
//	int mult(int op1, int op2) {
//		return op1*op2;
//	}
}
