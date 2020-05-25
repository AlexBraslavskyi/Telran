import React, {useEffect, useState} from "react";
import _ from 'lodash'
import useSubscribeEffect from "../util/subscriberData";
export default function OrdersStatistics(props) {
  const [orders,setOrders] = useSubscribeEffect(props.ordersService,props.ordersService.getOrders,1000)
    const listItems = Object.entries
    (_.countBy(orders, 'coffee')).map(e => {
        return <li>coffee:{e[0]}; count:{e[1]}</li>
    })


    return <div>
        <ul>
            {listItems}
        </ul>
    </div>
}
