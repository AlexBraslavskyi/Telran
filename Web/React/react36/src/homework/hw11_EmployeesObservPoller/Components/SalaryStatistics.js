import React, {useEffect, useState} from 'react';
import {maxSalary,minSalary} from "../config/EmployeesConfig";
import _ from "lodash";
import {getInputElement, getInputElementBlur} from "../utils/InputElements";
import {getRandomColor} from "../utils/Random";



const SalaryStatistics = (props) =>{
    let intervalSalary, setIntervalSalary, error,setError, tableStatistics, setTableStatistics,formRef,setFormRef,minimalSalary,
        setMinimalSalary, maximalSalary, setMaximalSalary, totalSalary,setTotalSalary;
    [error,setError]=useState('');
    [formRef,setFormRef]=useState(null);
    [intervalSalary,setIntervalSalary]=useState({value:'',controlError: 0});
    [tableStatistics,setTableStatistics]=useState([]);
    [minimalSalary,setMinimalSalary]=useState(0);
    [maximalSalary,setMaximalSalary]=useState(0);
    [totalSalary,setTotalSalary]=useState(0);
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
    
    
    function handlerInterval(event) {
        setTableStatistics([]);
        intervalSalary = event.target.value;
        if (employees.length <= 0) {
            setError('You mast add employees');
            setIntervalSalary({value: intervalSalary, controlError: -1})
        } else if (intervalSalary < 0) {
            setError('Interval can\'t be negative number');
            setIntervalSalary({value: intervalSalary, controlError: -1})
        } else if (intervalSalary.indexOf(".") >= 0) {
            setError('Interval can\'t be fraction number');
            setIntervalSalary({value: intervalSalary, controlError: -1})
        } else if (intervalSalary == "") {
            setError('Enter intervalSalary');
            setIntervalSalary({value: intervalSalary, controlError: -1})
        } else if (intervalSalary.substr(0, 1) == "0") {
            setError('Interval can\'t start from 0');
            setIntervalSalary({value: intervalSalary, controlError: -1})
        } else {
            setError('');
            setIntervalSalary({value: intervalSalary, controlError: 1})
        }
    }
   const submit = (event) =>  {
        event.preventDefault();
        let statisticsRecords = {};
        const res =[];
        let i = minSalary;
        let count = 0;
        let getInterval = +intervalSalary.value;
        for (; i < +maxSalary; i = i + getInterval) {
            employees.forEach(function (employee) {
                if (employee.salary > i && employee.salary < i + getInterval) {
                    count++;
                }
            });
            statisticsRecords = {
                rangeSalary: `employees with salary from ${i} to ${i + getInterval}`,
                countEmployees: count
            }
            count = 0;
            res.push(statisticsRecords);
        }
        formRef.reset();
        minMaxTotalSalary();
            setTableStatistics(res);
            setIntervalSalary({value: '', controlError: 0});
    }

   function minMaxTotalSalary() {
        minimalSalary = _.minBy(employees, function (employee) {
            return employee.salary;
        });
        maximalSalary = _.maxBy(employees, function (employee) {
            return employee.salary;
        });
        totalSalary =  _.sumBy(employees, function (employee) {
            return employee.salary;
        });

        setMinimalSalary(minimalSalary);
        setMaximalSalary(maximalSalary);
        setTotalSalary(totalSalary);

    }

   function viewTable() {
        const tableRecords = tableStatistics.map(
            (e) => {
                if(e.countEmployees>0) {
                    const width = (e.countEmployees * 100) / employees.length + "%";
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
function inputForm(){
        return  <div>
            <header className="card-header">
                <h3>Statistics of the Employees Salaries</h3>
            </header>
            <form ref={(ref) => formRef = ref} className='form-group' onSubmit={submit}>
                <div className='center'>
                    {getInputElement('number', 'Enter intervalSalary', 'Salary Interval',
                        handlerInterval, error, intervalSalary)}
                    <div className='btn btn-group'>
                        <button style={{cursor: 'pointer'}} type="submit" className="btn btn-primary"
                                disabled={intervalSalary.controlError ===0||intervalSalary.controlError ===-1}>
                            <i className="fa fa-pie-chart fa-2x"/> Set interval
                        </button>
                    </div>
                </div>
            </form>
        </div>
}

        if (tableStatistics.length <=0){
      return inputForm();
    }else {
            return <div>
                    {inputForm()}
                <div className='center'>
                    <div>
                    <label style={{"color":"red"}}>Minimal Salary :</label>
                        {' Name: '+minimalSalary.name+' Salary: '+minimalSalary.salary}
                    </div>
                    <div>
                        <label style={{"color":"red"}}>Maximal Salary :</label>
                        {' Name: '+maximalSalary.name+' Salary: '+maximalSalary.salary}
                    </div>
                    <div>
                        <label style={{"color":"red"}}>Total Salary :</label>
                        {totalSalary}
                    </div>
            </div>
                <div className='thirdLevel'>
                    {viewTable()}
                </div>
            </div>

        }
    }

            export default SalaryStatistics;

