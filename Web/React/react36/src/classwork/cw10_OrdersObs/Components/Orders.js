import React, {useState} from "react";
import _ from 'lodash';
import OrdersStatistics from "../../cw9_ClockHooks/Components/ordersStatistics";
import OrderForm from "../../cw9_ClockHooks/Components/OrderForm";
    const Orders = (props) => {
        const [flAddOrder, setFlAddOrder] = useState(false);
        const [flRemove, setFlRemove] = useState(true);
        const orders = props.orders;


        const showOrderForm = () => {
            setFlAddOrder(true)
        }

            function addOrder(order) {

                console.log(order);
                const ind = orders.findIndex
                (o => o.email == order.email);
                if (ind >= 0) {
                    return false;
                }
                orders.push(order);
                props.updateOrdersFn(orders);
                setFlAddOrder(false)
                return true;
            }

            function deleteOrder(email) {
                console.log('email', email);
                if (window.confirm(`You are going to delete order ${email}`)) {
                    _.remove(orders, order => order.email === email);
                  setFlRemove(!flRemove);

                }
                return true;

            }

            const styleCursor = {cursor: 'pointer'};
            const orderTableRecords = orders.map(
                (order) => {
                    return <tr key={order.email}>
                        <td>{order.coffee}</td>
                        <td>{order.email}</td>
                        <td>{order.size}</td>
                        <td>{order.flavor}</td>
                        <td>{order.strength}</td>
                        <td>
                            <i style={styleCursor}
                               onClick={() => deleteOrder(order.email)}
                               className="fa fa-trash "/>
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
                <i style={styleCursor} onClick={showOrderForm} className="fa fa-plus-circle"></i>

            </div>

    }

    export default Orders;
