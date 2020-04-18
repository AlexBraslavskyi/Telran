import React from "react";
import {getRandomEmployee} from "../utils/Random";
import {getInputElement} from "../utils/InputElements";


class EmployeesGenerations extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: this.props.employees,
            count: {value:'',controlError: 0},
            error:'',
            message: false,
        }
        this.formRef = null;
        this.handlerCount = this.handlerCount.bind(this);
        this.genEmployee = this.genEmployee.bind(this);
        this.submit = this.submit.bind(this);
    }
    handlerCount(event) {
        const inputCount = event.target.value;
        this.state.error = '';
        this.state.count.controlError = 0;
         if(inputCount<=0||inputCount>99){
             this.state.error = 'Count mast be in range [1-99]';
             this.state.count.value = inputCount;
             this.state.count.controlError = -1;
        } else if(inputCount.indexOf(".") >= 0){
            this.state.error = 'Count can\'t be fraction number';
            this.state.count.controlError = -1;
             this.state.count.value = inputCount;
        } else if(inputCount.substr(0,1)=="0"){
            this.state.error = 'Count can\'t start from 0';
            this.state.count.controlError = -1;
             this.state.count.value = inputCount;
        } else if(inputCount>0&&inputCount<=99) {
            this.state.error = '';
            this.state.count.value = inputCount;
            this.state.count.controlError = 1;
        }
            this.setState({
                error: this.state.error,
                count:{value:this.state.count.value,controlError:this.state.count.controlError},
            })
    }
    genEmployee=() =>{
            const employee = getRandomEmployee();
            const employees = this.state.employees;
            const index = employees
                .findIndex(e => e.id === employee.id);
            if (index < 0) {
            employees.unshift(employee);
            this.setState({
                employees,
            })
        } else this.genEmployee();
    }
    submit (event){
        event.preventDefault();
        let submitConfirm = true;
        if (this.state.error) {
            submitConfirm = false;}
        if (submitConfirm) {
            for (let i = 0; i < this.state.count.value; i++) {
                this.genEmployee();
            }

            this.state.message = `${this.state.count.value} employees was generated!`;
        }

        this.props.updateEmployeesFn(this.state.employees);
            this.setState({
                message: this.state.message,
                count: {value:'',controlError: 0},
            });
        this.formRef.reset();

    }

    render() {
        return       <div className="card">
        <div className="card-header">
            <h1>Generations Employees</h1>
                <div className="alert alert-info">
                    Current employees count: {this.props.employees.length}
                </div>
            <div className="center">
                <form ref={(ref) => this.formRef = ref} className='form-group' onSubmit={this.submit}>
                {getInputElement('number','Count','Count employees',
                this.handlerCount,this.state.error,this.state.count)}
                        <button type="submit" style={{cursor: 'pointer'}} className="btn btn-primary"disabled=
                            {this.state.count.controlError ===0||this.state.count.controlError ===-1}>
                            <i className="fa fa-users fa-2x"/>Generate Employee</button>
                    <div>
                    {this.state.message ? (
                        <div className="alert alert-success">{this.state.message}
                        </div>
                    ) : null}
                    </div>
                </form>
            </div>
        </div>
        </div>
    }
}
            export default EmployeesGenerations;
