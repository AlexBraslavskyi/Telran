import React from "react";
import {Link} from "react-router-dom";
import {LINKS} from "../config/EmployeesConfig";




export const EmployeesNav = (props) =>{
        const navItems = LINKS.map(link => {
                return <button key={link.path} type="button" className='btn btn-info'>
                    <Link to={link.path}>
                        <span className='nav-link'>{link.image}{link.label}</span>
                    </Link>
                </button>
        })
        return <div className="btn btn-group">{navItems}</div>
    }

