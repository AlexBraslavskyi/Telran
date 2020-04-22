import React from "react";
import {LINKS} from "../config/courses_configurations";
import {Link} from "react-router-dom";


export default function CoursesNav(props) {

    const navItems = LINKS.map(link=>{
        return  <li key={link.path} className='nav-item'>
            <Link to={link.path}>
                <span className='nav-link'>{link.label}</span>
            </Link>
        </li>
    })
    return <ul className='nav'>
        {navItems}
    </ul>
}