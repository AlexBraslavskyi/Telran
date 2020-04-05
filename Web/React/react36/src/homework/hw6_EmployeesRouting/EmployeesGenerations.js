import React from "react";
import {getRandomNumber, getRandomElement} from "./Random";
import {maxSalary,minSalary,genders,digitsId} from "./EmployeesConfig";
import {getInputElement, getButtonElement, getErrorMessage} from "./InputElements";


class EmployeesGenerations extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: [],
            count:'',
            error:{}
        }
        this.handlerCount = this.handlerCount.bind(this);
    }
    handlerCount(event) {
        const count = event.target.value;
        const error = this.state.error;
        error.errorCount = '';
        if(+count < 0) {
            error.errorCount = 'count can\'t be negative number';
        } else if(count.indexOf(".") >= 0){
            error.errorCount = 'count can\'t be fraction number';
        } else if(count.substr(0,1)=="0"){
            error.errorCount = 'count can\'t start from 0';
        } else {
            this.count = count;
        }
            this.setState({
                error: this.state.error,
                employee: this.state.employee
            })
    }
    addGenEmployee() {
        for (let i = 0;i<this.count;i++) {
            const employee = getRandomEmployee();
            const employees = this.state.employees;
            const index = employees
                .findIndex(e => e.id === employee.id);
            if (index >= 0) {
                return false;
            }
            employees.unshift(employee);
            this.props.updateEmployeesFn(employees);
        }

        return true;
    }

    render() {
        return <div className="generations">
            <h1>Generations Employees</h1>
            <div>
                {getErrorMessage(this.state.error.errorCount)}
            {getInputElement('number','countGenEmployees','Count employees',this.handlerCount)}

                {getButtonElement('Generate',this.addGenEmployee.bind(this))}


            </div>
        </div>
    }
}

            function getRandomEmployee() {
            const id = getRandomNumber(10 ** (digitsId - 1), 10 ** digitsId - 1);
            const gender = getRandomElement(genders);
            const name = getRandomName(gender);
            const emailAddress = name+id+'@gmail.com';
            const salary = getRandomNumber(minSalary, maxSalary);
            const title = getRandomTitle();
            return {id, name, emailAddress, gender, salary, title}

        }

            function getRandomName(gender) {
            const genderNames = {
            male: ['Moshe', 'David', 'Yosef', 'Asaf', 'Ivan',  'Yuri', 'Igor', 'Alex'],
            female: ['Sara', 'Rivka', 'Klara', 'Olya', 'Irina', 'Vera',
            'Katya', 'Rita', 'Elena']
        }
            return getRandomElement(genderNames[gender]);
        }


            function getRandomTitle() {
            const titles = ['Developer' ,'Development Manager', 'QA Tester', 'QA Manager', 'Sales Person', 'Sales Manager']
            return getRandomElement(titles);
        }

            export default EmployeesGenerations;
