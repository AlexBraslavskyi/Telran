import React, {useState} from "react";
import {getRandomElement, getRandomNumber} from "../util/random";
import {
    DIGITS_ID,
    TITLES,
    MAX_SALARY,
    MIN_SALARY,
    NAMES,
    GENDERS,
    MAX_EMPLOYEES_ONE_GENERATION
} from '../config/employees_config'
import {getErrorMessage, getInputElement} from "../util/input_elements";

function getEmployee() {
    const id = getRandomNumber(10 ** (DIGITS_ID - 1), 10 ** DIGITS_ID - 1 );
    const title = getRandomElement(TITLES);
    const gender = getRandomElement(GENDERS);
    const name = getRandomElement(NAMES[gender]);
    const salary = getRandomNumber(MIN_SALARY, MAX_SALARY);
    const emailAddress = name + id + '@' + 'tel-ran.co.il'
    return{id, title, gender, salary, name, emailAddress};
}
const EmployeesGeneration = (props) => {
    const employees = props.employees;
    let nEmployees, setNEmployees, error, setError;
    [nEmployees, setNEmployees] = useState(0);
    [error, setError] = useState('');
    function inputHandler(event) {
        event.preventDefault();
        nEmployees = event.target.value;
        setError('');
        if (nEmployees < 1) {
            setError('amount can not be less than 1');

        } else if(nEmployees > MAX_EMPLOYEES_ONE_GENERATION) {
            setError('amount can not be more than ' + MAX_EMPLOYEES_ONE_GENERATION);

        } else {
            setNEmployees(nEmployees)
        }
    }
    function onSubmit(event) {
        event.preventDefault();
        let count = 0;
        for (let i = 0; i < nEmployees; i++) {
            const employee = getEmployee();
            if(!employees.find(e => e.id === employee.id)){
                employees.push(employee);
                count++
            }

        }
        alert(`${count} employees have been generated`);
        event.target.reset();
        props.updateEmployeesFn(employees)
    }
    return <div className="card">
        <div className="card-header">
            <h3>Generation Courses</h3>
        </div>
        <div className="card-body">
            <form onSubmit={onSubmit}>
                {getInputElement
                ("number",
                    'nEmployees', "Enter number of Employees", inputHandler )}
                {getErrorMessage(error)}
                <button hidden={error ||
                !nEmployees}>Generate</button>
            </form>
        </div>
    </div>
}
export default  EmployeesGeneration ;
