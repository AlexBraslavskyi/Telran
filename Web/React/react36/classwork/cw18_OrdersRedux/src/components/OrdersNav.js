import React from 'react';
import {Link} from "react-router-dom";
import {useSelector} from "react-redux";
export default function OrdersNav() {
const userData = useSelector(state=>state.userData)
return <ul className="nav">
    <li className="nav-item">
        <Link to={'/orders'}>
            <span className="nav-link">Orders</span>
        </Link>
    </li>
    {userData.isAdmin? <li className="nav-item">
        <Link to={'/statistics'}>
            <span className="nav-link">Orders Statistics</span>
        </Link>
    </li>:null}
    <li className="nav-item">
        <Link to={'/login'}>
            <span className="nav-link">Sign in</span>
        </Link>
    </li>
    {userData.username ? <li className="nav-item">
        <Link to={'/logout'}>
            <span className="nav-link">{userData.username}</span>
        </Link>
    </li> : null}
</ul>
}
