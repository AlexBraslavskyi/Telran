import React from "react";
import {getInputElement, getSelectElement} from "../util/input_elements";
import EmployeesTable from "./EmployeesTable";
import {TITLES} from "../config/employees_config";
export default class EmployeesSearch extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            salaryTo: Number.MAX_VALUE,
            salaryFrom: 0,
            title: '',
            employeesSearch: []
        };
        this.employees = props.employees;
        this.inputHandler = this.inputHandler.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }
    inputHandler(event) {
        event.preventDefault();
        const state = this.state;
        state[event.target.name] = event.target.value;
        this.setState({state});
    }
    onSubmit(event) {
        event.preventDefault();
        this.title = this.state.title;
        this.salaryFrom = this.state.salaryFrom;
        this.salaryTo = this.salaryFrom > this.state.salaryTo ?
            Number.MAX_VALUE : this.state.salaryTo;
        this.setState({
            employeesSearch: this.employees
                .filter(this.filterFn.bind(this))
        })
    }
    filterFn(empl) {
        return (empl.title === this.title || this.title === '')
        && (empl.salary >= this.salaryFrom) && (empl.salary <= this.salaryTo);
    }
    render() {
        return <div className="card">
            <div className="card-header">
                <h3>Employees Search</h3>
            </div>
            <div className="card-body">
                <form onSubmit={this.onSubmit}>
                    {getSelectElement('Title', this.inputHandler,
                    'title', [' ',...TITLES])}
                    {getInputElement('number', 'salaryFrom',
                    "Salary From",this.inputHandler )}
                    {getInputElement('number', 'salaryTo',
                        "Salary To",this.inputHandler )}
                        <button type="submit">
                            <i className="fa fa-search" />
                        </button>

                </form>
                <EmployeesTable employees={this.state.employeesSearch}/>
            </div>
        </div>
    }

}
