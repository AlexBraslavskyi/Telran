import React from  'react';

export default function EmployeesTable(props) {
    function remove(id) {
        if(window.confirm('you are going to remove Employee ' +
            'with id=' + id)) {
            props.removeFn(id);
        }
    }
    console.log(props.employees);
    const employeeTableRecords = props.employees.map (
        (employee) => {
            return <tr key={employee.id}>
                <td>{employee.id}</td>
                <td>{employee.emailAddress}</td>
                <td>{employee.name}</td>
                <td>{employee.gender}</td>
                <td>{employee.salary}</td>
                <td>{employee.title}</td>
                <td>
                    <i className="fa fa-trash" style={{cursor: 'pointer'}}
                    onClick={remove.bind(this,employee.id)}/>
                </td>
            </tr>
        }
        )
    return <table className="table">
        <thead>
        <tr>
            <th>id</th>
            <th>emailAddress</th>
            <th>name</th>
            <th>gender</th>
            <th>salary</th>
            <th>title</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        {employeeTableRecords}
        </tbody>
    </table>
}
