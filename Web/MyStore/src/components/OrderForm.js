import React, {useEffect, useState} from 'react';
import {nameMinLength} from "../config/ShopConfig";
import {getInputElement} from "../utils/inputElements";
import {getRandomOrderNumb} from '../utils/random';
import CheckoutComp from "./CheckoutComp";
import Cart from "./Cart";


const letters = /^[a-zA-Z]+[\-'\s]?[a-zA-Z ]+$/;

export const OrderForm = (props) => {
    let [backFl, setBackFl] = useState(0);
    let [nextFl, setNextFl] = useState(0);
    let items = props.addedItems;
    let total = props.total;
    let delivery = props.delivery;
    let order, setOrder, error, setError, emailAddress, setEmailAddress,
        name, setName, address, setAddress, phone, setPhone, passport, setPassport, formRef, setFormRef, comment,
        setComment;
    [order, setOrder] = useState({
        orderNumber: getRandomOrderNumb(),
        orderDate: new Date().toLocaleDateString(),
        emailAddress: '',
        name: '',
        address: '',
        passport: '',
        phone: '',
        comment: '',
        items: items,
        total: total,
        delivery: delivery ? delivery : "0",
        paymentMethod: 'Empty',
    });
    [error, setError] = useState({errorPassport: '', errorName: '', errorEmail: '', errorPhone: ''});
    [emailAddress, setEmailAddress] = useState({value: '', controlError: 0});
    [name, setName] = useState({value: '', controlError: 0});
    [address, setAddress] = useState({value: '', controlError: 0});
    [phone, setPhone] = useState({value: '', controlError: 0});
    [passport, setPassport] = useState({value: '', controlError: 0});
    [comment, setComment] = useState({value: '', controlError: 0});
    [formRef, setFormRef] = useState(null);
    console.log(order)

    function submit(event) {
        event.preventDefault();
        setNextFl(1);
        formRef.reset();
        setFormRef(null);
        setError({...error});
        document.getElementById("content-form").style.display = "none";
    }

    useEffect(() => {
        setBackFl(backFl);
        setNextFl(nextFl);
        setOrder(order);
        setPhone(phone);
        setName(name);
        setEmailAddress(emailAddress);
        setAddress(address);
        setComment(comment);
        setPassport(passport);
    }, [error])


    function handlerName(event) {
        name = event.target.value;
        error = {errorName: ''}
        if (name == "") {
            name = {value: name, controlError: 0}
            order.name = name.value;
        } else if (name.length < nameMinLength) {
            error = {
                errorName: 'Minimal name length should be '
                    + nameMinLength + ' symbols'
            }
            name = {value: name, controlError: -1}
            order.name = name.value;
        } else if (!name.match(letters)) {
            error = {errorName: 'Name mast content only english letters'}
            name = {value: name, controlError: -1}
            order.name = name.value;
        } else {
            error = {errorName: ''}
            name = {value: name, controlError: 1}
            order.name = name.value;
        }
        setError({...error})
    }

    function handlerNonValidated(event) {
        order[event.target.name] = event.target.value;
        setError({...error})
    }

    function handlerEmail(event) {
        emailAddress = event.target.value;
        error = {errorEmail: ''}
        const format = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;

        if (emailAddress == "") {
            emailAddress = {value: emailAddress, controlError: 0}
            order.emailAddress = emailAddress;
        } else if (!format.test(emailAddress)) {
            error = {errorEmail: 'Wrong email format'}
            emailAddress = {value: emailAddress, controlError: -1}
            order.emailAddress = emailAddress;
        } else {
            error = {errorEmail: ''}
            order.emailAddress = emailAddress;
            emailAddress = {value: emailAddress, controlError: 1}
        }
        setError({...error})
    }

    function handlerPhone(event) {
        phone = event.target.value;
        error = {errorPhone: ''}
        const format = /^\+?(972|0)(\-)?0?(([23489]{1}\d{7})|[5]{1}\d{8})$/;

        if (phone == "") {
            phone = {value: phone, controlError: 0}
            order.phone = phone;
        } else if (!format.test(phone)) {
            error = {errorPhone: 'Wrong phone format'}
            phone = {value: phone, controlError: -1}
            order.phone = phone;
        } else {
            error = {errorPhone: ''}
            order.phone = phone;
            phone = {value: phone, controlError: 1}
        }

        setError({...error})
    }

    function backFn() {
        setBackFl(1)
        document.getElementById("content-form").style.display = "none";
    }

    const showCart = () => {
        return <Cart/>
    }
    const showPayPal = () => {
        return <CheckoutComp total={props.total} order={order} ordersService={props.ordersService}/>
    }
    if (backFl === 1) {
        return showCart()
    }
    if (nextFl === 1) {
        return showPayPal()
    }

    function validate() {
        const res = notErrors()
            && allFields();
        return res;
    }


    function notErrors() {
        return Object.values(error)
            .reduce((res, field) => {
                return res && !field
            }, true)
    }

    function allFields() {
        return Object.values(order)
            .reduce((res, field) => {
                return res && field
            }, true)
    }


    return <div className="content" id="content-form">
        <div className="row" style={{width: "60%"}}>
            <form className="col s12" id="formValidate" ref={(ref) => formRef = ref} novalidate="novalidate"
                  onSubmit={submit}>
                <div className="center">
                    <h3 style={{marginTop: "5vh"}}> - Personal information -</h3>
                </div>
                {getInputElement('text',
                    'name', 'Name',
                    handlerName, error.errorName, '', 'account_circle')}
                {getInputElement('email',
                    'emailAddress', 'Email',
                    handlerEmail, error.errorEmail, '', 'email')}
                {getInputElement('text',
                    'phone', 'Phone number',
                    handlerPhone, error.errorPhone, '', 'phone')}

                {getInputElement('text',
                    'address', 'Address',
                    handlerNonValidated, "", '', 'home')}
                {getInputElement('text',
                    'passport', 'Passport',
                    handlerNonValidated, "", '', 'local_library',)}
                {getInputElement('text',
                    'comment', 'Comment',
                    handlerNonValidated, "", '', 'comment')}
                <div className="buttons-form">
                    <button type="submit" name="action" className="btn waves-effect waves-light grey"
                            disabled={!validate()}
                    > Next <i className="fa fa-arrow-circle-o-right"></i>
                    </button>
                    <button className="btn waves-effect waves-right red" onClick={backFn}
                    >Back <i className="fa fa-arrow-circle-o-left"></i>
                    </button>
                </div>
            </form>
            {showCart}
            {showPayPal}
        </div>
    </div>
}
