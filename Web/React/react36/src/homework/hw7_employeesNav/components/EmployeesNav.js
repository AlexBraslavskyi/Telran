import React from "react";
import {LINKS} from "../config/employees_configuration";
import {Link} from "react-router-dom";
export default function EmployeesNav(props) {
const navItems = LINKS.map(link => {
    return <li key={link.path} className="nav-item">
        <Link to={link.path} className="nav-link">
            <span >{link.label}</span>
        </Link>
    </li>
})
    return <ul className="nav">
        {navItems}
    </ul>
}
