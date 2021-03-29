import React from "react";
import _ from "lodash";
import {useSelector} from "react-redux";

export default function PayPal(props) {
    const [paid, setPaid] = React.useState(false);
    const [error, setError] = React.useState(null);
    const paypalRef = React.useRef();
    const orders = useSelector(state => state.orders);
    const order = props.order;
    const index = _.findIndex(orders, o => o.orderNumber === order.orderNumber);

    function sendOrder() {
        order.paymentMethod = "VISA";
        addOrder(order);
    }

    function addOrder(order) {
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
        return <div><h5>Payment successful!</h5>
            <h5>Order with number {order.orderNumber} added successfully!</h5>
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