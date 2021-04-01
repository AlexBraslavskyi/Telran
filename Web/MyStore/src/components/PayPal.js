import React from "react";
import _ from "lodash";
import {useSelector} from "react-redux";
import * as firebase from "firebase";
import UsersFirebaseService from "../services/UsersFirebaseService";

export default function PayPal(props) {
    const [paid, setPaid] = React.useState(false);
    const [error, setError] = React.useState(null);
    const paypalRef = React.useRef();
    const orders = useSelector(state => state.orders);
    const order = props.order;
    const index = _.findIndex(orders, o => o.orderNumber === order.orderNumber);
    const clientsService = new UsersFirebaseService('clients');
    function sendOrder() {
        order.paymentMethod = "VISA";
        addOrder(order);
        document.getElementById("hide").style.display = "none";
        document.querySelectorAll('.hide-elem').forEach(function(el) {
            el.style.display = 'none';
        });
    }

    function addOrder(order) {
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
        props.ordersService.addOrder(order)
            .then(() => {
                    // alert(`order with number ${order.orderNumber} added successfully`)
                }
                , error => {
                    alert(`order with number ${order.orderNumber} already exists`)
                })
        return true;
    }

    // To show PayPal buttons once the component loads
    React.useEffect(() => {
        window.paypal
            .Buttons({
                createOrder: (data, actions) => {
                    return index < 0 ? actions.order.create({
                        intent: "CAPTURE",
                        purchase_units: [
                            {
                                description: props.order.orderNumber,
                                amount: {
                                    currency_code: "USD",
                                    value: props.total,
                                },
                            },
                        ],
                    }) : alert(`order with number ${order.orderNumber} already exists`)
                },
                onApprove: async (data, actions) => {
                    const response = await actions.order.capture();
                    setPaid(true);
                    console.log(response);
                    sendOrder(order);
                },
                onError: (err) => {
                    setError(err);
                    console.error(err);
                },
            })
            .render(paypalRef.current);
    }, []);

    // If the payment has been made
    if (paid) {
        return <div><h3>Payment successful!</h3>
            <h4>Order with number {order.orderNumber} added successfully!</h4>
        </div>

    }

    // If any error occurs
    if (error) {
        return <div>Error Occurred in processing payment! Please try again.</div>;
    }

    // Default Render
    return (
        <div>
            <div ref={paypalRef}/>
        </div>
    );
}