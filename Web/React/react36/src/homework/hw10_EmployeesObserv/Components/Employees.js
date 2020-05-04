import * as React from "react";
import _ from 'lodash';
import EmployeesTable from "./EmployeesTable";
import {EmployeeForm} from "./EmployeeForm";
import {getRandomEmployee} from "../utils/Random";
import {useState,useEffect} from "react";


const Employees = (props) =>{
   let [employeesSwitch,setEmployeesSwitch]=useState(0)
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
useEffect(
    () => {
        getEmployees();
        return () => {
            if(subscription && !subscription.closed) {
                subscription.unsubscribe();
            }
        }
    }, []
)
   
    
    const addEmployeeShow = ()=>{
        setEmployeesSwitch(1)
    }

    const genEmployee = () => {
        const employee = getRandomEmployee();
        const index = employees
            .findIndex(e => e.id === employee.id);
        if (index < 0) {
             props.employeesService.addEmployee(employee).subscribe(() => {
           if(!subscription || subscription.closed) {
               getEmployees() ;
           }
       }, error => {alert(JSON.stringify(error))});
        } else genEmployee();
    }

   function addEmployee(employee) {
        const index = employees
            .findIndex(e => e.id === employee.id);
        if (index >= 0) {
            return false;
        }
       props.employeesService.addEmployee(employee).subscribe(() => {
           if(!subscription || subscription.closed) {
               getEmployees() ;
           }
       }, error => {alert(JSON.stringify(error))});
       setEmployeesSwitch(0)
        return true;
    }
   function removeEmployee(id) {
        _.remove(employees, e => e.id == id);
        props.employeesService.deleteEmployee(id).subscribe(() => {
                if(!subscription || subscription.closed) {
                    getEmployees() ;
                }
            }, error => {alert(JSON.stringify(error))});
        return true;
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
                    <EmployeesTable employeesView={props.employeesService.getEmployee()}
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
