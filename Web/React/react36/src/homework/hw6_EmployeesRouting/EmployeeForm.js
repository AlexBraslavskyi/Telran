import React from 'react';
import {minSalary,maxSalary,titlesArr,digitsId,genders,nameMinLength} from "./EmployeesConfig";
import {getInputElement,getRadioButtonElement, getSelectElement,getErrorMessage,getButtonElement} from "./InputElements";
const letters = /^[A-Za-z]+$/;


export default class EmployeeForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employee: {
                id: 0,
                emailAddress: '',
                name: '',
                gender: '',
                salary: 0,
                title: titlesArr[0]
            },
            error: {}
        };
        this.submit = this.submit.bind(this);
        this.handlerId = this.handlerId.bind(this);
        this.handlerName = this.handlerName.bind(this);
        this.handlerSalary = this.handlerSalary.bind(this);
        this.handlerNonValidated = this.handlerNonValidated.bind(this);
    }
    submit(event) {
        event.preventDefault();
        if (!this.props.addEmployeeFn(this.state.employee)) {
            this.state.error.errorId =
                `Employee ${this.state.employee.id} already exists`;
            this.setState({
                error: this.state.error
            })
        }
    }
    handlerId(event) {
        const id = event.target.value;
        const error = this.state.error;
        error.errorId = '';
        const employee = this.state.employee;
        if (id.length != digitsId) {
            error.errorId = 'Number of ID digits should be ' + digitsId;

        } else if(+id < 0) {
            error.errorId = 'id can\'t be negative number';
        } else if(id.indexOf(".") >= 0){
            error.errorId = 'id can\'t be fraction number';
        } else if(id.substr(0,1)=="0"){
            error.errorId = 'id can\'t start from 0';
        } else {
            employee.id = id;
        }
        this.setState({
            error, employee
        })
    }
    handlerName(event) {
        const name = event.target.value;
        const error = this.state.error;
        error.errorName = '';
        const employee = this.state.employee;
        if (name.length < nameMinLength) {
            error.errorName = 'Minimal name length should be '
                + nameMinLength
        }  else if(!name.match(letters)) {
            error.errorName = 'Name mast content only english letters';
        }
        else {
            employee.name = name;
            this.fillEmployeeAddress();
        }
        this.setState({
            error, employee
        })
    }
    handlerNonValidated(event) {
        this.state.employee[event.target.name] = event.target.value;
        this.setState({
            employee: this.state.employee
        })
    }
    handlerSalary(event) {
        const salary = +event.target.value;
        this.state.error.errorSalary = ''
        if (salary < minSalary || salary > maxSalary) {
            this.state.error.errorSalary =
                `salary should be in rang [${minSalary}-${maxSalary}`;
        } else {
            this.state.employee.salary = salary;
        }
        this.setState({
            error: this.state.error,
            employee: this.state.employee
        })

    }
    fillEmployeeAddress() {
        if (this.state.employee.id && this.state.employee.name) {
            if (!this.state.employee.emailAddress) {
                this.state.employee.emailAddress = '@';
            }
            const emailAddressFirstPart =
                this.state.employee.name +
                this.state.employee.id.substr(0, 3);
            const index = this.state.employee.emailAddress.indexOf("@");
            const emailAddressSecondPart =
                this.state.employee.emailAddress.substr(index);
            this.state.employee.emailAddress = emailAddressFirstPart +
                emailAddressSecondPart;
            this.setState({
               employee: this.state.employee
            })
        }
    }
    validate() {
        const res =  this.notErrors() && this.allFields();
        console.log(this.state);
        return res;
    }
    render() {
        const genderRadioButtons =
            genders.map(
                g => getRadioButtonElement('gender',
                    this.handlerNonValidated, g)
            )

        return <div className="card">
            <div className="card-header">
                <h2>Employee Data Form</h2>
            </div>
            <div className="card-body">
                <form onSubmit={this.submit}>
                    <div className = 'firstLevel'>
                    {getInputElement('number',
                        'id', 'Enter Employee ID',
                        this.handlerId)}
                    {getErrorMessage(this.state.error.errorId)}

                    {getInputElement('text',
                        'name', 'Enter Employee Name',
                        this.handlerName)}
                    {getErrorMessage(this.state.error.errorName)}
                    {getInputElement('email',
                        'emailAddress', 'Enter email address',
                        this.handlerNonValidated, this.state.employee.emailAddress)}
                    </div>
                    <div className = 'secondLevel'>
                    {genderRadioButtons}
                    </div>
                    <div className = 'thirdLevel'>
                    {getInputElement('number', 'salary',
                    'Enter Salary', this.handlerSalary)}
                    {getErrorMessage(this.state.error.errorSalary)}
                    {getSelectElement('title', this.handlerNonValidated,
                        'Select Title', titlesArr)}
                        {/*{getButtonElement('Add Employee','','submit','!this.validate()')}*/}
                        <button type="submit" disabled={!this.validate()}>Add Employee</button>
                    </div>
                </form>
            </div>
        </div>
    }

    notErrors() {
        return Object.values(this.state.error)
            .reduce((res, field) => {
                return res && !field
            } , true)
    }

    allFields() {
        return Object.values(this.state.employee)
            .reduce((res, field) => {
                return res && field
            } , true)
    }
}
