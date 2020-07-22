import React from 'react';
import { Link } from 'react-router-dom'
import { connect } from 'react-redux';
import firebase from "firebase";
import {useDispatch, useSelector} from "react-redux";
import useMedia from "../utils/mediaHook";
import navMediaObject from "../config/navMediaConfig";
import MobileNavMenu from "./mobileNavMenu";
import {actionFlagMobMenu} from "./actions/actions";
import MobMenuService from "../services/MobMenuService";
import { pathCart, pathShop, pathOrders } from '../config/ShopConfig';
 


const Navbar = (props)=>{
    const buttons = useMedia(navMediaObject);
    const dispatch = useDispatch();
    const mobMenuService = new MobMenuService();
    let flagMobMenu =  useSelector(state=>state.flagMobMenu);
    const userData = useSelector(state=>state.userData);
    let itemsCount = props.addedItems.length;

    return(  <div className="navbar-fixed">
    <nav>
            <div className="nav-wrapper">
                <img className = 'left' style = {{width:'7vw',height:'10vw',marginLeft:'5vw'}}src={require('../style/images/Daco_4048601.png')}/>
                    <Link to="/" className = "left" style={{fontSize:'2.5vw',fontFamily:'fantasy'}}> 
                        Happy Balloons</Link>
                    <ul className="right">
                        <li><Link to={pathShop}>Shop</Link></li>
                        <li><Link to={pathCart}>My cart</Link></li>
                        <li><Link to={pathOrders}>Orders</Link></li>
                        <li><Link to={pathCart}><span className = 'basketQuant'>{itemsCount?itemsCount:null}</span>
                        <i className="fa fa-shopping-bag fa-2x"/>
                            </Link></li>
                        <li><Link to="/login">Login</Link></li>
                    </ul>
            </div>  
            </nav>
            </div>
    )
}

const mapStateToProps = (state)=>{
    return {
        addedItems: state.addedItems,
    }
  }
export default connect(mapStateToProps)(Navbar);