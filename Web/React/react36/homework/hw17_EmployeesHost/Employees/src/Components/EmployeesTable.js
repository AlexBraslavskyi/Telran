import React from "react";

export default function EmployeesTable(props) {
    const isAdmin = props.isAdmin;
    function remove(id) {
        if(window.confirm('you are going to remove Employee ' +
            'with id=' + id)) {
            props.removeFn(id);
        }
    }
    const employeeTableRecords = props.employees.map (
        (employee) => {
            return <tr key={employee.id}>
                <td>{employee.id}</td>
                <td>{employee.emailAddress}</td>
                <td>{employee.name}</td>
                <td>{employee.gender}</td>
                <td>{employee.salary}</td>
                <td>{employee.title}</td>
                {props.removeFn&&isAdmin? <td>
                    <i className="fa fa-trash" style={{cursor: 'pointer'}}
                       onClick={remove.bind(this,employee.id)}/>
                </td>:null}
            </tr>
        }
    )
    return <div style={{"margin":"1vw 15vw 3vw 3vw"}}>
        <table className="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Email</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Salary</th>
                <th>Title</th>
                {props.removeFn&&isAdmin? <th>Delete</th> :null}
            </tr>
            </thead>
            <tbody>
            {employeeTableRecords}
            </tbody>
        </table>
    </div>
}