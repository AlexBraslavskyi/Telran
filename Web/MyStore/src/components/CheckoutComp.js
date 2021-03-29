import React, {useEffect, useState} from "react";
import PayPal from "./PayPal";
import _ from "lodash";
import {useSelector} from "react-redux";


export default function CheckoutComp(props) {
    const [cashFl, setCashFl] = useState(0);
    const [visaFl, setVisaFl] = useState(0);
    const items = useSelector(state => state.addedItems);
    const orders = useSelector(state => state.orders);
    const order = props.order;
    console.log(items)

    function handlerCash() {
        setCashFl(1);
        setVisaFl(0)
    }

    function handlerVisa() {
        setVisaFl(1);
        setCashFl(0)
    }

    useEffect(() => {
        setCashFl(cashFl);
        setVisaFl(visaFl);
    }, [])

    function sendOrder() {
        order.paymentMethod = "CASH";
        addOrder(order);

    }

    function addOrder(order) {
        const index = _.findIndex(orders, o => o.orderNumber === order.orderNumber);
        if (index < 0) {
            props.ordersService.addOrder(order)
                .then(() => {
                        alert(`order with number ${order.orderNumber} added successfully`)
                    }
                    , error => {
                    })
            return true;
        } else {
            alert(`order with number ${order.orderNumber} already exists`)
            return false
        }
    }

    return <div className="center" style={{marginTop: "10%", marginBottom: "70%"}}>
        <h2>- Please choose payment method -</h2>
        <h4>Total Amount in $ : {props.total}</h4>
        <div className="center">
            <form style={{marginBottom: "20px"}}>
                <p>
                    <label>
                        <input className="with-gap" type="radio" onChange={handlerCash} name="paymentMethod"/>
                        <span>CASH</span>
                    </label>
                </p>
                <p>
                    <label>
                        <input className="with-gap" type="radio" onChange={handlerVisa} name="paymentMethod"/>
                        <span>CREDIT CARD</span>
                    </label>
                </p>
            </form>
        </div>
        {visaFl === 1 ? <PayPal total={props.total} order={order} ordersService={props.ordersService}/> : null}
        <div className="buttons-form">
            {cashFl === 1 ? <button className="btn waves-effect waves-light blue" onClick={sendOrder}>SEND
                <i className="fa fa-arrow-circle-o-right"></i></button> : null}

            <button className="btn waves-effect waves-right red" onClick=""
            >Back <i className="fa fa-arrow-circle-o-left"></i>
            </button>
        </div>
    </div>
}
