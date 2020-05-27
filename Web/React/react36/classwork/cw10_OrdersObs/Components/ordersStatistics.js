import React, {useEffect, useState} from "react";
import _ from 'lodash';

export default function OrdersStatistics(props) {
    const [orders,setOrders] = useState([])
    let subscription ;

    const getOrders = ()=>{
        subscription = props.ordersService.getOrders().subscribe(ordersFromServer=>{
            setOrders(ordersFromServer)
        },error => {
            alert(JSON.stringify(error))
        })
    }
    useEffect(
        ()=> {
            getOrders();
            return ()=>{
                if(subscription&&!subscription.closed){
                    subscription.unsubscribe;
                }
            }
        },[])


    const listItems = Object.entries(_.countBy(orders, 'coffee')).map(e =>{
    return <li>coffee:{e[0]}; count:{e[1]}</li>
});
return <div>
    <ul>
        {listItems}
    </ul>
</div>
}