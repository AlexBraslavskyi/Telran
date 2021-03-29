import * as React from "react";
import _ from 'lodash';
import {useSelector} from "react-redux";
import OrdersTable from './OrdersTable';

const Orders = (props) => {

    const orders = useSelector(state => state.orders);

    function updateOrder(order) {
        props.ordersService.addOrder(order)
            .then(() => {
            })
        return true;
    }

    function addItemToOrder(currentOrder, item) {
        const index = _.findIndex(currentOrder.items, i => i.id === item.id);
        if (index >= 0) {
            return false;
        }
        if (window.confirm('you are going to add item with number = ' + item.id)) {
            props.ordersService.addItemToOrder(currentOrder.orderNumber, item)
                .then(() => {
                        alert(`item with number ${item.id} added successfully`)
                    }
                )
        }
    }

    function updateItem(currentOrder, item) {

        props.ordersService.addItemToOrder(currentOrder.orderNumber, item)
            .then(() => {
                }
            )
    }

    function removeOrder(orderNumber) {
        _.remove(orders, o => o.orderNumber === orderNumber);
        props.ordersService.deleteOrder(orderNumber)
            .then(() => {
            })
    }

    function removeItemFromOrder(orderNumber, item) {
        props.ordersService.deleteItemFromOrder(orderNumber, item)
            .then(() => {
            })
    }

    return <div><OrdersTable orders={orders}
                             removeFn={removeOrder}
                             removeItemFn={removeItemFromOrder}
                             addItemToOrderFn={addItemToOrder}
                             updateOrderFn={updateOrder}
                             updateItemFn={updateItem}

    />
    </div>
}
export default Orders;
