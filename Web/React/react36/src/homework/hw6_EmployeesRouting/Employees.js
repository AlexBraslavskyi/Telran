import * as React from "react";
import './Employees.css';
import _ from 'lodash';
import EmployeesTable from "./EmployeesTable";
import EmployeeForm from "./EmployeeForm";

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
console.log(this.employees)
        switch(this.state.employeesSwitch){
            case 0: return <div className="employees">
                <EmployeesTable employees={this.employees}
                                removeFn={this.removeEmployee.bind(this)}/>
                <i style={{cursor: 'pointer'}}
                   className="fa fa-plus-square fa-3x"
                   onClick={this.addEmployeeShow.bind(this)}/>
            </div>
           
            case 1: return <EmployeeForm addEmployeeFn={this.addEmployee.bind(this)}/>
        }

    }
}
export default Employees;
