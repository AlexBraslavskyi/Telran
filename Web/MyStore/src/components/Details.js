import React from 'react'

const Details = (props) =>{
    const order = props.order;
    const removeFn = props.removeFn;
    const backFn = props.backFn;
    return <div className = 'card'>
<h4 className='card-header'>  Order for {order.email}</h4>
{Object.entries(order).map(e=>{
    return <h5 key={e[0]}>{e[0]}:{e[1]}</h5>
})}
{removeFn ? <button onClick={()=>{
    removeFn(order.email);
         backFn();}}
   >Remove</button>:null}
<button onClick={backFn}>Back</button>
    </div>
}
export default Details;