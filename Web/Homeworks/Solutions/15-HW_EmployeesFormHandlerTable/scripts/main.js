const formEmployee = new FormHandler('#form_employee');
const thEmployeesElement = document.getElementById('thEmployees');
const tBodyElement = document.getElementById('tBodyEmployees')
const tableEmployees = new Table(['id', 'emailAddress',
'name', 'salary', 'gender', 'title'], thEmployeesElement, tBodyElement);
const budgetElement = document.getElementById('total-salary')
const employees = new Employees();
formEmployee.addHandler(function(employee) {
    const res = employees.addEmployee(employee);
    if (!res) {
        return `employee with id: ${employee.id} already exists`
    }
    tableEmployees.addRow(employee);
    const budget = employees.computeSalaryBudget();
    budgetElement.textContent = '' + budget;

});
