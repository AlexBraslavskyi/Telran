import React, {useEffect, useState} from 'react';
import _ from 'lodash';
import subscribeEffect from "../utils/subscriber";
import useSubscribeEffect from "../utils/subscriber";
import {POLLING_INTERVAL} from "../config/EmployeesConfig";
export default function TitleStatistics(props) {
    const employeesService = props.employeesService;
    const [employees, setEmployees] =
        useSubscribeEffect(props.employeesService,props.employeesService.getEmployees,
            POLLING_INTERVAL)
    const statisticsObj = _.countBy(employees, 'title');
    const statisticsRecords = Object.entries(statisticsObj)
        .map(e => {
            return <tr key={e[0]}>
                <td>
                    {e[0]}
                </td>
                <td>
                    {e[1]}
                </td>
            </tr>
        });

    return <div style={{"margin":"1vw 15vw 3vw 3vw"}} className="card">
        <header className="card-header">
           <h3>Statistics of the Employee Titles</h3>
        </header>
        <div className="card-body" >
            <table className="table table-sm" style={{'textAlign':'center'}}>
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Count</th>
                </tr>
                </thead>
                <tbody>
                {statisticsRecords}
                </tbody>
            </table>
        </div>
        </div>
}
