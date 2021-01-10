import * as React from "react";
import _ from 'lodash';
import {OrderForm,} from "./OrderForm";
import {useState,useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import OrdersTable from './OrdersTable';

const Orders = (props) => {

    const userData = useSelector(state=>state.userData);
    const orders = useSelector(state=>state.orders);
    let addedItems = props.addedItems;
    let total = props.total;
    let delivery = props.delivery;
    // function addOrder(order) {
    //     const index = _.findIndex(orders,o => o.orderNumber === order.orderNumber);
    //     if (index >= 0) {
    //         return false;
    //     }
    //     props.ordersService.addOrder(order)
    //         .then(() => {
    //                 alert(`order with number ${order.orderNumber} added successfully`)
    //         }
    //         , error => {
    //             alert(`order with number ${order.orderNumber} already exists`)
    //         })
    //     return true;
    // }
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
                    // , error => {
                    //     alert(`item with number ${item.id} already exists`)
                    // }
                )
        }
    }
    function updateItem(currentOrder, item) {

            props.ordersService.addItemToOrder(currentOrder.orderNumber, item)
                .then(() => {}
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
    // if(userData.isAdmin) {
                return <div><OrdersTable orders={orders}
                                    removeFn={removeOrder}
                                         removeItemFn = {removeItemFromOrder}
                                         addItemToOrderFn = {addItemToOrder}
                                         updateOrderFn = {updateOrder}
                                         updateItemFn={updateItem}

                    />
                </div>
    // }else{
    //             return <OrderForm addOrderFn={addOrder}
    //                               addedItems={addedItems}
    //                               total = {total}
    //                               delivery = {delivery}/>
    //     }
    }
export default Orders;
