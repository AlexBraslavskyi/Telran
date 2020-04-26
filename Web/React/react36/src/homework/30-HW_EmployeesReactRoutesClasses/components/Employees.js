import * as React from "react";
import '../sstylesheets/Employees.css';
import _ from 'lodash';
import EmployeesTable from "./EmployeesTable";
import EmployeeForm from "./EmployeeForm";

class Employees extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employeesSwitch: 0
        }
        this.employees = props.employees;
    }
    addEmployeeShow() {

        this.setState({
            employeesSwitch: 2
        })
    }
    addEmployee(employee) {

        const index = this.employees
            .findIndex(e => e.id === employee.id);
        if (index >= 0) {
            return false;
        }
        this.employees.unshift(employee);
        this.setState({
            employeesSwitch: 0
        });
        this.props.updateEmployeesFn(this.employees);
        return true;
    }


   removeEmployee(id) {
      _.remove(this.employees, e => e.id === id)  ;
      this.props.updateEmployeesFn(this.employees);

   }
    render() {

        switch(this.state.employeesSwitch){
            case 0: return <div className="employees">
                <EmployeesTable employees={this.employees}
                                removeFn={this.removeEmployee.bind(this)}/>
                <i style={{cursor: 'pointer'}}
                   className="fa fa-plus-square fa-3x"
                   onClick={this.addEmployeeShow.bind(this)}/>
            </div>

            case 2: return <EmployeeForm addEmployeeFn={this.addEmployee.bind(this)}/>
        }

    }
}
export default Employees;
