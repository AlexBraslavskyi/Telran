import React, {useEffect, useState} from 'react';
import {minSalary,maxSalary,titlesArr,digitsId,genders,nameMinLength} from "../config/EmployeesConfig";
import {getInputElement, getInputElementBlur, getRadioButtonElement, getSelectElement} from "../utils/InputElements";
import _ from 'lodash';

const letters = /^[A-Za-z]+$/;

export const EmployeeForm = (props) =>{
    let employee, setEmployee, error, setError, id, setId, emailAddress, setEmailAddress,
        name, setName, salary, setSalary, formRef, setFormRef;
    [employee,setEmployee]=useState({
                id: '',
                emailAddress: '',
                name: '',
                gender: '',
                salary: '',
                title: titlesArr[0],
            });
    [error,setError]=useState({errorId:'',errorName:'',errorEmail:'',errorSalary:''});
    [id,setId]=useState({value:'Enter ID',controlError: 0});
    [emailAddress,setEmailAddress]=useState({value:'',controlError: 0});
    [name,setName]=useState({value:'Enter Name',controlError: 0});
    [salary,setSalary]=useState({value:'Enter Salary',controlError: 0});
    [formRef,setFormRef]=useState(null);

    function submit(event) {
        event.preventDefault();
        if (!props.addEmployeeFn(employee)) {
            error.errorId = `Employee ${employee.id} already exists`;
            id={value:employee.id,controlError: -1}
            error=error.errorId
            // formRef.reset();
            // setFormRef(null);
        }
    }

    useEffect(()=> {
        setError({...error});
        // setEmployee(employee);
        // setSalary(salary);
        // setName(name)
        // setEmailAddress(emailAddress)
        // setId(id)
        console.log('hi')
    },[error.errorId])

   function handlerId(event) {
        id = event.target.value;
       // error={errorId:''}
        if(id==""){
            id={value:id,controlError: 0}
            employee.id = id;
        }else if (id.length != digitsId) {
            error={errorId:'Number of ID digits should be ' + digitsId}
            id={value:id,controlError: -1}
            employee.id = id;
        } else if(+id < 0) {
            error={errorId:'id can\'t be negative number'}
            id={value:id,controlError: -1}
            employee.id = id;
        } else if(id.indexOf(".") >= 0){
            error={errorId: 'id can\'t be fraction number'}
            id={value:id,controlError: -1}
            employee.id = id;
        } else if(id.substr(0,1)=="0"){
            error={errorId: 'id can\'t start from 0'}
            id={value:id,controlError: -1}
            employee.id = id;
        } else {
            employee.id = id;
            id={value:id,controlError: 1}
            error={errorId:''}
        }

    }
  function  handlerName(event) {
        name = event.target.value;
        error={errorName:''}
        if(name ==""){
            name={value:name,controlError: 0}
            employee.name = name;
        }else if (name.length < nameMinLength) {
            error={errorName: 'Minimal name length should be '
                + nameMinLength+'symbol'}
            name={value:name,controlError: -1}
            employee.name = name;
        }  else if(!name.match(letters)) {
            error={errorName:'Name mast content only english letters'}
            name={value:name,controlError: -1}
            employee.name = name;
        }
        else {
            error={errorName:''}
            employee.name = name;
            name={value:name,controlError: 1}
            fillEmployeeAddress();
        }

        }

   function handlerNonValidated(event) {
        employee[event.target.name] = event.target.value;
    }
  function  handlerEmail(event) {
        const email = event.target.value;
        error={errorEmail:''}
        const format = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
        let newID = id.value.toString();
        newID = newID.substr(0,3);

        if (email == "") {
            emailAddress={value:email,controlError: 0}
            employee.emailAddress = email;
        }else if(email == name+newID+'@') {
            error={errorEmail:'Wrong email format, please enter domain'}
            emailAddress={value:email,controlError: -1}
            employee.emailAddress = email;
        } else if (!format.test(email)) {
            error={errorEmail:'Wrong email format'}
            emailAddress={value:email,controlError: -1}
            employee.emailAddress = email;
        } else {
            error={errorEmail:''}
            employee.emailAddress = email;
            emailAddress={value:email,controlError: 1}
        }
    }

   function handlerSalary(event) {
        salary = event.target.value;
        error={errorSalary :''}
        if (salary < minSalary || salary > maxSalary) {
            error={errorSalary :`salary should be in rang [${minSalary}-${maxSalary}]`}
            salary={value:salary,controlError: -1}
            employee.salary = salary;
        } else {
            error={errorSalary :''}
            employee.salary = salary;
            salary={value:salary,controlError: 1}
        }
    }
  function  fillEmployeeAddress() {
        if (id && name) {
            if (!emailAddress.value) {
                emailAddress.value = '@';
            }
            const emailAddressFirstPart =
                name +
                id.value.substr(0, 3);
            const index = emailAddress.value.indexOf("@");
            const emailAddressSecondPart =
                emailAddress.value.substr(index);
           emailAddress.value = emailAddressFirstPart +
                emailAddressSecondPart;
           // setEmployee(employee)
        }
    }
   // function validate() {
   //      const res =  notErrors() && allFields();
   //      return res;
   //  }
   //
   //      const genderRadioButtons =
   //          genders.map(
   //              g => getRadioButtonElement('gender',
   //                  handlerNonValidated, g)
   //          )
   //  function notErrors() {
   //      return Object.values(error)
   //          .reduce((res, field) => {
   //              return res && !field
   //          } , true)
   //  }
   //
   //  function allFields() {
   //      return Object.values(employee)
   //          .reduce((res, field) => {
   //              return res && field
   //          } , true)
   //  }
        return <div className="card">
            <div className="card-header">
                <h2>Employee Data Form</h2>
            </div>
            <div className="card-body">
                <form ref={(ref) => formRef = ref} onSubmit={submit}>
                    <div className = 'firstLevel'>
                    {getInputElement('number',
                        'id', 'Employee ID',
                       handlerId,error.errorId,id)}
                    {getInputElement('text',
                        'name', 'Employee Name',
                        handlerName,error.errorName,name)}
                    {getInputElementBlur('email',
                        'emailAddress', 'Email address',
                       handlerEmail,error.errorEmail,emailAddress)}
                    </div>
                    <div className = 'secondLevel'>
                    {/*{genderRadioButtons}*/}
                    </div>
                    <div className = 'thirdLevel'>
                    {getInputElement('number', 'salary',
                    'Salary', handlerSalary,error.errorSalary,salary)}
                    {getSelectElement('title', handlerNonValidated,
                        'Select Title', titlesArr)}
                    </div>
                    <div className='form-group'>
                    <div className='btn btn-group'>
                    <button type="submit" className="btn btn-success"
                            // disabled={!validate()}
                    >
                        <i className="fa fa-user-plus fa-2x"/>Add Employee</button>
                    </div>
                    </div>
                </form>
            </div>
        </div>
    }



