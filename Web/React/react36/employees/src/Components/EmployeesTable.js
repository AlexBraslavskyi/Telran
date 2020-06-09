import React, {useState} from "react";
import {useSelector} from "react-redux";
import columnsMediaObject from "../config/columnsMediaConfig";
import useColumnsMedia from "../utils/mediaHook";
import DetailsTable from "./DetailsTable";
import columnsContent from "../config/tableConfig";


export default function EmployeesTable(props) {
    const userData = useSelector(state=>state.userData);
    const employees = props.employees;
    const columns = useColumnsMedia(columnsMediaObject);
    const [employee,setEmployee] = useState({});
    const showDetails=(employee)=>
        setEmployee({...employee});
    const backFn = () => setEmployee({});
    const columnValues = Object.values(columnsMediaObject);
    const maxColumns = Math.max(...columnValues);
    function remove(id) {
        if(window.confirm('you are going to remove Employee ' +
            'with id=' + id)) {
            props.removeFn(id);
        }
    }
    const employeeTableRecords =
        employees.map (
            (employee) => {
                return <tr key={employee.id+employee.salary}>
                    {columnsContent[columns].map((k) => {
                        return <td key={k}>{employee[k]}</td>;
                    })}

                    {columns === maxColumns ? props.removeFn && userData.isAdmin ?<td>
                        <i className="fa fa-trash" style={{cursor: 'pointer'}}
                           onClick={remove.bind(this,employee.id)}/>
                    </td> : null : <td>
                        <i className="fa fa-ellipsis-h" style={{cursor: 'pointer'}}
                           onClick={() => showDetails(employee)}/>
                    </td>}
                </tr>
            }
        );
    return employee.id ? <DetailsTable employee={employee}
                                  removeFn={userData.isAdmin && props.removeFn ? remove : null} backFn={backFn}/>
                                  :<div className='center' style={{"margin":"3vw"}}>
         <table className="table">
            <thead>
            <tr>
                { columnsContent[columns]
                    .map((k) => {
                        return <th key={k}>{k}</th>
                    }) }
                {(props.removeFn && userData.isAdmin) || columns < maxColumns ? <th/> : null}

            </tr>
            </thead>
            <tbody>
            {employeeTableRecords}
            </tbody>
        </table>
    </div>
}