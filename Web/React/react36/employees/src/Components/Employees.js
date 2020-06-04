import * as React from "react";
import _ from 'lodash';
import EmployeesTable from "./EmployeesTable";
import {EmployeeForm} from "./EmployeeForm";
import {getRandomEmployee} from "../utils/Random";
import {useState,useEffect} from "react";
import {useSelector} from "react-redux";
import {pathDBEmployees} from "../config/EmployeesConfig";


const Employees = (props) => {

    let [employeesSwitch, setEmployeesSwitch] = useState(0)
    const userData = useSelector(state=>state.userData);
    const employees = useSelector(state=>state.employees);
    const addEmployeeShow = () => {
        setEmployeesSwitch(1)
    }

    function genEmployee() {
        const employee = getRandomEmployee();
        const index = employees
            .findIndex(e => e.id === employee.id);
        if (index < 0) {
            props.employeesService.addEmployee(employee).then(() => {
            }).catch(error => {
                alert(JSON.stringify(error))
            })
        } else genEmployee();
    }

    function addEmployee(employee) {
        const index = employees
            .findIndex(e => e.id === employee.id);
        if (index >= 0) {
            return false;
        }
        props.employeesService.addEmployee(employee)
            .then(() => {
                setEmployeesSwitch(0);
            }, error => {
                alert(`employee with id ${employee.id} already exists`)
            })
        return true;
    }

    function removeEmployee(id) {
        _.remove(employees, e => e.id === id);
        props.employeesService.deleteEmployee(id)
            .then(() => {
            })
    }


   function viewButton() {
                return <div className="center">
                    <div className='btn btn-group'>
                        {userData.isAdmin? <button style={{cursor: 'pointer'}} type="button" className="btn btn-success"
                                onClick={addEmployeeShow}><i className="fa fa-user-plus fa-2x"/>Add
                            Employee
                        </button>:null}
                        {userData.isAdmin?<button style={{cursor: 'pointer'}} type="button" className="btn btn-primary"
                                onClick={genEmployee}><i className="fa fa-users fa-2x"/>Generate
                            Employee
                        </button>:null}
                        {/*<button style={{cursor: 'pointer'}} type="button" className="btn btn-success"*/}
                        {/*        onClick={props.employeesService.deleteAllEmployees()}><i className="fa fa-trash fa-2x"/>Delete All*/}
                        {/*    Employees*/}
                        {/*</button>*/}
                    </div>
                </div>
        }
    if(employees.length>0) {
        switch (employeesSwitch) {
            case 0:
                return <div>{viewButton()}
                    <EmployeesTable employees={employees}
                                    removeFn={removeEmployee}
                                    isAdmin={userData.isAdmin}
                    />
                </div>
            case 1:
                return userData.isAdmin?<EmployeeForm addEmployeeFn={addEmployee}/>:null
        }
    }else{
        switch (employeesSwitch) {
            case 0:
                return viewButton()
            case 1:
                return userData.isAdmin?<EmployeeForm addEmployeeFn={addEmployee}/>:null
        }
        }
    }
export default Employees;
