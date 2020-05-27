import {maxSalary, POLLING_INTERVAL, titlesArr} from "../config/EmployeesConfig";
import React, {useEffect, useState} from "react";
import {getInputElement, getInputElementBlur} from "../utils/InputElements";
import EmployeesTable from "./EmployeesTable";
import subscribeEffect from "../utils/subscriber";
import useSubscribeEffect from "../utils/subscriber";


const EmployeesSearch = (props) =>{
    let  error,setError, formRef,setFormRef, title, setTitle, salaryFrom,setSalaryFrom,
        salaryTo, setSalaryTo, resObj,setResObj;
    [error,setError]=useState({errorTitle:'',errorSalaryFrom:'',errorSalaryTo:''});
    [formRef,setFormRef]=useState(null);
    [title,setTitle]=useState({value:'',controlError:0});
    [salaryFrom,setSalaryFrom]=useState({value:'',controlError:0});
    [salaryTo,setSalaryTo]=useState({value:'',controlError:0});
    [resObj,setResObj]=useState({});
    const employeesService = props.employeesService;
    const [employees, setEmployees] =
        useSubscribeEffect(props.employeesService,props.employeesService.getEmployees,
            POLLING_INTERVAL)

   function handlerTitle(event) {
        const title = event.target.value;
        let flag = 0;
        titlesArr.forEach(function (t) {
                    if(t==title||title==""){
                        flag=1;
                        return flag;
                    }
        });
        if (flag==0) {
            setError({errorTitle:'Title '+ title+ ' wrong'});
            setTitle({value:title,controlError:-1})
        }else if (title=="") {
            setError({errorTitle:''});
            setTitle({value:title,controlError:0})
        } else {
            setError({errorTitle:''});
            setTitle({value:title,controlError:1})
        }
    }

   function handlerSalaryFrom(event) {
        const salaryFrom = event.target.value;

        if(salaryFrom < 0) {
            setSalaryFrom({value:salaryFrom,controlError:-1})
            setError({errorSalaryFrom:'Interval can\'t be negative number'});
        } else if(salaryFrom.indexOf(".") >= 0){
            setSalaryFrom({value:salaryFrom,controlError:-1})
            setError({errorSalaryFrom:'Interval can\'t be fraction number'});
        }else if (!salaryFrom) {
            setSalaryFrom({value:salaryFrom,controlError:0})
            setError({errorSalaryFrom:''});
        } else {
            setSalaryFrom({value:salaryFrom,controlError:1})
            setError({errorSalaryFrom:''});
        }
    }
   function handlerSalaryTo(event) {
        const salaryTo = event.target.value;
        if(salaryTo < 0) {
            setSalaryTo({value:salaryTo,controlError:-1});
            setError({errorSalaryTo:'Interval can\'t be negative number'});
        } else if(salaryTo.indexOf(".") >= 0){
            setSalaryTo({value:salaryTo,controlError:-1});
            setError({errorSalaryTo:'Interval can\'t be fraction number'});
        } else if (!salaryTo) {
            setSalaryTo({value:salaryTo,controlError:0});
            setError({errorSalaryTo:''});
        }else {
            setSalaryTo({value:salaryTo,controlError:1});
            setError({errorSalaryTo:''});
        }
    }
    function submit(event) {
        event.preventDefault();
        let searchResult = [];
        const titleProps = title.value;
        const salaryFromProps = +salaryFrom.value;
        const salaryToProps = +salaryTo.value;

        if (titleProps && salaryFromProps && salaryToProps && salaryFromProps < salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.title == titleProps && employee.salary > salaryFromProps && employee.salary
                    < salaryToProps) {
                    searchResult.push(employee);
                }
            })
        }

        if (titleProps && salaryFromProps && salaryToProps && salaryFromProps > salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.title == titleProps && employee.salary < salaryToProps) {
                    searchResult.push(employee);
                }
            })

        }
        if (!titleProps && salaryFromProps && salaryToProps && salaryFromProps > salaryToProps) {
            alert("Wrong parameters - salaryFrom can\'t be more then salaryTo")
        }

        if (!titleProps && salaryFromProps && salaryToProps && salaryFromProps < salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.salary < salaryToProps&&employee.salary>salaryFromProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (!titleProps && !salaryFromProps && salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.salary <= salaryToProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (!titleProps && salaryFromProps && !salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.salary <= maxSalary&&employee.salary>=salaryFromProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (titleProps && !salaryFromProps && salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.salary < salaryToProps && employee.title == titleProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (titleProps && !salaryFromProps && !salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.title == titleProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (titleProps && salaryFromProps && !salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.title == titleProps&&employee.salary>=salaryFromProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (!titleProps && !salaryFromProps && !salaryToProps) {
            employees.forEach(function (employee) {
                    searchResult.push(employee);
            })
        }
        setTitle({value:'',controlError:0});
        setSalaryFrom({value:'',controlError:0});
        setSalaryTo({value:'',controlError:0});
        formRef.reset();
        setResObj(searchResult);
        searchResult=[];
    }

        let viewRes='';
        if(resObj.length>0) {
            viewRes = <EmployeesTable employees = {resObj}/>
        }
        return <div style={{"margin":"1vw 15vw 3vw 3vw"}} className="generations">
            <header className="card-header">
                <h3>Searching Employees</h3>
            </header>
            <form ref={(ref) => formRef = ref} className='form-group' onSubmit={submit}>
                <div className='firstLevel'>
            <div>
                {getInputElementBlur('','Enter title','Title',handlerTitle,
                    error.errorTitle,title)}
            </div>
            <div>
                {getInputElementBlur('number','Enter salaryFrom','Salary From',handlerSalaryFrom,
                    error.errorSalaryFrom,salaryFrom)}
            </div>
            <div>
                {getInputElementBlur('number','Enter salaryTo','Salary To',handlerSalaryTo,
                    error.errorSalaryTo,salaryTo)}
            </div>
                </div>
                <div className='center'>
                <button type="submit" style={{cursor: 'pointer'}} className="btn btn-primary">
            <i style={{cursor: 'pointer'}} className="fa fa-search fa-2x"/>Search employee</button>
                </div>
            </form>
            <div>
                {viewRes}
            </div>
            </div>
        }
export default EmployeesSearch;
