1.	Rewrite Employees application according to the following depicted architecture

1.1.	App – Stateful component.

1.1.1.	Renders React component BrowserRouter composing of EmployeesNav component and Switch with five Routes. All paths as being exported string variables are included in the employees_config.js file.

1.1.1.1.	“/employees” – path for routing Employees component
1.1.1.2.	“/title/statistics” - path for routing TitleStatistics component
1.1.1.3.	“/search” – path for routing EmployeesSearch component
1.1.1.4.	“/generation” – path for routing EmployeesGeneration component
1.1.1.5.	“/salary/statistics” - path for routing SalaryStatistics component

1.1.2.	Gets employee’s data as “Lifting State up” from Employees and EmployeesGeneration components. 

1.2.	EmployeesNav – Functional component for navigation (see OrdersNav as an example) according to the 1.1.1.1 – 1.1.1.5 paths described above

1.3.	Employees – Stateful component for maintaining the employee’s data (adds / removes employee objects).

1.3.1.	 Renders either EmployeesTable or EmployeeForm component
1.3.2.	 Gets updateEmployeesFn function as a property from App component (for lifting state up)
1.3.3.	Gets employees as a property from App component (for keeping the current employee’s data state from App component)

1.4.	TitleStatistics – renamed component EmployeesStatistics from the HW #27

1.5.	EmployeesTable – with no update from the HW #27

1.6.	EmployeeForm – Stateful component from the HW #27 with the following updates

1.6.1.	All parameters related to the input elements validation are imported from the file employees_config 
1.6.2.	All additional functions for getting Form input elements are imported from the file input_elements.js

1.7.	EmployeesSearch – Stateful component with an array of the employee objects matching the following inputted parameters of searching (this.state = {employeesSearch: []})

1.7.1.	Returns rendered  element containing
1.7.1.1.	Three input elements for inputting the following search parameters and clickable icon “Search” (magnifying glass). After pressing on “Search” icon there should be triggered getting the proper employee objects 
1.7.1.1.1.	Title name. If title name is not specified it will not be considered in the search request
1.7.1.1.2.	Salary From. If a salary “from” value is not specified or it greater than Salary To or it is a negative, the 0 will be implied.
1.7.1.1.3.	Salary To. If a salary “to” value is not specified the Number.MAX_VALUE will be implied 

1.7.1.2.	EmployeesTable component with attribute employees equaled to the array of the found employees (employees=this.state.employeesSearch)

1.8.	EmployeesGeneration – Stateful component that 
1.8.1.	Allows the user to input number of the generated random employees
1.8.2.	Generates entered number of the employees according to the configuration parameters imported from the file employees_config.js. 
1.8.3.	Lifts state up  - all employees should be passed to App component using updateEmployeesFn property

1.9.	SalaryStatistics – Stateful component that 

1.9.1.	Allows the user to enter salary interval value for getting salary statistics grouped by the ranges. For example, the inputted interval value is 5000 and minimal salary is the value equaled to 5000 imported from the employees_config.js file. Thus, the output will be as follows (maximal interval value is exclusive)
1.9.2.	5000 – 10000 :  <count of employees with salary from 5000 to 5999>
1.9.3.	10000 – 15000 :  <count of employees with salary from 10000 to 14999 >
1.9.4.	15000 – 20000 :  <count of employees with salary from 15000 to 19999 >
1.9.5.	20000 – 25000 :  <count of employees with salary from 20000 to 24999>
1.9.6.	25000 – 30000 :  <count of employees with salary from 25000 to 29999>
1.9.7.	30000 – 35000 :  <count of employees with salary from 30000 to 34999>
1.9.8.	Presents actual minimal salary
1.9.9.	Presents actual maximal salary
1.9.10.	Presents total salary budget

1.10.	employees.js – file containing exported configuration variables for importing to the appropriate components (see the architecture)

1.11.	input_elements.js – file containing exported functions for getting form input elements such as getInputElement, getRadioButtonElement, getSelectElement, getErrorMessageElement

1.12.	random.js – file containing exported functions getRandomNumber and getRandomElement
