class Employees {
    constructor() {
        this.employees = {}
    }
    getPromise(value, timeout) {
            return new Promise(function(resolve) {
                setTimeout(function() {
                    resolve(value);
                }, timeout);
            })
    }
    addEmployee(employee) {
        if (this.employees[employee.id]) {
            return this.getPromise(false, 200);
        }
        employee.salary = +employee.salary;
        this.employees[employee.id] = employee;
        return this.getPromise(true, 1000);
    }

    removeEmployee(id) {
        delete this.employees[id];
        return this.getPromise(true, 2000);
    }

    computeSalaryBudget() {
        const allEmployees = Object.values(this.employees);
        const allSalaries = allEmployees.map(function (e) {
            return e.salary;
        });
        const budgetSalary = allEmployees.length != 0 ?
            allSalaries.reduce(function (budget, salary) {
                return budget + salary;
            }) : 0;
        return this.getPromise(budgetSalary, 3000);

    }
}
