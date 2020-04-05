import React from "react";
import {Link} from "react-router-dom";
import {pathEmployees,pathGenerations,pathSalaryStatistics,pathSearch,pathTitleStatistics} from "./EmployeesConfig";

export default function EmployeesNav(props) {
return <ul className="nav">
    <li className="nav-item">
        <Link to={pathEmployees}>
            <span className='nav-link'>Employees</span>
        </Link>
    </li>
    <li className='nav-item'>
        <Link to={pathTitleStatistics}>
            <span className='nav-link'>Title Statistics</span>
        </Link>
    </li>
    <li className='nav-item'>
        <Link to={pathGenerations}>
            <span className='nav-link'>Generate Employees</span>
        </Link>
    </li>
    <li className='nav-item'>
        <Link to={pathSalaryStatistics}>
            <span className='nav-link'>Salary Statistics</span>
        </Link>
    </li>
    <li className='nav-item'>
        <Link to={pathSearch}>
            <span className='nav-link'>Search</span>
        </Link>
    </li>
</ul>
}
