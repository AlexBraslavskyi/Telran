import React, {useState} from "react";
import _ from 'lodash';
import OrdersStatistics from "./ordersStatistics";
import OrderForm from "./OrderForm";
const Orders = (props) =>{
    let flAddOrder;
    let setFlAddOrder;
    [flAddOrder,setFlAddOrder]=useState(false)
    const orders = props.orders;
    const showOrderForm = ()=>{
        setFlAddOrder(true)
    }


    function addOrder(order) {
        const orders = props.orders;
        const ind = orders.findIndex(o=>o.email===order.email)
        if(ind>=0){
            return false;
        }
        orders.push(order);
        props.updateOrdersFn(orders);
        setFlAddOrder(false);
        return true;
    }

   function deleteOrder(email){
        const orders = props.orders;
        if(window.confirm `You are going to delete order ${email}`){
            _.remove(orders, order => order.email === email)
            props.updateOrdersFn(orders);
        }
    }
        const styleCursor = {cursor:"pointer"}
        const orderTableRecords =orders .map((order) => {
            return <tr key={order.email}>
                     <td>{order.coffee}</td>
                     <td>{order.email}</td>
                     <td>{order.size}</td>
                     <td>{order.flavor}</td>
                     <td>{order.strength}</td>
                <td>
                <i style={styleCursor}
                   onClick={deleteOrder(order.email)}
                className="fa fa-trash"></i>
                </td>
                     </tr>})

            return flAddOrder? <OrderForm addFn={addOrder}/>:<div>
                <table className="table">
                    <thead>
                    <tr>
                        <th>Coffee</th>
                        <th>Email</th>
                        <th>Size</th>
                        <th>Flavor</th>
                        <th>Strength</th>
                    </tr>
                    </thead>
                    <tbody>
                    {orderTableRecords}
                    </tbody>
                </table>
                <i style={styleCursor} onClick={showOrderForm} className="fa fa-plus-circle"></i>
            </div>

    }
export default Orders;