import React, {useState} from "react";
import {useSelector} from "react-redux";
import columnsMediaObject from "../config/columnsMediaConfig";
import useColumnsMedia from "../utils/mediaHook";
import DetailsTable from "./DetailsTable";


export default function EmployeesTable(props) {
    const userData = useSelector(state=>state.userData);
    const employees = props.employees;
    const columns = useColumnsMedia(columnsMediaObject);
    const [employee,setEmployee] = useState({});
    const showDetails=(employee)=>
        setEmployee({...employee});
    const backFn = () => setEmployee({});
    function remove(id) {
        if(window.confirm('you are going to remove Employee ' +
            'with id=' + id)) {
            props.removeFn(id);
        }
    }
    const employeeTableRecords = employees.map (
        (employee) => {
            return <tr key={employee.id}>
                <td>{employee.id}</td>
                <td>{employee.name}</td>
                {columns >2 ? <td>{employee.emailAddress}</td>:null}
                {columns >3 ?<td>{employee.gender}</td>:null}
                {columns >4 ?<React.Fragment>
                    <td>{employee.salary}</td>
                    <td>{employee.title}</td>
                    <td>
                        {props.removeFn&&userData.isAdmin?<i className="fa fa-trash" style={{cursor: 'pointer'}}
                       onClick={remove.bind(this,employee.id)}/>:null}</td></React.Fragment>
                    :<i style={{cursor: 'pointer'}}
                    onClick={() => showDetails(employee)}
                    className="fa fa-ellipsis-h"/>}
            </tr>
        }
    )
    return employee.id ?<DetailsTable employee = {employee} removeFn={userData.isAdmin?
        props.removeFn:null} backFn = {backFn}></DetailsTable>:<div className='center' style={{"margin":"3vw"}}>
        <table className="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                {columns >2 ?  <th>Email</th>:null}
                {columns >3 ? <th>Gender</th>:null}
                {columns >4 ? <React.Fragment><th>Salary</th>
                <th>Title</th>
                {props.removeFn&&userData.isAdmin? <th>Delete</th> :null}
                </React.Fragment>:<div/>}
            </tr>
            </thead>
            <tbody>
            {employeeTableRecords}
            </tbody>
        </table>
    </div>
}