import React from "react";
import _ from 'lodash';
import EmployeesStatistics from "./EmployeesStatistics";
import EmployeesTable from "./EmployeesTable";
import EmployeeForm from "./OrderForm";


class Employees extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: [], flag:'0';
        }
    }
    addEmployee() {
        const employees = this.state.employees;
		  const ind = employees.findIndex(o=>o.email===order.email)
        if(ind>=0){
            return false;
        }
        employees.unshift(employee);
        this.setState({
            employees,flag:'0'
        })
		  return true;
    }
    removeEmployee(id) {
        const employees = this.state.employees;
        if (window.confirm(`You are going to delete employee ${id}`)) {
            _.remove(employees, employee => employee.id == id)
            this.setState({employees});
            return true;
        }
    }

    render() {
        if (this.state.flag == 0) {
            return <div>
                <EmployeesTable employees = {this.state.employees}
                                removeFn={this.removeEmployee.bind(this)}/>
                <div style={{"textAlign": "center"}}>
                       <i style={styleCursor} onClick={this.setState({flag:'2'})} className="fa fa-plus-circle"></i>
                        Add Employee</i>
                    <button style={{"marginLeft": 10}} type="button" onClick={this.setState({flag:'1'})}>
                        Show Statistics</button>
                </div>
            </div>
        }
        if (this.state.flag ==1) {
            return <div>
                <EmployeesStatistics stat ={this.state.employees} />
                <div style={{"textAlign": "center"}}>
                    <button  type="button" onClick={this.setState({flag:'0'})}>
                        Show table</button>
   <i style={styleCursor} onClick={this.setState({flag:'2'})} className="fa fa-plus-circle"></i>
                        Add Employee</i>
                </div>
            </div>
        }
    }
    if (this.state.flag ==2) {
            return <div>
     <EmployeeForm addFn={this.addEmployee.bind(this)}/>
	      <button style={{"marginLeft": 10}} type="button" onClick={this.setState({flag:'2'})}>
                        Show Statistics</button>
    <button style={{"marginLeft": 10}} type="button" onClick={this.setState({flag:'0'})}>
                        Show Table</button>
}
export default Employees;
