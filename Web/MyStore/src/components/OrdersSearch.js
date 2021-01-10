// import React, {useState} from "react";
// import {getInputElement, getSelectElement} from "../util/input_elements";
// import OrdersTable from "./OrdersTable";
//
// const OrdersSearch = (props) => {
//     let salaryTo, salaryFrom, title, employeesSearch;
//     let setSalaryTo, setSalaryFrom, setTitle, setEmployeesSearch;
//     [salaryTo, setSalaryTo] = useState(Number.MAX_VALUE);
//     [salaryFrom, setSalaryFrom] = useState(0);
//     [title, setTitle] = useState('');
//     [employeesSearch, setEmployeesSearch] = useState([]);
//     function inputHandler(event) {
//         event.preventDefault();
//         switch (event.target.name) {
//             case 'title': setTitle(event.target.value); break;
//             case 'salaryTo': setSalaryTo(event.target.value); break;
//             case 'salaryFrom': setSalaryFrom(event.target.value); break;
//         }
//     }
//     function onSubmit(event) {
//         event.preventDefault();
//         salaryTo = +salaryFrom > +salaryTo ? Number.MAX_VALUE : salaryTo;
//
//         const employees = props.employees.filter(filterFn);
//         setEmployeesSearch(employees);
//
//     }
//     function filterFn(empl) {
//         return (empl.title === title || title === '')
//             && (empl.salary >= salaryFrom) && (empl.salary <= salaryTo);
//
//     }
//     return <div className="card">
//         <div className="card-header">
//             <h3>Employees Search</h3>
//         </div>
//         <div className="card-body">
//             <form onSubmit={onSubmit}>
//                 {getSelectElement('Title', inputHandler,
//                     'title', [' ',...TITLES])}
//                 {getInputElement('number', 'salaryFrom',
//                     "Salary From",inputHandler )}
//                 {getInputElement('number', 'salaryTo',
//                     "Salary To",inputHandler )}
//                 <button type="submit">
//                     <i className="fa fa-search" />
//                 </button>
//
//             </form>
//             <OrdersTable orders={ordersSearch}/>
//         </div>
//     </div>
// }
// export default OrdersSearch;