import React from 'react';
import { Link } from 'react-router-dom'
import { connect } from 'react-redux';
import { pathCart, pathShop } from '../config/ShopConfig';
 const Navbar = (props)=>{
     let itemsCount = props.addedItems.length;
     console.log(itemsCount);
    return(  <div class="navbar-fixed">
    <nav>
            <div className="nav-wrapper">
                <img className = 'left' style = {{width:'7vw',height:'10vw',marginLeft:'5vw'}}src={require('../style/images/Daco_4048601.png')}/>
                    <Link to="/" className = "left" style={{fontSize:'2.5vw',fontFamily:'fantasy'}}> 
                        Happy Balloons</Link>
                    <ul className="right">
                        <li><Link to={pathShop}>Shop</Link></li>
                        <li><Link to={pathCart}>My cart</Link></li>
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