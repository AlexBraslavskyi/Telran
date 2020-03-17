const formEmployee = new FormHandler('#form_employee');
const thEmployeesElement = document.getElementById('thEmployees');
const tBodyElement = document.getElementById('tBodyEmployees')
const employees = new Employees();
const tableEmployees = new Table(['id', 'emailAddress',
        'name', 'salary', 'gender', 'title'], thEmployeesElement, tBodyElement,
    'id', function (id) {
       // startSpinner()
        return employees.removeEmployee(id).then(function(){
             return employees.computeSalaryBudget()
                .then(function(budget) {
                    stopSpinner();
                    budgetElement.textContent = '' + budget;

                });

        });

    });
const budgetElement = document.getElementById('total-salary')
const formGeneration = new FormHandler('#form-generation');
formEmployee.addHandler(function (employee) {
    startSpinner()
    employees.addEmployee(employee)
        .then(function (res) {
            stopSpinner();
            if (!res) {
                return `employee with id: ${employee.id} already exists`
            }
            tableEmployees.addRow(employee);
            employees.computeSalaryBudget()
                .then(function(budget) {
                    budgetElement.textContent = '' + budget;
                });
            return '';
        });



});
formGeneration.addHandler(generateRandomEmployees);

function generationDataToNumbers(generationData) {
    generationData.nEmployees = +generationData.nEmployees;
    generationData.nDigits = +generationData.nDigits;
    generationData.minSalary = +generationData.minSalary;
    generationData.maxSalary = +generationData.maxSalary;
}

function validateGenerationData(generationData) {
    const nEmployees = generationData.nEmployees;
    const nDigits = generationData.nDigits;
    const minSalary = generationData.minSalary;
    const maxSalary = generationData.maxSalary;
    if (nEmployees < 1 || nEmployees > 100) {
        return 'number of employees should be in the range [1-100]';
    }
    if (nDigits < 3 || nDigits > 5) {
        return 'number of ID digits should be in the range [3-5]';
    }
    if (minSalary < 0) {
        return 'salary can\'t be a negative number'
    }
    if (maxSalary < minSalary) {
        return 'maximal value can\'t be less than minimal one';
    }
    return '';
}

function getRandomEmployee(generationData) {
    const id = getRandomId(generationData.nDigits);
    const gender = getRandomElement(['male', 'female']);
    const name = getRandomName(gender);
    const emailAddress = getRandomEmail(id, name);
    const salary = getRandomNumber(generationData.minSalary,
        generationData.maxSalary);
    const title = getRandomTitle();
    return {id, emailAddress, name, gender, salary, title};

}

function generateRandomEmployees(generationData) {
    startSpinner();
    generationDataToNumbers(generationData);
    const errorMessage = validateGenerationData(generationData);
    if (!errorMessage) {
        for (let i = 0; i < generationData.nEmployees - 1; i++) {
            let res = true;
            do { //this loop doesn't work
                const employee = getRandomEmployee(generationData);
               employees.addEmployee(employee)
                   .then(function(res) {
                       if (res) {
                           tableEmployees.addRow(employee);
                       }
                   });
            }while(!res);
        }
        const employee = getRandomEmployee(generationData);
        employees.addEmployee(employee)
            .then(function(res) {
                stopSpinner();
                if (res) {
                    tableEmployees.addRow(employee);
                }
                employees.computeSalaryBudget()
                    .then(function(budget) {
                        budgetElement.textContent = '' + budget;
                    });
            });
    }
    return new Promise(function(resolve) {
        resolve(errorMessage);
    })
}


function addNewEmployee() {
    $('#employee-data').attr('hidden', false);
    $('#employees-data-generation').attr('hidden', true);
    $('#employees-table').attr('hidden', true);
}

function generateEmployees() {
    $('#employee-data').attr('hidden', true);
    $('#employees-data-generation').attr('hidden', false);
    $('#employees-table').attr('hidden', true);
}

function showEmployees() {
    $('#employee-data').attr('hidden', true);
    $('#employees-data-generation').attr('hidden', true);
    $('#employees-table').attr('hidden', false);
}
function getRandomId(nDigits) {
    const minId = 10 ** (nDigits - 1);
    const maxId = 10 ** nDigits - 1;
    return getRandomNumber(minId, maxId);
}
function getRandomName(gender) {
   const genderNames = {
       male: ['Moshe', 'David', 'Yosef',  'Asaf',
           'Lee', 'Ivan', 'Nik', 'Yuri', 'Igor'],
       female: ['Sara', 'Rivka', 'Klara', 'Olya', 'Irina', 'Vera',
       'Katya', 'Rita', 'Ida', 'Elena']
   }
   return getRandomElement(genderNames[gender]);
}
function getRandomEmail(id, name) {
    const domains = [
        'gmail.com', 'co.il','mail.ru'
    ]
    return name + id + '@' + getRandomElement(domains);
}
function getRandomTitle() {
    const titles = [
        'WageEmployee', 'Manager', 'SalesPerson', 'SalesManager'
    ]
    return getRandomElement(titles);
}
function startSpinner() {
    $('#spinner').attr('hidden', false);
}
function stopSpinner() {
    $('#spinner').attr('hidden', true);
}
