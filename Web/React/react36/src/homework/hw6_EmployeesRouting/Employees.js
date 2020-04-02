import {getRandomNumber, getRandomElement} from "../random";
import * as React from "react";
import './Employees.css';
import _ from 'lodash';
import EmployeesStatistics from "./EmployeesStatistics";
import EmployeesTable from "./EmployeesTable";
import EmployeeForm from "./EmployeeForm";

class Employees extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: [],
            employeesSwitch: 0
        }
    }
    addEmployeeShow() {

        this.setState({
            employeesSwitch: 2
        })
    }
    addEmployee(employee) {
        const employees = this.state.employees;
        const index = employees
            .findIndex(e => e.id === employee.id);
        if (index >= 0) {
            return false;
        }
        employees.unshift(employee);
        this.setState({
            employees,
            employeesSwitch: 0
        })
        return true;
    }
  
   removeEmployee(id) {
        console.log(id);
        const employees = this.state.employees;
      _.remove(employees, e => e.id == id)  ;
      this.setState({
          employees
      })
   }
    render() {

        switch(this.state.employeesSwitch){
            case 0: return <div className="employees">
                <EmployeesTable employees={this.state.employees}
                                removeFn={this.removeEmployee.bind(this)}></EmployeesTable>
                <i style={{cursor: 'pointer'}}
                   className="fa fa-plus-square fa-3x"
                   onClick={this.addEmployeeShow.bind(this)}></i>
            </div>
           
            case 2: return <EmployeeForm addEmployeeFn={this.addEmployee.bind(this)}></EmployeeForm>
        }

    }
}
export default Employees;
