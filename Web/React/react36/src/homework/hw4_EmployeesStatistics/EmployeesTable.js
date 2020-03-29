import React from "react";

export default function EmployeesTable(props){
        const styleCursor = {cursor: "pointer"}
        const employeeTableRecords = props.employees.map(
            (employee) => {
                return <tr key={employee.id}>
                    <td>{employee.id}</td>
                    <td>{employee.name}</td>
                    <td>{employee.emailAddress}</td>
                    <td>{employee.gender}</td>
                    <td>{employee.salary}</td>
                    <td>{employee.title}</td>
                    <td>
                        <i style={styleCursor} onClick={props.removeFn.bind(this,employee.id)} className="fa fa-trash fa-2x"></i>
                    </td>
                </tr>})
        return <div  style={{"margin":"40px"}}>
            <table className="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Salary</th>
                    <th>Title</th>
                    <th>Delete Employee</th>
                </tr>
                </thead>
                <tbody>
                {employeeTableRecords}
                </tbody>
            </table>
        </div>
 }
