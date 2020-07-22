import React from 'react'

const DetailsTable = (props) =>{
    const order = props.order;
    const removeFn = props.removeFn;
    const backFn = props.backFn;
    return <div className = 'card'>
<h4 className='card-header'>  Order {order.orderNumber}{"-"}{order.name}</h4>
{Object.entries(order).map(o=>{
    return <h5 style={{marginLeft:'20px'}} key={o[0]}>{o[0]}{" "}:{" "}{o[1]}</h5>
})}
<div><i className="fa fa-arrow-left fa-3x" style={{cursor: 'pointer',marginLeft:'20px'}}
        onClick={()=>{backFn()}}/>
        {removeFn ?<i className="fa fa-trash fa-3x" style={{cursor: 'pointer'}}
                      onClick={()=>{removeFn(order.orderNumber);backFn()}}/>:null}</div>

    </div>
}
export default DetailsTable;