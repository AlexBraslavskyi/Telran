import * as React from "react";
import _ from 'lodash';
import EmployeesTable from "./EmployeesTable";
import {EmployeeForm} from "../../hw10_EmployeesObserv/Components/EmployeeForm";
import {getRandomEmployee} from "../utils/Random";
import {useState} from "react";

const Employees = (props) =>{
   let [employeesSwitch,setEmployeesSwitch]=useState(0)
    const employees = [...props.employees];
    const addEmployeeShow = ()=>{
        setEmployeesSwitch(1)
    }

    const genEmployee = () => {
        const employee = getRandomEmployee();
        const index = employees
            .findIndex(e => e.id === employee.id);
        if (index < 0) {
            employees.unshift(employee);
           props.updateEmployeesFn(employees);
        } else genEmployee();
    }

   function addEmployee(employee) {
        const index = employees
            .findIndex(e => e.id === employee.id);
        if (index >= 0) {
            return false;
        }
        employees.unshift(employee);
        props.updateEmployeesFn(employees);
       setEmployeesSwitch(0);
        return true;
    }

   function removeEmployee(id) {
        _.remove(employees, e => e.id == id);
        props.updateEmployeesFn(employees);
    }

   function viewButton() {
                return <div className="form-group">
                    <div className='btn btn-group'>
                        <button style={{cursor: 'pointer'}} type="button" className="btn btn-success"
                                onClick={addEmployeeShow}><i className="fa fa-user-plus fa-2x"/>Add
                            Employee
                        </button>
                        <button style={{cursor: 'pointer'}} type="button" className="btn btn-primary"
                                onClick={genEmployee}><i className="fa fa-users fa-2x"/>Generate
                            Employee
                        </button>
                    </div>
                </div>
        }
    if(employees.length>0) {
            switch (employeesSwitch) {
                case 0:
                    return <div>{viewButton()}
                        <EmployeesTable employees={employees}
                                        removeFn={removeEmployee}/>
                    </div>
                case 1:
                    return <EmployeeForm addEmployeeFn={addEmployee}/>
            }
        }else{
            switch (employeesSwitch) {
                case 0:
                    return viewButton()
                case 1:
                    return <EmployeeForm addEmployeeFn={addEmployee}/>
            }
        }
    }
export default Employees;
