
import React, {useState} from "react";
import {useSelector} from "react-redux";
import columnsMediaObject from "../config/columnsMediaConfig";
import useColumnsMedia from "../utils/mediaHook";
import DetailsTable from "./DetailsTable";
import columnsContent from "../config/tableConfig";
import MaterialTable, {MTableBody, MTableBodyRow, MTableCell, MTableHeader, MTablePagination} from "material-table";


export default function OrdersTable(props) {
    const userData = useSelector(state=>state.userData);
    const orders = props.orders;
    // const columns = useColumnsMedia(columnsMediaObject);
    // const [order,setOrder] = useState({});
    // const showDetails=(order)=>
    //     setOrder({...order});
    // const backFn = () => setOrder({});
    const columnValues = Object.values(columnsMediaObject);
    const maxColumns = Math.max(...columnValues);
    function remove(orderNumber) {
        if(window.confirm('you are going to remove Order with number = ' + orderNumber)) {
            props.removeFn(orderNumber);
        }
    }
    // const orderTableRecords =
    //     orders.map (
    //         (order) => {
    //             return <tr key={order.orderNumber}>
    //                 {columnsContent[columns].map((k) => {
    //                     return <td key={k}>{order[k]}</td>;
    //                 })}
    //
    //                 {columns === maxColumns ? props.removeFn && userData.isAdmin ?
    //                     <td>
    //                     <i className="fa fa-trash" style={{cursor: 'pointer'}}
    //                        onClick={remove.bind(this,order.orderNumber)}/>
    //                 </td> : null : <td>
    //                     <i className="fa fa-ellipsis-h" style={{cursor: 'pointer'}}
    //                        onClick={() => showDetails(order)}/>
    //                 </td>}
    //             </tr>
    //         }
    //     );
    // return order.orderNumber ? <DetailsTable order={order}
    //                               removeFn={userData.isAdmin && props.removeFn ? remove : null} backFn={backFn}/>
    //                               :<div className='center' style={{"margin":"3vw"}}>
    //      <table className="table">
    //         <thead>
    //         <tr>
    //             { columnsContent[columns]
    //                 .map((k) => {
    //                     return <th key={k}>{k}</th>
    //                 }) }
    //             {(props.removeFn && userData.isAdmin) || columns < maxColumns ? <th/> : null}
    //
    //         </tr>
    //         </thead>
    //         <tbody>
    //         {orderTableRecords}
    //         </tbody>
    //     </table>
    // </div>

    const { useState } = React;

    const [columns, setColumns] = useState([
                { title: 'Order Number', field: 'orderNumber' },
                { title: 'Name', field: 'name' },
                { title: 'Email', field: 'emailAddress' },
                { title: 'Address', field: 'address' },
                { title: 'Phone', field: 'phone' },
                { title: 'Passport', field: 'passport' },
                { title: 'Comment', field: 'comment' },
                {title:  'Payment method', field: 'paymentMethod'}
            ]);

    const [data, setData] = useState(orders.map((order)=>({orderNumber: order.orderNumber, name:
                        order.name,emailAddress:order.emailAddress,address:order.address, phone:order.phone,
                        passport:order.passport,comment:order.comment, items:order.items, total:order.total, delivery:order.delivery, paymentMethod: order.paymentMethod
                       })));

    return  (
        <MaterialTable style={{marginTop:'50px'}}
            title="- Orders - "
            columns={columns}
            data={data}
                       onRowClick={(event, rowData, togglePanel) => togglePanel()}
            editable={{
                onRowAdd: newData =>
                    new Promise((resolve, reject) => {
                        setTimeout(() => {
                            setData([...data, newData]);

                            resolve();
                        }, 1000)
                    }),
                onRowUpdate: (newData, oldData) =>
                    new Promise((resolve, reject) => {
                        setTimeout(() => {
                            const dataUpdate = [...data];
                            const index = oldData.tableData.id;
                            dataUpdate[index] = newData;
                            setData([...dataUpdate]);

                            resolve();
                        }, 1000)
                    }),
                onRowDelete: oldData =>
                    new Promise((resolve, reject) => {
                        setTimeout(() => {
                            const dataDelete = [...data];
                            const index = oldData.tableData.id;
                            dataDelete.splice(index, 1);
                            setData([...dataDelete]);

                            resolve()
                        }, 1000)
                    }),
            }}
                    detailPanel={rowData => {

                        return (
                            <div>
                            <MaterialTable   style={{margin:'10px 100px 10px 100px'}} options={{
                                search: false,
                                paging:false
                            }}
                                title="- Order details -"
                                             columns={[{ title: 'ID', field: 'id' },
                                                             { title: 'Title', field: 'title' },
                                                             { title: 'Price', field: 'price' },
                                                             { title: 'Quantity', field: 'quantity' },
                                                             { title: 'Subtotal', field: 'subtotal' },

                                                         ]}
                                             data={rowData.items.map((item)=>({id: item.id, title:
                                                 item.title, price:item.price,quantity:item.quantity, subtotal:item.price*item.quantity}))}
                                             editable={{
                                                 onRowAdd: newData =>
                                                     new Promise((resolve, reject) => {
                                                         setTimeout(() => {
                                                             setData([...data, newData]);

                                                             resolve();
                                                         }, 1000)
                                                     }),
                                                 onRowUpdate: (newData, oldData) =>
                                                     new Promise((resolve, reject) => {
                                                         setTimeout(() => {
                                                             const dataUpdate = [...data];
                                                             const index = oldData.tableData.id;
                                                             dataUpdate[index] = newData;
                                                             setData([...dataUpdate]);

                                                             resolve();
                                                         }, 1000)
                                                     }),
                                                 onRowDelete: oldData =>
                                                     new Promise((resolve, reject) => {
                                                         setTimeout(() => {
                                                             const dataDelete = [...data];
                                                             const index = oldData.tableData.id;
                                                             dataDelete.splice(index, 1);
                                                             setData([...dataDelete]);

                                                             resolve()
                                                         }, 1000)
                                                     }),
                                             }}
                            components={{
                                Body: props => (
                                <div style={{ backgroundColor: '#e8eaf5', width:'100%', display: 'contents' }}>
                                <MTableBody {...props} />
                                <div className='detailTotal'>
                                    <table style={{width:'100%'}}>
                                    <tr>
                                        <td>Delivery</td>
                                        <td>{rowData.delivery}₪</td>
                                    </tr>
                                    <tr>
                                        <td>Total</td>
                                        <td>{rowData.total}₪</td>
                                    </tr>
                                    </table>
                                </div>
                                </div>
                                    )}}

                            />
                            </div>
            )
                    }
                    }
        />
    )
    //     <MaterialTable style={{marginTop:'50px'}}
    //         title="- Orders -"
    //         columns={[
    //             { title: 'Order Number', field: 'orderNumber' },
    //             { title: 'Name', field: 'name' },
    //             { title: 'Email', field: 'emailAddress' },
    //             { title: 'Address', field: 'address' },
    //             { title: 'Phone', field: 'phone' },
    //             { title: 'Passport', field: 'passport' },
    //             { title: 'Comment', field: 'comment' },
    //         ]}
    //         data={orders.map((order)=>({orderNumber: order.orderNumber, name:
    //             order.name,emailAddress:order.emailAddress,address:order.address, phone:order.phone,
    //             passport:order.passport,comment:order.comment, items:order.items, total:order.total, delivery:order.delivery
    //            }))}
    //
    //
    //         detailPanel={rowData => {
    //
    //             return (
    //                 <div>
    //                 <MaterialTable   style={{margin:'10px 100px 10px 100px'}} options={{
    //                     search: false,
    //                     paging:false
    //                 }}
    //                     title="- Order details -"
    //                 columns={[
    //                     { title: 'ID', field: 'id' },
    //             { title: 'Title', field: 'title' },
    //             { title: 'Price', field: 'price' },
    //             { title: 'Quantity', field: 'quantity' },
    //             { title: 'Subtotal', field: 'subtotal' },
    //
    //         ]}
    //             data={rowData.items.map((item)=>({id: item.id, title:
    //                     item.title, price:item.price,quantity:item.quantity, subtotal:item.price*item.quantity}))}
    //
    //                 components={{
    //                     Body: props => (
    //                     <div style={{ backgroundColor: '#e8eaf5', width:'100%', display: 'contents' }}>
    //                     <MTableBody {...props} />
    //                         <span className='detailTotal'> Delivery - {rowData.delivery}</span><br/>
    //                         <span className='detailTotal'> Total - {rowData.total}</span>
    //                     </div>
    //                         )}}
    //
    //                 />
    //                 </div>
    // )
    //         }
    //         }
    //     />
    // )
}
