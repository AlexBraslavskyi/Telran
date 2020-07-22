import React, {useState} from "react";
import {useSelector} from "react-redux";
import columnsMediaObject from "../config/columnsMediaConfig";
import useColumnsMedia from "../utils/mediaHook";
import DetailsTable from "./DetailsTable";
import columnsContent from "../config/tableConfig";


export default function OrdersTable(props) {
    const userData = useSelector(state=>state.userData);
    const orders = props.orders;
    const columns = useColumnsMedia(columnsMediaObject);
    const [order,setOrder] = useState({});
    const showDetails=(order)=>
        setOrder({...order});
    const backFn = () => setOrder({});
    const columnValues = Object.values(columnsMediaObject);
    const maxColumns = Math.max(...columnValues);
    function remove(orderNumber) {
        if(window.confirm('you are going to remove Order with number = ' + orderNumber)) {
            props.removeFn(orderNumber);
        }
    }
    const orderTableRecords =
        orders.map (
            (order) => {
                return <tr key={order.orderNumber}>
                    {columnsContent[columns].map((k) => {
                        return <td key={k}>{order[k]}</td>;
                    })}

                    {columns === maxColumns ? props.removeFn && userData.isAdmin ?<td>
                        <i className="fa fa-trash" style={{cursor: 'pointer'}}
                           onClick={remove.bind(this,order.orderNumber)}/>
                    </td> : null : <td>
                        <i className="fa fa-ellipsis-h" style={{cursor: 'pointer'}}
                           onClick={() => showDetails(order)}/>
                    </td>}
                </tr>
            }
        );
    return order.orderNumber ? <DetailsTable order={order}
                                  removeFn={userData.isAdmin && props.removeFn ? remove : null} backFn={backFn}/>
                                  :<div className='center' style={{"margin":"3vw"}}>
         <table className="table">
            <thead>
            <tr>
                { columnsContent[columns]
                    .map((k) => {
                        return <th key={k}>{k}</th>
                    }) }
                {(props.removeFn && userData.isAdmin) || columns < maxColumns ? <th/> : null}

            </tr>
            </thead>
            <tbody>
            {orderTableRecords}
            </tbody>
        </table>
    </div>
}