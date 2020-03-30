import React from "react";
import _ from 'lodash';
import EmployeesStatistics from "./EmployeesStatistics";
import EmployeesTable from "./EmployeesTable";
import EmployeesForm from "./EmployeesForm";
const styleCursor = {cursor: "pointer"}

class Employees extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: [], flag: '0'
        }
    }

    addEmployee(employee) {
        const employees = this.state.employees;
        const ind = employees.findIndex(o => o.id === employee.id)
        if (ind >= 0) {
            return false;
        }
        employees.unshift(employee);
        this.setState({
            employees, flag: '0'
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
    changeFlag(props){
        if(props==0){
            this.setState({flag:'0'})}
        if(props==1){
            this.setState({flag:'1'})
        }
        if(props==2){
            this.setState({flag:'2'})
        }
    }
    render() {
        if (this.state.flag == 0) {
            return <div>
                <EmployeesTable employees={this.state.employees}
                                removeFn={this.removeEmployee.bind(this)}/>
                <div style={{"textAlign": "center"}}>
                    <i style={styleCursor} onClick={this.changeFlag.bind(this,2)} className="fa fa-plus-circle">
                        Add Employee</i>
                    <button style={{"marginLeft": 10}} type="button" onClick={this.changeFlag.bind(this,1)}>
                        Show Statistics
                    </button>
                </div>
            </div>
        }
        if (this.state.flag == 1) {
            return <div>
                <EmployeesStatistics stat={this.state.employees}/>
                <div style={{"textAlign": "center"}}>
                    <button type="button" onClick={this.changeFlag.bind(this,0)}>
                        Show table
                    </button>
                    <i style={styleCursor} onClick={this.changeFlag.bind(this,2)} className="fa fa-plus-circle">
                        Add Employee</i>
                </div>
            </div>
        }
        if (this.state.flag == 2) {
            return <div>
                <EmployeesForm addFn={this.addEmployee.bind(this)}/>
                <button style={{"marginLeft": 10}} type="button" onClick={this.changeFlag.bind(this,1)}>
                    Show Statistics
                </button>
                <button style={{"marginLeft": 10}} type="button" onClick={this.changeFlag.bind(this,0)}>
                    Show Table
                </button>
            </div>
        }
    }
}
export default Employees;
