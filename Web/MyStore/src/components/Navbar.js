import React from 'react';
import { Link } from 'react-router-dom'
import { connect } from 'react-redux';
import firebase from "firebase";
import {useDispatch, useSelector} from "react-redux";
import useMedia from "../utils/mediaHook";
import navMediaObject from "../config/navMediaConfig";
import MobileNavMenu from "./mobileNavMenu";
import {actionFlagMobMenu, addToCart} from "./actions/actions";
import MobMenuService from "../services/MobMenuService";
import {pathCart, pathShop, pathOrders, pathHome, pathLogout, pathLogin, pathProducts} from '../config/ShopConfig';
 


const Navbar = (props)=>{
    console.log(props.userData);
    const buttons = useMedia(navMediaObject);
    const dispatch = useDispatch();
    const mobMenuService = new MobMenuService();
    let flagMobMenu =  useSelector(state=>state.flagMobile);
    const userData = props.userData
    const quantity = useSelector(state=>state.quantity);
    let itemsCount = quantity;
    // const log =
    //     userData.username ? <div> <span className='sing-bar'>
    //         {userData.isAdmin?<img className='img-avatar' src={require('../images/admin.jpg')}/>:
    //             userData.username=='user@tel-ran.co.il'? <img className='img-avatar' src={require('../images/user.png')}/>:
    //                 <img className='img-avatar' src={firebase.auth().currentUser.photoURL}/>}
    //             {' '+ userData.username}</span>
    //             <button type="button" className='btn btn-warning'>
    //                 <Link to={pathLogout}>
    //                     <span className="nav-link"><i style={{'paddingRight':'2px'}} className="fa fa-lock"/>Sing out</span>
    //                 </Link>
    //             </button>
    //         </div>
    //         :  <div>
    //             <button type="button" className='btn btn-warning'>
    //                 <Link to={pathLogin}>
    //                     <span className="nav-link"><i style={{'paddingRight':'2px'}} className="fa fa-unlock"/>Sing in</span>
    //                 </Link>
    //             </button>
    //         </div>


    function showDetails (flag){

        dispatch(actionFlagMobMenu({flag:mobMenuService.changeFlag(flag)}))

        return true;
    }

    const navItems =
    <nav>
            <div className="nav-wrapper">
                <img className = 'left' style = {{width:'7vw',height:'10vw',marginLeft:'5vw'}}src={require('../style/images/Daco_4048601.png')}/>
                    <Link to={pathHome} className = "left" style={{fontSize:'2.5vw',fontFamily:'fantasy'}}> 
                        Happy Balloons</Link>
                    <ul className="right">
                        <li><Link to={pathShop}>Shop</Link></li>
                        <li><Link to={pathOrders}>Orders</Link></li>
                        <li><Link>Statistics</Link></li>
                        <li><Link>Search</Link></li>
                        <li><Link to={pathProducts}>Products</Link></li>
                        <li><Link>Contact</Link></li>
                        <li><Link>My Cabinet</Link></li>
                        <li><Link style={{height:'64px'}} to={pathCart}><span className = 'basketQuant'>{itemsCount?itemsCount:null}</span>
                        <i className="fa fa-shopping-bag fa-2x"/>
                            </Link></li>
                        {!userData.username ? <li><Link to={pathLogin}>Login</Link></li>
                            : <li><Link to={pathLogout}>Sign out</Link></li>}
                    </ul>
            </div>  
            </nav>


    return buttons ===1 ?<div className='navbar-fixed'>
            <div>{navItems}</div>
            {/*<div className='sing-bar'>{log}*/}
            {/*</div>*/}
    </div>:
        <div className='navbar-fixed'>
            {flagMobMenu.flag==='none'?<i style={{cursor: 'pointer',marginLeft:'15px'}}
                                          onClick={()=>showDetails(flagMobMenu.flag)}
                                          className="fa fa-bars fa-3x"/>:
                <i style={{cursor: 'pointer',marginLeft:'15px'}}onClick={()=>showDetails(flagMobMenu.flag)}
                   className="fa fa-times fa-3x"/>}
            <MobileNavMenu/>
            {/*<div className='sing-bar'>{log}</div>*/}
        </div>
}

const mapStateToProps = (state)=>{
    return {
        quantity: state.quantity,
    }
}
    const mapDispatchToProps = (dispatch)=>{
        return{
            addToCart: (id)=>{dispatch(addToCart(id))}
        }
  }
export default connect(mapStateToProps,mapDispatchToProps)(Navbar);