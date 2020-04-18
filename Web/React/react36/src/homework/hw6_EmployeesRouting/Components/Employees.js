import * as React from "react";
import _ from 'lodash';
import EmployeesTable from "./EmployeesTable";
import EmployeeForm from "./EmployeeForm";
import {getRandomEmployee} from "../utils/Random";

class Employees extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employeesSwitch: 0
        }
        this.employees = this.props.employees;
    }
    addEmployeeShow() {
        this.setState({
            employeesSwitch: 1
        })
    }
    genEmployee=() =>{
        const employee = getRandomEmployee();
        const employees = this.props.employees;
        const index = employees
            .findIndex(e => e.id === employee.id);
        if (index < 0) {
            employees.unshift(employee);
            this.setState({
                employees,
            })
        } else this.genEmployee();
    }
    addEmployee(employee) {
        const employees = this.employees;
        const index = employees
            .findIndex(e => e.id === employee.id);
        if (index >= 0) {
            return false;
        }
        employees.unshift(employee);
        this.props.updateEmployeesFn(this.employees);
        this.setState({
            employeesSwitch: 0
        })
        return true;
    }
  
   removeEmployee(id) {
        console.log(id);
        const employees = this.employees;
      _.remove(employees, e => e.id == id)  ;
       this.props.updateEmployeesFn(this.employees);
   }
    render() {
        if(this.props.employees.length>0) {
            switch (this.state.employeesSwitch) {
                case 0:
                    return <div className="form-group">
                        <div className='btn btn-group'>
                            <button style={{cursor: 'pointer'}} type="button" className="btn btn-success"
                                    onClick={this.addEmployeeShow.bind(this)}><i className="fa fa-user-plus fa-2x"/>Add
                                Employee
                            </button>
                            <button style={{cursor: 'pointer'}} type="button" className="btn btn-primary"
                                    onClick={this.genEmployee.bind(this)}><i className="fa fa-users fa-2x"/>Generate
                                Employee
                            </button>
                        </div>
                        <EmployeesTable employees={this.employees}
                                        removeFn={this.removeEmployee.bind(this)}/>

                    </div>
                case 1:
                    return <EmployeeForm addEmployeeFn={this.addEmployee.bind(this)}/>
            }
        }else{
            switch (this.state.employeesSwitch) {
                case 0:
                    return <div className="form-group">
                        <div className='btn btn-group'>
                            <button style={{cursor: 'pointer'}} type="button" className="btn btn-success"
                                    onClick={this.addEmployeeShow.bind(this)}><i className="fa fa-user-plus fa-2x"/>Add
                                Employee
                            </button>
                            <button style={{cursor: 'pointer'}} type="button" className="btn btn-primary"
                                    onClick={this.genEmployee.bind(this)}><i className="fa fa-users fa-2x"/>Generate
                                Employee
                            </button>
                        </div>
                    </div>
                case 1:
                    return <EmployeeForm addEmployeeFn={this.addEmployee.bind(this)}/>
            }
        }
    }
}
export default Employees;
