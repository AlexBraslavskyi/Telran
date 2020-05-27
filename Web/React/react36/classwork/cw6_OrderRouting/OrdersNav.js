import React from "react";
import {Link} from "react-router-dom";

export default function OrdersNav(props) {
return <ul className="nav">
    <li className="nav-item">
        <Link to={"/orders"}>
            <span className='nav-link'>Orders</span>
        </Link>
    </li>
    <li className='nav-link'>
        <Link to={"/statistics"}>
            <span className='nav-link'>Orders Statistics</span>
        </Link>
    </li>

</ul>
}