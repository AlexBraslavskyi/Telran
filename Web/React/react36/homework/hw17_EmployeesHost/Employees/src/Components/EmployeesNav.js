import React from "react";
import {Link} from "react-router-dom";
import {LINKS, pathLogin, pathLogout} from "../config/EmployeesConfig";
import firebase from "firebase";

export const EmployeesNav = (props) =>{
    const userData = props.userData;
    const navItems = LINKS.map(link => {
            return link.isAdmin&&userData.isAdmin||!link.isAdmin? <button key={link.path} type="button" className='btn btn-info'>
                <Link to={link.path}>
                    <span className='nav-link'>{link.image}{link.label}</span>
                </Link>
            </button>:null})
    const log =
        userData.username ? <div> <span className='sing-bar'>
        {!userData.isAdmin||userData.username!='user@tel-ran.co.il'?
            <img className='img-avatar' src={firebase.auth().currentUser.photoURL}/>:userData.isAdmin?
            <img className='img-avatar' src={require('../images/admin.jpg')}/>:
                <img className='img-avatar' src={require('../images/user.png')}/>}
                {' '+ userData.username}</span>
                <button type="button" className='btn btn-success'>
                    <Link to={pathLogout}>
                        <span className="nav-link"><i style={{'paddingRight':'2px'}} className="fa fa-lock"/>Sing out</span>
                    </Link>
                </button>
            </div>
            :  <div>
                <button type="button" className='btn btn-success'>
                    <Link to={pathLogin}>
                        <span className="nav-link"><i style={{'paddingRight':'2px'}} className="fa fa-unlock"/>Sing in</span>
                    </Link>
                </button>
            </div>


    return <div className='nav'><div className="btn btn-group">{navItems}</div><div className='sing-bar'>{log}</div></div>
    }

