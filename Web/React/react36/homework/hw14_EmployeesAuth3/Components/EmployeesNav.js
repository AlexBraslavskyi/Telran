import React from "react";
import {Link} from "react-router-dom";
import {LINKS, pathLogin, pathLogout} from "../config/EmployeesConfig";


export const EmployeesNav = (props) =>{
    const username = props.userData.username;
    const isAdmin = props.userData.isAdmin;
       const navItems = LINKS.map(link => {
           if(isAdmin==link.isAdmin){
                return <button key={link.path} type="button" className='btn btn-info'>
                    <Link to={link.path}>
                        <span className='nav-link'>{link.image}{link.label}</span>
                    </Link>
                </button>}
           else if(isAdmin||!username){
               return <button key={link.path} type="button" className='btn btn-info'>
               <Link to={link.path}>
                   <span className='nav-link'>{link.image}{link.label}</span>
               </Link>
           </button>}
        })
           const log =
    username ? <div> <span className='sing-bar'>
        {!isAdmin?<img className='img-avatar' src={require('../images/user.png')}/>:<img className='img-avatar' src={require('../images/admin.jpg')}/>}
            {' '+ username}</span>
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

