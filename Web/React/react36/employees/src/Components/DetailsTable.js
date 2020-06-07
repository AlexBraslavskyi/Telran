import React from 'react'

const DetailsTable = (props) =>{
    const employee = props.employee;
    const removeFn = props.removeFn;
    const backFn = props.backFn;
    return <div className = 'card'>
<h4 className='card-header'>  Employee {employee.name}{"-"}{employee.id}</h4>
{Object.entries(employee).map(e=>{
    return <h5 style={{marginLeft:'20px'}} key={e[0]}>{e[0]}{" "}:{" "}{e[1]}</h5>
})}
<div><i className="fa fa-arrow-left fa-3x" style={{cursor: 'pointer',marginLeft:'20px'}}
        onClick={()=>{backFn()}}/>
        {removeFn ?<i className="fa fa-trash fa-3x" style={{cursor: 'pointer'}}
                      onClick={()=>{removeFn(employee.id);backFn()}}/>:null}</div>

    </div>
}
export default DetailsTable;