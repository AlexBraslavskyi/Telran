import React from "react";
import _ from 'lodash';
import {MAX_SALARY, MIN_SALARY} from "../config/employees_config";
export default class SalaryStatistics extends React.Component {
    constructor(props) {
        super(props);
        this.employees = props.employees;

    }
    render() {

        if (this.employees.length === 0) {
            return <div/>
        }
        let interval = window.prompt("Enter salaries interval", "1000");
        const statistics = this.getStatistics();
        return <div className="card">
            <div className="card-header">
                <h3>Salary statistics</h3>
            </div>
            <div className="card-body">
                <div >
                    <label>minimal salary value: {statistics.min}</label><br/>
                    <label>maximal salary value: {statistics.max}</label><br/>
                    <label>total salary: {statistics.total}</label><br/>
                    <ol>
                        {this.getIntervalItems(interval)}
                    </ol>
                </div>
            </div>
        </div>
    }

    getStatistics() {
        return this.employees.reduce((res, c) => {
            return {min: c.salary < res.min ? c.salary : res.min,
                max: c.salary > res.max ? c.salary : res.max,
                total: res.total + c.salary}
        }, {min: MAX_SALARY, max: MIN_SALARY, total:0})
    }

    getIntervalItems(interval) {

        const objIntervalStatistics =
            _.countBy(this.employees, c => {

                return Math.floor(c.salary / interval);
            });

        let startInterval = +MIN_SALARY;
        console.log(objIntervalStatistics);
        return Object.values(objIntervalStatistics).map(v => {
            const nextInterval = startInterval + +interval;
            const key = `${startInterval}-${nextInterval}`;
            startInterval = nextInterval;
            return <li key={key}>{key}:{v}</li>
        })

    }
}
