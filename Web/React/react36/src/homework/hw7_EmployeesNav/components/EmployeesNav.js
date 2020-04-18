import React from "react";
<<<<<<< HEAD
import {Link} from "react-router-dom";
import {LINKS} from "../config/employees_config";
=======
import {LINKS} from "../config/employees_configuration";
import {Link} from "react-router-dom";
>>>>>>> a412a12be1ebf17da6e02d8f1baffe6278bb462a
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
