import React, {useEffect, useState} from "react";
import _ from "lodash";
import {OrderForm} from "./OrderForm";
import {useSelector} from "react-redux";
import useColumnsMedia from "../util/mediaHook";
import columnsMediaObject from "../config/columns-media-config";
import Details from "./Details";
const Orders = (props) => {
const ordersService = props.ordersService;
    const [flAddOrder, setFlAddOrder] = useState(false);
    const orders = useSelector(state => state.orders);
    const userData = useSelector(state => state.userData);
    const columns = useColumnsMedia(columnsMediaObject);
    const [order, setOrder] = useState({});
    const showOrderForm = () => {
        setFlAddOrder(true);
    }
    const showDetails = (order) => setOrder({...order});
    const backFn = () => setOrder({});
    function addOrder(order) {
        const ind = orders.findIndex
        (o => o.email == order.email);
        if (ind >= 0 ) {
            return false;
        }
      ordersService.addOrder(order).then(() => {

       }, error => {alert(JSON.stringify(error))});

       setFlAddOrder(false)
        return true;
    }
    function deleteOrder(email) {
console.log('email', email);
        if (window.confirm(`You are going to delete order ${email}`)) {
           ordersService.deleteOrder(email).then(() => {

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
                {columns > 2 ? <td>{order.size}</td> : null}
                {columns > 3 ? <td>{order.flavor}</td> : null}
                {columns > 4 ?<React.Fragment><td>{order.strength}</td>
                    <td>
                        {userData.isAdmin ? <i style={styleCursor}
                                               onClick={() => deleteOrder(order.email)}
                                               className="fa fa-trash "/> : null}
                    </td></React.Fragment> : <i style={styleCursor}
                                                onClick={() => showDetails(order)}
                                                className="fa fa-ellipsis-h "/>}
            </tr>
        }
    )

    return order.email ? <Details order={order}
    removeFn={userData.isAdmin ? deleteOrder : null} backFn={backFn}></Details> : flAddOrder ? <OrderForm addFn={addOrder}/> : <div>
        <table className="table">
            <thead>
            <tr>
                <th>coffee</th>
                <th>email</th>
                {columns > 2 ?<th>size</th>:null}
                {columns > 3 ? <th>flavor</th>:null}
                {columns > 4 ?<th>strength</th>:null}
                <th></th>
            </tr>
            </thead>
            <tbody>
            {orderTableRecords}
            </tbody>
        </table>
        {userData.isAdmin ? <i style={styleCursor} onClick={showOrderForm} className="fa fa-plus-circle">

        </i> : null}

    </div>

}

export default Orders;
