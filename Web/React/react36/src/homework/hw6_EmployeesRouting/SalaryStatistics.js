import React from 'react';
import {maxSalary,minSalary,genders,digitsId} from "./EmployeesConfig";
import {getInputElement, getButtonElement, getErrorMessage} from "./InputElements";

class SalaryStatistics extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            tableStatistics:[],
            minimalSalary:0,
            maximalSalary:0,
            totalSalary:0,
            interval:'',
            error:{}
        }
        this.handlerInterval = this.handlerInterval.bind(this);
    }
    handlerInterval(event) {
        const interval = event.target.value;
        const error = this.state.error;
        error.errorInterval = '';
        if(+interval < 0) {
            error.errorInterval = 'Interval can\'t be negative number';
        } else if(interval.indexOf(".") >= 0){
            error.errorInterval = 'Interval can\'t be fraction number';
        } else if(interval== ""){
            error.errorInterval = 'Enter interval';
        } else if(interval.substr(0,1)=="0") {
            error.errorInterval = 'Interval can\'t start from 0';
        } else {
            this.interval = interval;
        }
        this.setState({
            error: this.state.error,
        })
    }
    addInterval() {
        let statisticsRecords = {};
        const employees = this.props.employees;
        let i=parseInt(minSalary);
        let count = 0;
        let interval = parseInt(this.interval)
        for (; i < +maxSalary; i=i+interval) {
           employees.forEach(function (employee) {
                if (employee.salary>i&&employee.salary < i+interval) {
                    count++;
                }
                    });
            statisticsRecords = {rangeSalary:`employees with salary from ${i} to ${i+interval}`,countEmployees:count}
            count=0;
            this.state.tableStatistics.push(statisticsRecords);
                }
        this.setState({
            tableStatistics: this.state.tableStatistics,
        })
        return  this.state.tableStatistics

        }
minMaxTotalSalary(){
    const employees = this.props.employees;
    let minimalSalary = maxSalary;
    let maximalSalary = 0;
    let total =0;
        employees.forEach(function (employee) {
            total=total+employee.salary;
            if(employee.salary<minimalSalary){
                minimalSalary=employee.salary;
            }
            if(employee.salary>maximalSalary){
                maximalSalary=employee.salary;
            }
        });
    this.state.minimalSalary=minimalSalary;
    this.state.maximalSalary=maximalSalary;
    this.state.totalSalary=total;

    this.setState({
        maximalSalary:this.state.maximalSalary,
        minimalSalary:this.state.minimalSalary,
        totalSalary:this.state.totalSalary
    })
}
viewTable(){

    const tableRecords =this.state.tableStatistics.map (
        (e) => {
            return <tr key={e.rangeSalary}>
                <td>{e.rangeSalary}</td>
                <td>{e.countEmployees}</td>
            </tr>
        }
    )
    return    <table className="table">
        <thead>
        <tr>
            <th>Range Salary</th>
            <th>Count Employees</th>
        </tr>
        </thead>
        <tbody>
        {tableRecords}
        </tbody>
    </table>
}

render() {
    return <div className="generations">
            <header className="card-header">
                <h3>Statistics of the Employees Salary</h3>
            </header>
        <div>
            {getErrorMessage(this.state.error.errorInterval)}
            {getInputElement('number','intervalSalary','Interval Salaries',this.handlerInterval)}

            {getButtonElement('Get Statistics',this.addInterval.bind(this))}
        </div>
        <div>
            <label>Minimal Salary :</label>
            {this.state.minimalSalary}
        </div>
        <div>
            <label>Maximal Salary :</label>
            {this.state.maximalSalary}
        </div>
        <div>
            <label>Total Salary :</label>
            {this.state.totalSalary}
        </div>
        <div>
            {getButtonElement('Show min max total',this.minMaxTotalSalary.bind(this))}
        </div>
        <div>
            {this.viewTable()}
        </div>
    </div>
    }

}
export default SalaryStatistics;

