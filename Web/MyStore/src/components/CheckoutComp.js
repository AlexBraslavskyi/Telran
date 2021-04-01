import React, {useEffect, useState} from "react";
import PayPal from "./PayPal";
import _ from "lodash";
import {useSelector} from "react-redux";
import {OrderForm} from "./OrderForm";
import * as firebase from "firebase";
import UsersFirebaseService from "../services/UsersFirebaseService";


export default function CheckoutComp(props) {
    const [cashFl, setCashFl] = useState(0);
    const [visaFl, setVisaFl] = useState(0);
    const orders = useSelector(state => state.orders);
    const order = props.order;
    const items = props.addedItems;
    const ordersService = props.ordersService;
    const total = props.total;
    const delivery = props.delivery;
    const clientsService = new UsersFirebaseService('clients');
    let [backFl, setBackFl] = useState(0);

    function handlerCash() {
        setCashFl(1);
        setVisaFl(0)
    }

    function handlerVisa() {
        setVisaFl(1);
        setCashFl(0)
    }

    useEffect(() => {
        setBackFl(backFl);
        setCashFl(cashFl);
        setVisaFl(visaFl);
    }, [])

    function sendOrder() {
        order.paymentMethod = "CASH";
        addOrder(order);
        document.getElementById("hide").style.display = "none";
        document.querySelectorAll('.hide-elem').forEach(function(el) {
            el.style.display = 'none';
        });
        setCashFl(2);

    }
    function addOrder(order) {
        const index = _.findIndex(orders, o => o.orderNumber === order.orderNumber);
        if(!orders.find(o => o.emailAddress == firebase.auth().currentUser.displayName)){
            let user =  {
                emailAddress: order.emailAddress,
                name:order.name,
                address: order.address,
                passport: order.passport,
                phone: order.phone,
                password: "Test12345",
                confirmPassword: "Test12345"}
            clientsService.addSocialUser(user)
                .then(() => {
                        // alert(`User with email ${user.emailAddress} added successfully`)
                    }
                    , error => {
                        alert(`User with email ${user.emailAddress} already exists`)
                    })
        }

        if (index < 0) {
            props.ordersService.addOrder(order)
                .then(() => {
                        // alert(`order with number ${order.orderNumber} added successfully`)
                    }
                    , error => {
                    })
            return true;
        } else {
            alert(`order with number ${order.orderNumber} already exists`)
            return false
        }

    }

    function backFn() {
        setBackFl(1)
        // document.getElementById("center").style.display = "none";
    }

    function showForm() {
        return <OrderForm addedItems={items}
                          ordersService={ordersService}
                          total={total}
                          delivery={delivery}/>;
    }

    if (backFl === 1) {
        return showForm();
    }

const sendView = ()=> {

        return <div>
            <div>
                <h3>Order with number {order.orderNumber} added successfully!</h3>
                <h4>Our customer service call back you soon</h4>
                {/*<img src={require("../style/images/Balloons-Background-PNG-715x715.png")}/>*/}
            </div>
        </div>
    }

    return <div className="center" style={{marginTop: "10%", marginBottom: "70%"}}>
        <h2 className="hide-elem">- Please choose payment method -</h2>
        <h4 className="hide-elem">Total Amount in $ : {props.total}</h4>
        <div className="center">
            <form className="hide-elem" style={{marginBottom: "20px"}}>
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
        <div id="hide" className="buttons-form">
            {cashFl === 1 ? <button className="btn waves-effect waves-light blue" onClick={sendOrder}><i
                className="fa fa-arrow-circle-o-left"></i>SEND
            </button> : null}

            <button className="btn waves-effect waves-right red" onClick={backFn}
            >Back <i className="fa fa-arrow-circle-o-right"></i>
            </button>
        </div>
        {cashFl==2?sendView():null}
    </div>
}
