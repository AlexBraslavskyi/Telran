import React from 'react';
import _ from 'lodash';
export default function TitleStatistics(props) {
    const statisticsObj = _.countBy(props.employees, 'title');
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

    return <div className="card">
        <header className="card-header">
           <h3>Statistics of the Employee Titles</h3>
        </header>
        <div className="card-body">
            <table>
                <thead>
                <tr>
                    <th>title</th>
                    <th>count</th>
                </tr>
                </thead>
                <tbody>
                {statisticsRecords}
                </tbody>
            </table>
        </div>
        </div>
}
