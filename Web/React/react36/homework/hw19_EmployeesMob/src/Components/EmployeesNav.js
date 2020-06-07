import React, {useState} from "react";
import {Link} from "react-router-dom";
import {LINKS, pathLogin, pathLogout} from "../config/EmployeesConfig";
import firebase from "firebase";
import {useDispatch, useSelector} from "react-redux";
import useNavMedia from "../utils/mediaHookNav";
import navMediaObject from "../config/navMediaConfig";
import MobileNavMenu from "./mobileNavMenu";
import {actionFlagMobMenu} from "../store/actions";
import MobMenuService from "../Service/MobMenuService";



export const EmployeesNav = () =>{
    const buttons = useNavMedia(navMediaObject);
    const dispatch = useDispatch();
    const mobMenuService = new MobMenuService();
    let flagMobMenu =  useSelector(state=>state.flagMobMenu);
    const userData = useSelector(state=>state.userData);
    const navItems = LINKS.map(link => {
            return link.isAdmin&&userData.isAdmin||!link.isAdmin? <button
                key={link.path} type="button" className='btn btn-info'>
                <Link to={link.path}>
                    <span className='nav-link'>{link.image}{link.label}</span>
                </Link>
            </button>:null})
    const log =
        userData.username ? <div> <span className='sing-bar'>
            {userData.isAdmin?<img className='img-avatar' src={require('../images/admin.jpg')}/>:
            userData.username=='user@tel-ran.co.il'? <img className='img-avatar' src={require('../images/user.png')}/>:
             <img className='img-avatar' src={firebase.auth().currentUser.photoURL}/>}
                {' '+ userData.username}</span>
                <button type="button" className='btn btn-warning'>
                    <Link to={pathLogout}>
                        <span className="nav-link"><i style={{'paddingRight':'2px'}} className="fa fa-lock"/>Sing out</span>
                    </Link>
                </button>
            </div>
            :  <div>
                <button type="button" className='btn btn-warning'>
                    <Link to={pathLogin}>
                        <span className="nav-link"><i style={{'paddingRight':'2px'}} className="fa fa-unlock"/>Sing in</span>
                    </Link>
                </button>
            </div>


    function showDetails (flag){

dispatch(actionFlagMobMenu({flag:mobMenuService.changeFlag(flag)}))

        return true;
    }

    return buttons ===1 ?<div className='nav'>
            <div className="btn btn-group">{navItems}</div>
            <div className='sing-bar'>{log}</div></div>:
        <div className='nav'>
             {flagMobMenu.flag==='none'?<i style={{cursor: 'pointer',marginLeft:'15px'}}
           onClick={()=>showDetails(flagMobMenu.flag)}
           className="fa fa-bars fa-3x"/>:
         <i style={{cursor: 'pointer',marginLeft:'15px'}}onClick={()=>showDetails(flagMobMenu.flag)}
            className="fa fa-times fa-3x"/>}
           <MobileNavMenu/>
        <div className='sing-bar'>{log}</div>
            </div>
    }

