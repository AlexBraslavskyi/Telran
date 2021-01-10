  
import React, {Component, useState} from 'react'
import {connect, useSelector} from 'react-redux'
import {addDelivery, addToCart} from './actions/actions'
import { Link } from 'react-router-dom'
import {DELIVERY, pathOrderForm, pathOrders} from '../config/ShopConfig'
import {OrderForm} from "./OrderForm";
import Orders from "./Orders";
import Cart from "./Cart";
import OrdersFirebaseService from "../services/OrdersFirebaseService";
const Recipe = (props) => {

    const ordersService =
        new OrdersFirebaseService('orders');
    let [ordersSwitch, setOrdersSwitch] = useState(0)

    const handleChecked = (e) => {
        if (e.target.checked) {
            props.addDelivery();
        } else {
            props.subtractDelivery();
        }
    }

    const showOrders = () => {

        setOrdersSwitch(1)

    }
    const showCart = () => {
        return <div className="container">
            <div className="collection">
                <li className="collection-item">
                    <label>
                        <input type="checkbox" onChange={handleChecked}/>
                        <span>Delivery : {DELIVERY} ₪</span>
                    </label>
                </li>
                <li className="collection-item"><b>Total: {props.total} ₪</b></li>
            </div>
            <div className="checkout">
                <button className="waves-effect waves-light btn" onClick={showOrders}>
                        <span style={{color: 'white'}}><i
                            className="material-icons right">send</i>Submit</span>
                </button>
            </div>
        </div>

    }

    if (ordersSwitch == 0) {
        return showCart()
    }
    return <OrderForm addedItems={props.addedItems}
                   ordersService = {ordersService}
                   total = {props.total}
                   delivery = {props.delivery}
    />
}

const mapStateToProps = (state)=>{
    return{
        addedItems: state.addedItems,
        total: state.total,
        delivery: state.delivery,
    }
}

const mapDispatchToProps = (dispatch)=>{
    return{
        addDelivery: ()=>{dispatch({type: 'ADD_DELIVERY'})},
        subtractDelivery: ()=>{dispatch({type: 'SUB_DELIVERY'})}

    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Recipe)