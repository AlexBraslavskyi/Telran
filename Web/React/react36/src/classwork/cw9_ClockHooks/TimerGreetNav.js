import React from "react";
import {Link} from "react-router-dom";

export const TimerGreetNav = ()=>{

    return <ul className='nav'>
        <li className='nav-item'>
            <Link  to='/timer' className='nav-link'>Timer</Link>
        </li>
         <li className='nav-item'>
        <Link  to='/greeting' className='nav-link'>Greeting</Link>
        </li>
    </ul>
}