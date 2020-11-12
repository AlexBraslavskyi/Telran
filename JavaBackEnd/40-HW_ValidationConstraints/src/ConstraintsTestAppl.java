


import telran.validation.Validator;

public class ConstraintsTestAppl {

	public static void main(String[] args) {
		
		Employee empl = new Employee(123, null, "2022-01-01", "QA", 11000,"alex@gmail.com","192.168.0.1",
				new Address("","Herzl",-1,20));
		Validator.validate(empl).forEach(System.out::println);;

	}

}
