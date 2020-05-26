import React from 'react';
import {Link} from "react-router-dom";
export default function OrdersNav(props) {
    const username = props.userData.username;
    const isAdmin = props.userData.isAdmin;
return <ul className="nav">
    <li className="nav-item">
        <Link to={'/orders'}>
            <span className="nav-link">Orders</span>
        </Link>
    </li>
    {isAdmin? <li className="nav-item">
        <Link to={'/statistics'}>
            <span className="nav-link">Orders Statistics</span>
        </Link>
    </li>:null}
    <li className="nav-item">
        <Link to={'/login'}>
            <span className="nav-link">Sign in</span>
        </Link>
    </li>
    {username ? <li className="nav-item">
        <Link to={'/logout'}>
            <span className="nav-link">{username}</span>
        </Link>
    </li> : null}
</ul>
}
