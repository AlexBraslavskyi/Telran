import React, {useEffect, useState} from "react";
import _ from 'lodash'
import {useSelector} from "react-redux";
export default function OrdersStatistics() {
    const orders = useSelector(state => state.orders);
    const listItems = Object.entries
    (_.countBy(orders, 'coffee')).map(e => {
        return <li>coffee:{e[0]}; count:{e[1]} key={e[0]}</li>
    })


    return <div>
        <ul>
            {listItems}
        </ul>
    </div>
}
