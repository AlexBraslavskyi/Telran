import React, {useEffect, useState} from "react";
import _ from "lodash";
import {OrderForm} from "./OrderForm";
const Orders = (props) => {


    const isAdmin = props.isAdmin;
    const [flAddOrder, setFlAddOrder] = useState(false);
    const [orders, setOrders] = useState([]);
    let subscription;
    let polling = true;
    let intervalId;
    const getOrders = () => {
        subscription =
            props.ordersService.getOrders()
                .subscribe(ordersFromServer => {
                    setOrders(ordersFromServer)
                }, () => {}, () => {
                    if (polling) {
                        intervalId = setInterval(poller, 1000);
                    }
                })
    }

    const poller = () => {
        props.ordersService.getOrders().subscribe(ordersFromServer => {
            setOrders(ordersFromServer)
        })
    }
    useEffect(
        () => {
            getOrders();



            return () => {
                if(subscription && !subscription.closed) {
                    polling = false;
                    subscription.unsubscribe();

                }
                if (intervalId) {
                    clearInterval(intervalId)
                }
            }
        }, []
    )
    const showOrderForm = () => {
        setFlAddOrder(true);
    }
    function addOrder(order) {
        const ind = orders.findIndex
        (o => o.email == order.email);
        if (ind >= 0 ) {
            return false;
        }
       props.ordersService.addOrder(order).subscribe(() => {
           if(!subscription || subscription.closed) {
               getOrders() ;
           }
       }, error => {alert(JSON.stringify(error))});

       setFlAddOrder(false)
        return true;
    }
    function deleteOrder(email) {
        if (window.confirm(`You are going to delete order ${email}`)) {
            props.ordersService.deleteOrder(email).subscribe(() => {
                if(!subscription || subscription.closed) {
                    getOrders() ;
                }
            }, error => {alert(JSON.stringify(error))});


        }
        return true;

    }
    const styleCursor = {cursor: 'pointer'};
    const orderTableRecords = orders.map (
        (order) => {
            return <tr key={order.email}>
                <td>{order.coffee}</td>
                <td>{order.email}</td>
                <td>{order.size}</td>
                <td>{order.flavor}</td>
                <td>{order.strength}</td>
                <td>
                    {isAdmin?<i style={styleCursor}
                        onClick={() => deleteOrder(order.email)}
                        className="fa fa-trash "/>:null
                    }
                </td>
            </tr>
        }
    )

    return flAddOrder ? <OrderForm addFn={addOrder}/> : <div>
        <table className="table">
            <thead>
            <tr>
                <th>coffee</th>
                <th>email</th>
                <th>size</th>
                <th>flavor</th>
                <th>strength</th>
            </tr>
            </thead>
            <tbody>
            {orderTableRecords}
            </tbody>
        </table>
        {isAdmin?<i style={styleCursor} onClick={showOrderForm} className="fa fa-plus-circle"></i>:null}

    </div>

}

export default Orders;
