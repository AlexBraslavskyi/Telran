import React from 'react';
import { Link } from 'react-router-dom'
import { connect } from 'react-redux';
 const Navbar = (props)=>{
     let itemsCount = props.addedItems.length;
     console.log(itemsCount); 
    return(
            <nav className="nav-wrapper">
                <div className="container">
                <img className = 'left' style = {{width:'7vw',height:'10vw'}}src={require('../style/images/Daco_4048601.png')}/>
                    <Link to="/" className = "left" style={{fontSize:'2.5vw'}}> 
                        Happy Balloons</Link>
                    
                    <ul className="right">
                        <li><Link to="/shop">Shop</Link></li>
                        <li><Link to="/cart">My cart</Link></li>
                        <li><Link to="/cart"><span className = 'basketQuant'>{itemsCount?itemsCount:null}</span>
                        <i className="fa fa-shopping-bag fa-2x"/>
                             
                            </Link></li>
                        <li><Link to="/login">Login</Link></li>
                    </ul>
                </div>
            </nav>  
    )
}
const mapStateToProps = (state)=>{
    return {
        addedItems: state.addedItems,
    }
  }
export default connect(mapStateToProps)(Navbar);