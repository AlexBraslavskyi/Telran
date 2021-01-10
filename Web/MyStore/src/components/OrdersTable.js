
import React, {useState} from "react";
import {useSelector} from "react-redux";
import columnsMediaObject from "../config/columnsMediaConfig";
import useColumnsMedia from "../utils/mediaHook";
import DetailsTable from "./DetailsTable";
import columnsContent from "../config/tableConfig";
import MaterialTable, {MTableBody, MTableBodyRow, MTableCell, MTableHeader, MTablePagination} from "material-table";
import {render} from "react-dom";
import _ from "lodash";
import { forwardRef } from 'react';

import AddBox from '@material-ui/icons/AddBox';
import ArrowDownward from '@material-ui/icons/ArrowDownward';
import Check from '@material-ui/icons/Check';
import ChevronLeft from '@material-ui/icons/ChevronLeft';
import ChevronRight from '@material-ui/icons/ChevronRight';
import Clear from '@material-ui/icons/Clear';
import DeleteOutline from '@material-ui/icons/DeleteOutline';
import Edit from '@material-ui/icons/Edit';
import FilterList from '@material-ui/icons/FilterList';
import FirstPage from '@material-ui/icons/FirstPage';
import LastPage from '@material-ui/icons/LastPage';
import Remove from '@material-ui/icons/Remove';
import SaveAlt from '@material-ui/icons/SaveAlt';
import Search from '@material-ui/icons/Search';
import ViewColumn from '@material-ui/icons/ViewColumn';

export default function OrdersTable(props) {
    // const userData = useSelector(state=>state.userData);
    const orders = props.orders;
    // let [rowData,setRowData] = useState({});
    // const columns = useColumnsMedia(columnsMediaObject);
    // const [order,setOrder] = useState({});
    // const showDetails=(order)=>
    //     setOrder({...order});
    // const backFn = () => setOrder({});
    const columnValues = Object.values(columnsMediaObject);
    const maxColumns = Math.max(...columnValues);
    const tableIcons = {
        Add: forwardRef((props, ref) => <AddBox {...props} ref={ref} />),
        Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
        Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
        Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref} />),
        DetailPanel: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
        Edit: forwardRef((props, ref) => <Edit {...props} ref={ref} />),
        Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
        Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
        FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
        LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
        NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
        PreviousPage: forwardRef((props, ref) => <ChevronLeft {...props} ref={ref} />),
        ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
        Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
        SortArrow: forwardRef((props, ref) => <ArrowDownward {...props} ref={ref} />),
        ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref} />),
        ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />)
    };
    function removeOrder(orderNumber) {
            props.removeFn(orderNumber);
    }
    function removeItemFromOrder(orderNumber, item) {
            props.removeItemFn(orderNumber,item);
    }
    function addItemToOrder(currentOrder, item) {
       return props.addItemToOrderFn(currentOrder,item);
    }
    function updateItem(currentOrder, item) {
        return props.updateItemFn(currentOrder,item);
    }
    function addOrder(order) {
        props.updateOrderFn(order);
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
                { title: 'Date', field: 'orderData' },
                { title: 'Email', field: 'emailAddress' },
                { title: 'Address', field: 'address' },
                { title: 'Phone', field: 'phone' },
                { title: 'Passport', field: 'passport' },
                { title: 'Comment', field: 'comment' },
                { title: 'Payment method', field: 'paymentMethod'}
            ]);

    const [data, setData] = useState(orders.map((order)=>({orderNumber: order.orderNumber, orderData:order.orderData,
        name: order.name,emailAddress:order.emailAddress,address:order.address, phone:order.phone,
        passport:order.passport,comment:order.comment, items:order.items, total:order.total,
        delivery:order.delivery, paymentMethod: order.paymentMethod
                       })));

    return  (
        <MaterialTable style={{marginTop:'50px'}}
                       icons={tableIcons}
            title="- Orders - "
                       options={{
                           headerStyle:{fontWeight:"bold", fontSize:16}
                       }}
            columns={columns}
            data={data}
                       onRowClick={(event, rowData, togglePanel) => togglePanel()}

            editable={{
                onRowUpdate: (newData,oldData) =>
                    new Promise((resolve, reject) => {
                        setTimeout(() => {
                            if(window.confirm(`You are going to update item with number ${oldData.orderNumber}`)) {
                                removeOrder(oldData.orderNumber);
                                addOrder(newData);
                                const dataDelete = [...data];
                                const index = oldData.tableData.id;
                                dataDelete.splice(index, 1);
                                dataDelete.push(newData);
                                setData([...dataDelete]);
                            }
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
                            removeOrder(oldData.orderNumber);
                            resolve()
                        }, 1000)
                    }),
            }}

                    detailPanel={[
                        rowData => ({
                            ...props,
                            render: (rowData) => {
                                // rowData is now updated, so changes took place
                                return (
                            <div>
                            <MaterialTable   style={{margin:'10px 100px 10px 100px'}}
                                             icons={tableIcons}
                                             options={{
                                search: false,
                                headerStyle:{fontWeight:"bold", fontSize:16},
                                cellStyle:{padding:0},
                                paging:false

                            }}
                                title = "- Orders Details -"
                                             columns={[{ title: 'ID', field: 'id',width: 10 },
                                                 { title: '', field: 'img', width: 120, padding:0,
                                                     render: (row) => (
                                                         <div
                                                             style={{
                                                                 display: 'flex',
                                                                 justifyContent: 'center',
                                                                 alignItems: 'center',
                                                                 height: '100px',
                                                                 width: '120px',
                                                                 padding:0,
                                                             }}
                                                         >
                                                             <img
                                                                 style={{ height: '90px', width: '100px' }}
                                                                 src={(
                                                                         row.img
                                                                     )
                                                                 }
                                                             />
                                                         </div>
                                                     )},
                                                             { title: 'Title', field: 'title', width: 300 },
                                                             { title: 'Price', field: 'price', width: 10,  cellStyle:{padding:40}},
                                                             { title: 'Quantity', field: 'quantity' , width: 10, type:'numeric',  cellStyle:{paddingRight:40}},
                                                             { title: 'Subtotal', field: 'subtotal', width: 10 , cellStyle:{padding:40}},
                                                             { title: 'Description', field: 'description', hidden: true},
                                                         ]}

                                             data={rowData.items.map((item)=>({id: item.id, title:
                                                 item.title, price:item.price,quantity:item.quantity,
                                                 subtotal:item.price*item.quantity, description:item.description,img:
                                                         item.img
                                                 }))}

                                             editable={{

                                                 onRowAdd: newData =>
                                                     new Promise((resolve, reject) => {
                                                         setTimeout(() => {
                                                                 if(!addItemToOrder(rowData, newData)){
                                                                     rowData.items.push(newData)
                                                                 }else{
                                                                     alert(`item with number ${newData.id} already exists`)
                                                             }
                                                             resolve();
                                                         }, 1000)
                                                     }),
                                                 onRowUpdate: (newData, oldData) =>
                                                     new Promise((resolve, reject) => {
                                                         setTimeout(() => {
                                                             if(window.confirm(`You are going to update item with number ${oldData.id}`)) {
                                                                 const index = oldData.tableData.id;
                                                                 rowData.items.splice(index, 1);
                                                                 removeItemFromOrder(rowData.orderNumber, oldData);
                                                                 updateItem(rowData, newData);
                                                                 rowData.items.push(newData);
                                                             }
                                                             resolve();
                                                         }, 1000)
                                                     }),

                                                 onRowDelete: oldData =>
                                                     new Promise((resolve, reject) => {
                                                         setTimeout(() => {
                                                                const index = oldData.tableData.id;
                                                                rowData.items.splice(index, 1);
                                                                removeItemFromOrder(rowData.orderNumber, oldData);

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
                                        <tbody>
                                    <tr>
                                        <td>Delivery</td>
                                        <td>{rowData.delivery}₪</td>
                                    </tr>
                                    <tr>
                                        <td>Total</td>
                                        <td>{parseInt(rowData.delivery) + rowData.items.reduce(function(prev, cur)
                                        {
                                            return prev + (cur.price * cur.quantity)
                                        }, 0)}₪</td>
                                    </tr>
                                        </tbody>
                                    </table>
                                </div>
                                </div>
                                    )}}
                            />
                            </div>
            )}
                    })]
                    }
        />
    )
}