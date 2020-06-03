import React, {useEffect, useState} from "react";
import {OrderForm} from "./OrderForm";
import {useSelector} from "react-redux";
const Orders = (props) => {
    const userData = useSelector(state=>state.userData)
    const [flAddOrder, setFlAddOrder] = useState(false);
    const orders = useSelector(state=>state.orders)
    const showOrderForm = () => {
        setFlAddOrder(true);
    }
    function addOrder(order) {
        const ind = orders.findIndex
        (o => o.email == order.email);
        if (ind >= 0 ) {
            return false;
        }
       props.ordersService.addOrder(order).then(() => {

       }, error => {alert(JSON.stringify(error))});

       setFlAddOrder(false)
        return true;
    }
    function deleteOrder(email) {
        if (window.confirm(`You are going to delete order ${email}`)) {
            props.ordersService.deleteOrder(email).then(() => {
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
                    {userData.isAdmin?<i style={styleCursor}
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
        {userData.isAdmin?<i style={styleCursor} onClick={showOrderForm} className="fa fa-plus-circle"></i>:null}

    </div>

}

export default Orders;
