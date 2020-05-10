import React, {useEffect, useState} from 'react';
import _ from 'lodash';
export default function TitleStatistics(props) {
    const [employees, setEmployees] = useState([]);
    let subscription;
    const getEmployees = () => {
        subscription =
            props.employeesService.getEmployee()
                .subscribe(employeesFromServer => {
                    setEmployees(employeesFromServer)
                }, error => {
                    alert(JSON.stringify(error))
                })
    }
 let intervalID;
    const poller = ()=>{
        if(!subscription||subscription.closed) {
            getEmployees();
        }
    }
useEffect(
    () => {
      getEmployees();
                intervalID = setInterval(poller,1000);
            return () => {
                if(subscription && !subscription.closed) {
                    subscription.unsubscribe();
                }else {
                    clearInterval(intervalID);
                }
            }
        }, []
)
   
    
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

    return <div className="card">
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
