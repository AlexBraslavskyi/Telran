import React from 'react';
import {maxSalary,minSalary} from "../config/EmployeesConfig";
import _ from "lodash";
import {getInputElement} from "../utils/InputElements";
import {getRandomColor} from "../utils/Random";

class SalaryStatistics extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            tableStatistics: [],
            minimalSalary: 0,
            maximalSalary: 0,
            totalSalary: 0,
            interval: {value: '', controlError: 0},
            error: {}
        }
        this.formRef = null;
        this.submit = this.submit.bind(this);
        this.handlerInterval = this.handlerInterval.bind(this);
    }

    handlerInterval(event) {
        const interval = event.target.value;
        const error = this.state.error;
        const employees = this.props.employees;
        error.errorInterval = '';
        this.state.interval.controlError = 0;
        if(employees.length<=0){
            error.errorInterval = 'You mast add employees';
            this.state.interval.controlError = -1;
    }else if (interval < 0) {
            error.errorInterval = 'Interval can\'t be negative number';
            this.state.interval.controlError = -1;
            this.state.interval.value = interval;
        } else if (interval.indexOf(".") >= 0) {
            error.errorInterval = 'Interval can\'t be fraction number';
            this.state.interval.controlError = -1;
            this.state.interval.value = interval;
        } else if (interval == "") {
            error.errorInterval = 'Enter interval';
            this.state.interval.controlError = -1;
            this.state.interval.value = interval;
        } else if (interval.substr(0, 1) == "0") {
            error.errorInterval = 'Interval can\'t start from 0';
            this.state.interval.controlError = -1;
            this.state.interval.value = interval;
        } else {
            this.state.interval.value = interval;
            this.state.interval.controlError = 1;
        }
        this.setState({
            error: this.state.error,
            interval:{value:this.state.interval.value,controlError: this.state.interval.controlError}
        })
    }

    submit = (event) =>  {
        event.preventDefault();
        let statisticsRecords = {};
        const employees = this.props.employees;
        let i = minSalary;
        let count = 0;
        let interval = parseInt(this.state.interval.value);
        for (; i < +maxSalary; i = i + interval) {
            employees.forEach(function (employee) {
                if (employee.salary > i && employee.salary < i + interval) {
                    count++;
                }
            });
            statisticsRecords = {
                rangeSalary: `employees with salary from ${i} to ${i + interval}`,
                countEmployees: count
            }
            count = 0;
            this.state.tableStatistics.push(statisticsRecords);
        }

        this.setState({
            tableStatistics: this.state.tableStatistics,
            interval: {value: '', controlError: 0},
        })
        this.minMaxTotalSalary();
        this.formRef.reset();

    }

    minMaxTotalSalary() {
        const employees = this.props.employees;
        this.state.minimalSalary = _.minBy(employees, function (employee) {
            return employee.salary;
        });
        this.state.maximalSalary = _.maxBy(employees, function (employee) {
            return employee.salary;
        });
        this.state.totalSalary =  _.sumBy(employees, function (employee) {
            return employee.salary;
        });

        this.setState({
            maximalSalary: this.state.maximalSalary,
            minimalSalary: this.state.minimalSalary,
            totalSalary: this.state.totalSalary
        })
    }

    viewTable() {
        const tableRecords = this.state.tableStatistics.map(
            (e) => {
                if(e.countEmployees>0) {
                    const width = (e.countEmployees * 100) / this.props.employees.length + "%";
                    const color = getRandomColor();
                    return <tr key={e.rangeSalary}>
                        <td>{e.rangeSalary}</td>
                        <td>
                            <div style={{'width': "100%"}} className="bar-container">
                                <div style={{'width': width, 'height': '18px', 'backgroundColor': color}}
                                     className="bar"></div>
                            </div>
                        </td>
                        <td>{e.countEmployees}</td>
                    </tr>
                }
            }
        )
        this.state.tableStatistics = [];
        return <div className="card">
            <header className="card-header">
                <h3>Table of employees salary statistics</h3>
            </header>
        <div className="card-body" >
        <table className="table table-sm" style={{'textAlign':'center'}}>
            <thead>
            <tr>
                <th>Interval Salary</th>
                <th></th>
                <th>Count Employees</th>
            </tr>
            </thead>
            <tbody>
            {tableRecords}
            </tbody>
        </table>
        </div>
        </div>
    }
inputForm(){
        return  <div>
            <header className="card-header">
                <h3>Statistics of the Employees Salaries</h3>
            </header>
            <form ref={(ref) => this.formRef = ref} className='form-group' onSubmit={this.submit}>
                <div className='center'>
                    {getInputElement('number', 'Enter interval', 'Salary Interval',
                        this.handlerInterval, this.state.error.errorInterval, this.state.interval)}
                    <div className='btn btn-group'>
                        <button style={{cursor: 'pointer'}} type="submit" className="btn btn-primary"
                                disabled={this.state.interval.controlError ===0||this.state.interval.controlError ===-1}>
                            <i className="fa fa-pie-chart fa-2x"/> Set interval
                        </button>
                    </div>
                </div>
            </form>
        </div>
}
    render() {
        if (this.state.tableStatistics.length <= 0){
      return this.inputForm()
    }else {
            return <div>
                    {this.inputForm()}
                <div className='center'>
                    <div>
                    <label style={{"color":"red"}}>Minimal Salary :</label>
                        {' Name: '+this.state.minimalSalary.name+' Salary: '+this.state.minimalSalary.salary}
                    </div>
                    <div>
                        <label style={{"color":"red"}}>Maximal Salary :</label>
                        {' Name: '+this.state.maximalSalary.name+' Salary: '+this.state.maximalSalary.salary}
                    </div>
                    <div>
                        <label style={{"color":"red"}}>Total Salary :</label>
                        {this.state.totalSalary}
                    </div>
            </div>
                <div className='thirdLevel'>
                    {this.viewTable()}
                </div>
            </div>
        }
    }
}

            export default SalaryStatistics;

