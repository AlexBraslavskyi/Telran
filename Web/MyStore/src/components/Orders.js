import * as React from "react";
import _ from 'lodash';
import {OrderForm,} from "./OrderForm";
import {useState,useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import OrdersTable from './OrdersTable';

const Orders = (props) => {

    const userData = useSelector(state=>state.userData);
    const orders = useSelector(state=>state.orders);

    function addOrder(order) {
        const index = orders
            .findIndex(o => o.orderNumber === order.orderNumber);
        if (index >= 0) {
            return false;
        }
        props.ordersService.addOrder(order)
            .then(() => {
                // setEmployeesSwitch(0);
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
    if(orders.length>0) {
                return userData.isAdmin?<div><OrdersTable orders={orders}
                                    removeFn={removeOrder}
                                    isAdmin={userData.isAdmin}
                    />
                </div>:null;
    }else{
                return <OrderForm addOrderFn={addOrder}/>
        }
    }
export default Orders;
