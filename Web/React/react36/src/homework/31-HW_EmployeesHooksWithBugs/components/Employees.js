import * as React from "react";
import '../sstylesheets/Employees.css';
import _ from 'lodash';
import EmployeesTable from "./EmployeesTable";
import EmployeeForm from "./EmployeeForm";
import {useState} from "react";
import {useEffect} from "react";


const Employees = (props) => {
    let employeesSwitch;
    let setEmployeesSwitch;
    [employeesSwitch, setEmployeesSwitch] = useState(0);
    const employees = props.employees;

    function addEmployeeShow() {
        setEmployeesSwitch(2);
    }

    function addEmployee(employee) {

        const index = employees
            .findIndex(e => e.id === employee.id);
        if (index >= 0) {
            return false;
        }
        employees.unshift(employee);
        setEmployeesSwitch(0);
        props.updateEmployeesFn(employees);
        return true;
    }
    function removeEmployee(id) {
        _.remove(employees, e => e.id === id)  ;

       props.updateEmployeesFn(employees);
        setEmployeesSwitch(0);

    }
    switch(employeesSwitch){
        case 0: return <div className="employees">
            <EmployeesTable employees={employees}
                            removeFn={removeEmployee}/>
            <i style={{cursor: 'pointer'}}
               className="fa fa-plus-square fa-3x"
               onClick={addEmployeeShow}/>
        </div>

        case 2: return <EmployeeForm addEmployeeFn={addEmployee}/>
    }
}

export default Employees;
