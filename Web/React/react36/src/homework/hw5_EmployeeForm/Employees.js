import React from "react";
import _ from 'lodash';
import EmployeesStatistics from "./EmployeesStatistics";
import EmployeesTable from "./EmployeesTable";
import EmployeeForm from "./OrderForm";


class Employees extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: [], flStatistics: false,flAddEmployee:false
        }
    }
	  showOrderForm(){
        this.setState({flAddEmployee:false})
    }
    addEmployee() {

        const employees = this.state.employees;
		  const ind = employees.findIndex(o=>o.email===order.email)
        if(ind>=0){
            return false;
        }
        employees.unshift(employee);
        this.setState({
            employees,flAddEmployee:false
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
    changeFlag(){
        if(!this.state.flStatistics){
    this.setState({flStatistics:true})}
        else {
            this.setState({flStatistics:false})
        }
}
    render() {
        if (!this.state.flStatistics) {
            return this.state.flAddEmployee? <EmployeeForm addFn={this.addEmployee.bind(this)}/>: <div>
                <EmployeesTable employees = {this.state.employees}
                                removeFn={this.removeEmployee.bind(this)}/>
                <div style={{"textAlign": "center"}}>
                       <i style={styleCursor} onClick={this.showEmployeeForm.bind(this)} className="fa fa-plus-circle"></i>
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
