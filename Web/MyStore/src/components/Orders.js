import * as React from "react";
import _ from 'lodash';
import {OrderForm,} from "./OrderForm";
import {useState,useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import OrdersTable from './OrdersTable';

const Orders = (props) => {

    const userData = useSelector(state=>state.userData);
    const orders = useSelector(state=>state.orders);
console.log(orders);
    let addedItems = props.addedItems;
    // console.log(addedItems);
    function addOrder(order) {
        const index = _.findIndex(orders,o => o.orderNumber === order.orderNumber);
        if (index >= 0) {
            return false;
        }
        props.ordersService.addOrder(order)
            .then(() => {
                    alert(`order with number ${order.orderNumber} added successfully`)
            }
            , error => {
                alert(`order with number ${order.orderNumber} already exists`)
            })
        return true;
    }

    function removeOrder(orderNumber) {
        _.remove(orders, o => o.orderNumber === orderNumber);
        props.ordersService.deleteOrder(orderNumber)
            .then(() => {
            })
    }
    if(orders.length) {
                return <div><OrdersTable orders={orders}
                                    removeFn={removeOrder}
                                    // isAdmin={userData.isAdmin}
                    />
                </div>
    }else{
                return <OrderForm addOrderFn={addOrder}
                                  addedItems={addedItems}/>
        }
    }
export default Orders;
