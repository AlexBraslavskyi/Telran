async function addEmployeeServer(employee) {
    try {
       await $.ajax({
           url: this.url,
           contentType: 'application/json',
           type: 'POST',
           data: JSON.stringify(employee)
       });
       return 1; //OK
    } catch (error) {
        if (error.readyState == 0 || error.status == 404) {
            return -1; //URL is unavailable
        } else {
            return -2; //existing employee id
        }
    }
}
async function removeEmployeeServer(id) {
    try {
       await $.ajax({
           url: this.url + encodeURIComponent(id),
           type: 'DELETE'
       })
       return 1;
    } catch(error) {
        return -1;
    }
}
async function getAllEmployeesServer() {
    try {
      const employees = await $.ajax({
          url: this.url,
          type: 'GET'
      })
      return employees;
    }catch (error) {
        return -1;
    }
}
async function computeSalaryServer() {
    const allEmployees = await getAllEmployeesServer.call(this);
    if (!allEmployees) {
        return 0;
    }
    if ( allEmployees == -1) {
        return -1;
    }

    const allSalaries = allEmployees.map(function (e) {
        return +e.salary;
    });
    const budgetSalary = allEmployees.length != 0 ?
        allSalaries.reduce(function (budget, salary) {
            return budget + salary;
        }) : 0;
    return budgetSalary;
}
class Employees {
    constructor(url) {
        if (!url) {
            throw new Error('url is not defined')
        }
        this.url = url;
    }

    addEmployee(employee) {
        return addEmployeeServer.call(this, employee);
    }

    removeEmployee(id) {
        return removeEmployeeServer.call(this, id);
    }
    getAllEmployees() {
        return getAllEmployeesServer.call(this);
    }

    computeSalaryBudget() {
       return computeSalaryServer.call(this);

    }
}
