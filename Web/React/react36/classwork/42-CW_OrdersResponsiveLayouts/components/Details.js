import React from "react";
const Details = (props) => {
    const order = props.order;
    const removeFn = props.removeFn;
    const backFn = props.backFn;
    return <div className="card">
        <h3 className="card-header">Order for {order.email}</h3>
        {Object.entries(order).map(e => {
            return <h6 key={e[0]}>{e[0]}:{e[1]}</h6>
        })}
        {removeFn ? <button onClick={() => {
            removeFn(order.email);
            backFn();
        }}>Remove</button> : null}
        <button onClick={backFn}>Back</button>

    </div>
}
export default Details;
