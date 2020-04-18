import React from 'react';
import {minSalary,maxSalary,titlesArr,digitsId,genders,nameMinLength} from "../config/EmployeesConfig";
import {
    getInputElement,
    getInputElementBlur,
    getInputElementInp,
    getRadioButtonElement,
    getSelectElement
} from "../utils/InputElements";

const letters = /^[A-Za-z]+$/;


export default class EmployeeForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employee: {
                id: '',
                emailAddress: '',
                name: '',
                gender: '',
                salary: '',
                title: titlesArr[0],
            },
                id: {value:'Enter ID',controlError: 0},
                name: {value:'Enter Name',controlError: 0},
                salary: {value:'Enter Salary',controlError: 0},
                emailAddress: {value:'',controlError: 0},
            error: {},
        };
        this.formRef = null;
        this.submit = this.submit.bind(this);
        this.handlerId = this.handlerId.bind(this);
        this.handlerName = this.handlerName.bind(this);
        this.handlerSalary = this.handlerSalary.bind(this);
        this.handlerEmail= this.handlerEmail.bind(this);
        this.handlerNonValidated = this.handlerNonValidated.bind(this);
    }
    submit(event) {
        event.preventDefault();
        if (!this.props.addEmployeeFn(this.state.employee)) {
            this.state.error.errorId =
                `Employee ${this.state.employee.id} already exists`;
            this.state.controlError = -1;
            this.setState({
                error: this.state.error,
            })
            this.formRef.reset();
        }
    }
    handlerId(event) {
        const id = event.target.value;
        const error = this.state.error;
        error.errorId = '';
        this.state.id.controlError = 0;
        const employee = this.state.employee;
        if(id==""){
            this.state.id.controlError = 0;
            this.state.id.value=id;
            this.state.employee.id = id;
        }else if (id.length != digitsId) {
            error.errorId = 'Number of ID digits should be ' + digitsId;
            this.state.id.controlError = -1;
            this.state.id.value=id;
            this.state.employee.id = id;
        } else if(+id < 0) {
            error.errorId = 'id can\'t be negative number';
            this.state.id.controlError = -1;
            this.state.id.value=id;
            this.state.employee.id = id;
        } else if(id.indexOf(".") >= 0){
            error.errorId = 'id can\'t be fraction number';
            this.state.id.controlError = -1;
            this.state.id.value=id;
            this.state.employee.id = id;
        } else if(id.substr(0,1)=="0"){
            error.errorId = 'id can\'t start from 0';
            this.state.id.controlError = -1;
            this.state.id.value=id;
            this.state.employee.id = id;
        } else {
            this.state.employee.id = id;
            this.state.id.value=id;
            this.state.id.controlError = 1;
        }
        this.setState({
            error, employee,
        })
    }
    handlerName(event) {
        this.state.name.controlError = 0;
        const name = event.target.value;
        const error = this.state.error;
        error.errorName = '';
        const employee = this.state.employee;
        if(name ==""){
            this.state.name.controlError = 0;
            this.state.employee.name = name;
            this.state.name.value = name;
        }else if (name.length < nameMinLength) {
            error.errorName = 'Minimal name length should be '
                + nameMinLength+'symbol';
            this.state.name.controlError = -1;
            this.state.employee.name = name;
            this.state.name.value = name;
        }  else if(!name.match(letters)) {
            error.errorName = 'Name mast content only english letters';
            this.state.name.controlError = -1;
            this.state.employee.name = name;
            this.state.name.value = name;
        }
        else {
            this.state.employee.name = name;
            this.state.name.controlError = 1;
            this.state.name.value = name;
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
    handlerEmail(event) {

        const email = event.target.value;
        const error = this.state.error;
        error.errorEmail = '';
        const format = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
        const employee = this.state.employee;
        let newID = this.state.id.value.toString();
        newID = newID.substr(0,3);
        let name = this.state.name.value;
        console.log(name,newID);
        console.log(email)
        if (email == "") {
            this.state.emailAddress.controlError = 0;
            this.state.employee.emailAddress = email;
            this.state.emailAddress.value = email;
        }else if(email == name+newID+'@') {
            error.errorEmail = 'Wrong email format, please enter domain';
            this.state.emailAddress.controlError = -1;
            this.state.employee.emailAddress = email;
            this.state.emailAddress.value = email;
        } else if (!format.test(email)) {
            error.errorEmail = 'Wrong email format';
            this.state.emailAddress.controlError = -1;
            this.state.employee.emailAddress = email;
            this.state.emailAddress.value = email;
        } else {
            this.state.employee.emailAddress = email;
            this.state.emailAddress.controlError = 1;
            this.state.emailAddress.value = email;
        }
        this.setState({
            error, employee
        })
    }

    handlerSalary(event) {
        const salary = event.target.value;
        this.state.salary.controlError = 0;
        this.state.error.errorSalary = ''
        if (salary < minSalary || salary > maxSalary) {
            this.state.error.errorSalary =
                `salary should be in rang [${minSalary}-${maxSalary}]`;
            this.state.salary.controlError = -1;
            this.state.salary.value = salary;
            this.state.employee.salary = salary;
        } else {
            this.state.employee.salary = salary;
            this.state.salary.value = salary;
            this.state.salary.controlError = 1;
        }
        this.setState({
            error: this.state.error,
            employee: this.state.employee,
        })

    }
    fillEmployeeAddress() {
        if (this.state.id && this.state.name) {
            if (!this.state.emailAddress.value) {
                this.state.emailAddress.value = '@';
            }
            const emailAddressFirstPart =
                this.state.name.value +
                this.state.id.value.substr(0, 3);
            const index = this.state.emailAddress.value.indexOf("@");
            const emailAddressSecondPart =
                this.state.emailAddress.value.substr(index);
            this.state.emailAddress.value = emailAddressFirstPart +
                emailAddressSecondPart;
            this.setState({
               employee: this.state.employee
            })
        }
    }
    validate() {
        const res =  this.notErrors() && this.allFields();
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
                <form ref={(ref) => this.formRef = ref} onSubmit={this.submit}>
                    <div className = 'firstLevel'>
                    {getInputElement('number',
                        'id', 'Employee ID',
                        this.handlerId,this.state.error.errorId,this.state.id)}
                    {getInputElement('text',
                        'name', 'Employee Name',
                        this.handlerName,this.state.error.errorName,this.state.name)}
                    {getInputElementBlur('email',
                        'emailAddress', 'Email address',
                        this.handlerEmail, this.state.error.errorEmail,this.state.emailAddress)}
                    </div>
                    <div className = 'secondLevel'>
                    {genderRadioButtons}
                    </div>
                    <div className = 'thirdLevel'>
                    {getInputElement('number', 'salary',
                    'Salary', this.handlerSalary,this.state.error.errorSalary,this.state.salary)}
                    {getSelectElement('title', this.handlerNonValidated,
                        'Select Title', titlesArr)}

                    </div>
                    <div className='form-group'>
                    <div className='btn btn-group'>
                    <button type="submit" className="btn btn-success" disabled={!this.validate()}>
                        <i className="fa fa-user-plus fa-2x"/>Add Employee</button>
                    </div>
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
