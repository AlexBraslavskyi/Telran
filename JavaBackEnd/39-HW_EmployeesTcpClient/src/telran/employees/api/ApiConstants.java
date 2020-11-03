package telran.employees.api;


public interface ApiConstants {

	String ADD_EMPLOYEE = "telran.employees.net.EmployeesProtocol.addEmployee";
	String GET_EMPLOYEE = "telran.employees.net.EmployeesProtocol.getEmployee";
	String REMOVE_EMPLOYEE = "telran.employees.net.EmployeesProtocol.removeEmployee";
	String UPDATE_EMPLOYEE = "telran.employees.net.EmployeesProtocol.updateEmployee";
	String GET_EMPLOYEES_BY_AGE = "telran.employees.net.EmployeesProtocol.getEmplByAge";
	String GET_EMPLOYEES_BY_DEPARTMENT = "telran.employees.net.EmployeesProtocol.getEmplByDept";
	String GET_EMPLOYEES_BY_SALARY = "telran.employees.net.EmployeesProtocol.getEmplBySalary";
	String GET_EMPLOYEES_BY_SALARY_INTERVAL = "telran.employees.net.EmployeesProtocol.getEmplBySalaryInterval";
	String GET_DEPT_AVR_SALARY_DISTR = "telran.employees.net.EmployeesProtocol.getDeptAvrSalaryDistr";
	int SALARY_FROM = 5000;
	int SALARY_TO = 30000;
	
}
