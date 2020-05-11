import React from 'react';
import {LINKS} from "../config/employees_config";
import {Link} from "react-router-dom";
export default function EmployeesNav (props) {
   const links =  LINKS.map(l => {
        return <li key={l.path} className="nav-item">
            <Link to={l.path}>
                <span className="nav-link">{l.label}</span>
            </Link>
        </li>
    })
    return <ul className="nav">
        {links}
    </ul>
}
