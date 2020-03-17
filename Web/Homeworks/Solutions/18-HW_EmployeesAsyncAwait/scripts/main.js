const formEmployee = new FormHandler('#form_employee');
const thEmployeesElement = document.getElementById('thEmployees');
const tBodyElement = document.getElementById('tBodyEmployees')
const employees = new Employees();
const tableEmployees = new Table(['id', 'emailAddress',
        'name', 'salary', 'gender', 'title'], thEmployeesElement, tBodyElement,
    'id', async function (id) {
        startSpinner();
        await employees.removeEmployee(id);
        const budget = await employees.computeSalaryBudget();
        budgetElement.textContent = '' + budget;
        stopSpinner();
    });
const budgetElement = document.getElementById('total-salary')
const formGeneration = new FormHandler('#form-generation');
formEmployee.addHandler(async function (employee) {
    startSpinner();
    const addRes = await employees.addEmployee(employee);
    stopSpinner();
    if (addRes) {
        tableEmployees.addRow(employee);
    }
    return addRes ? '' :
        `employee with id: ${employee.id} already exists`;
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

    if (nDigits < 1 || nDigits > 5) {
        return 'number of ID digits should be in the range [1-5]';
    }
    if (nEmployees < 1) {
        return 'number of employees can\'t be less than 1';
    }
    const maxEmployees = 10 ** nDigits - 10 ** (nDigits - 1);
    if (nEmployees > maxEmployees) {
        return 'number of employees can\'t be more than ' + maxEmployees
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

async function addRandomEmployee(generationData) {
    let res = false;
    const limit = 10 ** generationData.nDigits;
    let count = 0;
    do {
        count++;
        const employee = getRandomEmployee(generationData);
        res = await employees.addEmployee(employee);
        if (res) {
            tableEmployees.addRow(employee);
        }
    } while (!res && count < limit);
}

async function generateRandomEmployees(generationData) {
    startSpinner();
    generationDataToNumbers(generationData);
    const errorMessage = validateGenerationData(generationData);
    if (!errorMessage) {
        for (let i = 0; i < generationData.nEmployees; i++) {
            await addRandomEmployee(generationData);
        }
        const budget = await employees.computeSalaryBudget();
        budgetElement.textContent = '' + budget;

    }
    stopSpinner();
    return new Promise(function (resolve) {
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
        male: ['Moshe', 'David', 'Yosef', 'Asaf',
            'Lee', 'Ivan', 'Nik', 'Yuri', 'Igor'],
        female: ['Sara', 'Rivka', 'Klara', 'Olya', 'Irina', 'Vera',
            'Katya', 'Rita', 'Ida', 'Elena']
    }
    return getRandomElement(genderNames[gender]);
}

function getRandomEmail(id, name) {
    const domains = [
        'gmail.com', 'co.il', 'mail.ru'
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
