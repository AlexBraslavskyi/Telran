import React, {useEffect, useState} from "react";
import {getRandomEmployee} from "../utils/Random";
import {getInputElement} from "../utils/InputElements";

const EmployeesGenerations = (props) =>{
    let count, setCount, error,setError, message, setMessage,formRef,setFormRef;
    [error,setError]=useState('');
    [formRef,setFormRef]=useState(null);
    [count,setCount]=useState({value:'',controlError: 0});
    [message,setMessage]=useState(false);
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
  function  handlerCount(event) {
        const inputCount = event.target.value;
         if(inputCount<=0||inputCount>99){
             setError('Count mast be in range [1-99]');
             setCount({value:inputCount,controlError:-1})
        } else if(inputCount.indexOf(".") >= 0){
             setError('Count can\'t be fraction number');
             setCount({value:inputCount,controlError:-1})
        } else if(inputCount.substr(0,1)=="0"){
             setError('Count can\'t start from 0');
             setCount({value:inputCount,controlError:-1})
        } else if(inputCount>0&&inputCount<=99) {
             setCount({value:inputCount,controlError:1})
             setError('');
        }
    }
   const genEmployee=() =>{
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
    function submit (event){
        event.preventDefault()
        let submitConfirm = true;
        if (error) {
            submitConfirm = false;}
        if (submitConfirm) {
            for (let i=0; i < count.value;i++) {
                genEmployee();
            }
        }
        formRef.reset();
                setMessage(`${count.value} employees was generated!`);
                setCount({value:'',controlError: 0});
    }
        return <div className="card">
        <div className="card-header">
            <h1>Generations Employees</h1>
                <div className="alert alert-info">
                    Current employees count: {employees.length}
                </div>
            <div className="center">
                <form ref={(ref) => formRef = ref} className='form-group' onSubmit={submit}>
                {getInputElement('number','Count','Count employees',
                handlerCount,error,count)}
                        <button type="submit" style={{cursor: 'pointer'}} className="btn btn-primary"disabled=
                            {count.controlError ===0||count.controlError ===-1}>
                            <i className="fa fa-users fa-2x"/>Generate Employee</button>
                    <div>
                    {message ? (
                        <div className="alert alert-success">{message}
                        </div>
                    ) : null}
                    </div>
                </form>
            </div>
        </div>
        </div>
    }
            export default EmployeesGenerations;
