import React from "react";


export default function OrdersStatistics(prors) {
    const listItems = JSON.stringify(prors.statistics);//hw table
return <div>
    <ul>
        {listItems}
    </ul>
</div>
}