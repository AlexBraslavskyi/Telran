import React from "react";
import _ from 'lodash';

export default function OrdersStatistics(props) {
const listItems = Object.entries(_.countBy(props.orders, 'coffee')).map(e =>{
    return <li>coffee:{e[0]}; count:{e[1]}</li>
});
return <div>
    <ul>
        {listItems}
    </ul>
</div>
}