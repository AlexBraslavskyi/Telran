import React from "react";


export default function OrdersStatistics(props) {
    const listItems = JSON.stringify(props.statistics);//hw table
return <div>
    <ul>
        {listItems}
    </ul>
</div>
}