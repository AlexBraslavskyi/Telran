import React from "react";
import {Link} from "react-router-dom";

export default function EmployeesNav(props) {
return <ul className="nav">
    <li className="nav-item">
        <Link to={"/employees"}>
            <span className='nav-link'>Employees</span>
        </Link>
    </li>
    <li className='nav-link'>
        <Link to={"/statistics"}>
            <span className='nav-link'>Employees Statistics</span>
        </Link>
    </li>

</ul>
}
