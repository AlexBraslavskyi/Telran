import React from "react";
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

export default class EmployeesGeneration extends React.Component{
    constructor(props) {
        super(props);
        this.employees = props.employees;
        this.state = {
            nEmployees: 0,
            error: ''
        };
        this.inputHandler = this.inputHandler.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

    }

    inputHandler(event) {
        event.preventDefault();
        const nEmployees = event.target.value;
        this.setState({
            error: ''
        });
        if (nEmployees < 1) {
            this.setState({
                error: 'amount can not be less than 1'
            })
        } else if(nEmployees > MAX_EMPLOYEES_ONE_GENERATION) {
            this.setState({
                error: 'amount can not be more than ' + MAX_EMPLOYEES_ONE_GENERATION
            })
        } else {
            console.log(this.state);
            this.setState({
                nEmployees
            })
        }
    }

    onSubmit(event) {
        event.preventDefault();
        let count = 0;
        for (let i = 0; i < this.state.nEmployees; i++) {
            const employee = getEmployee();
            if(!this.employees.find(e => e.id === employee.id)){
                this.employees.push(employee);
                count++
            }

        }
        alert(`${count} employees have been generated`);
        event.target.reset();
        this.props.updateEmployeesFn(this.employees)
    }

    render() {
        return <div className="card">
            <div className="card-header">
                <h3>Generation Courses</h3>
            </div>
            <div className="card-body">
                <form onSubmit={this.onSubmit}>
                    {getInputElement
                    ("number",
                        'nEmployees', "Enter number of Employees", this.inputHandler )}
                    {getErrorMessage(this.state.error)}
                    <button hidden={this.state.error ||
                    !this.state.nEmployees}>Generate</button>
                </form>
            </div>
        </div>
    }

}
