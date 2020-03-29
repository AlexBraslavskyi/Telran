import React from "react";
import _ from 'lodash';
import EmployeesStatistics from "./EmployeesStatistics";
import EmployeesTable from "./EmployeesTable";
import {getRandomEmployee} from "./GetRandomEmployee";

class Employees extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: [], flStatistics: false
        }
    }
    addEmployee() {
        const employee = getRandomEmployee();
        const employees = this.state.employees;
        employees.unshift(employee);
        this.setState({
            employees
        })
    }
    removeEmployee(id) {
        const employees = this.state.employees;
        if (window.confirm(`You are going to delete employee ${id}`)) {
            _.remove(employees, employee => employee.id == id)
            this.setState({employees});
            return true;
        }
    }
    changeFlag(){
        if(!this.state.flStatistics){
    this.setState({flStatistics:true})}
        else {
            this.setState({flStatistics:false})
        }
}
    render() {
        if (!this.state.flStatistics) {
            return <div>
                <EmployeesTable employees = {this.state.employees}
                                removeFn={this.removeEmployee.bind(this)}/>
                <div style={{"textAlign": "center"}}>
                    <i style={{"cursor": "pointer"}} onClick={this.addEmployee.bind(this)} className="fa fa-plus-square fa-2x">
                        Add Employee</i>
                    <button style={{"marginLeft": 10}} type="button" onClick={this.changeFlag.bind(this)}>
                        Show Statistics</button>
                </div>
            </div>
        }
        if (this.state.flStatistics) {
            return <div>
                <EmployeesStatistics stat ={this.state.employees} />
                <div style={{"textAlign": "center"}}>
                    <button  type="button" onClick={this.changeFlag.bind(this)}>
                        Hide Statistics</button>
                </div>
            </div>
        }
    }
}
export default Employees;
