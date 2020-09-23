
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import Box from '@material-ui/core/Box';
import Collapse from '@material-ui/core/Collapse';
import IconButton from '@material-ui/core/IconButton';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';


import React, {useState} from "react";
import {useSelector} from "react-redux";
import columnsMediaObject from "../config/columnsMediaConfig";
import useColumnsMedia from "../utils/mediaHook";
import DetailsTable from "./DetailsTable";
import columnsContent from "../config/tableConfig";
import MaterialTable from "material-table";


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


    ////////////////////////////////////////////////////////////

    // const useRowStyles = makeStyles({
    //     root: {
    //         '& > *': {
    //             borderBottom: 'unset',
    //         },
    //     },
    // });
    //
    // function Row(props) {
    //
    //     const { order } = props;
    //     const [open, setOpen] = React.useState(false);
    //     const classes = useRowStyles();
    //
    //     function ccyFormat(num) {
    //         return `${num.toFixed(2)}`;
    //     }
    //     function subtotal(items) {
    //         return items.map(({ price, quantity }) => price * quantity).reduce((sum, i) => sum + i, 0);
    //     }
    //     const invoiceSubtotal = subtotal(order.items);
    //     const invoiceDelivery = 5;
    //         // {order.items.delivery}?order.items.delivery:0;
    //     const invoiceTotal = invoiceDelivery + invoiceSubtotal;
    //     return (
    //         <React.Fragment>
    //             <TableRow className={classes.root}>
    //                 <TableCell>
    //                     <IconButton aria-label="expand row" size="small" onClick={() => setOpen(!open)}>
    //                         {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
    //                     </IconButton>
    //                 </TableCell>
    //
    //                 <TableCell component="th" scope="row">
    //                     {order.orderNumber}
    //                 </TableCell>
    //                 <TableCell align="right">{order.name}</TableCell>
    //                 <TableCell align="right">{order.emailAddress}</TableCell>
    //                 <TableCell align="right">{order.address}</TableCell>
    //                 <TableCell align="right">{order.phone}</TableCell>
    //                 <TableCell align="right">{order.passport}</TableCell>
    //                 <TableCell align="right">{order.comment}</TableCell>
    //             </TableRow>
    //             <TableRow>
    //                 <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
    //                     <Collapse in={open} timeout="auto" unmountOnExit>
    //                         <Box margin={1}>
    //                             <Typography variant="h6" gutterBottom component="div">
    //                                 - Order details -
    //                             </Typography>
    //                             <Table size="small" aria-label="purchases">
    //                                 <TableHead>
    //                                     <TableRow>
    //                                         <TableCell>ID</TableCell>
    //                                         <TableCell>Title</TableCell>
    //                                         <TableCell>Price</TableCell>
    //                                         <TableCell align="right">Quantity</TableCell>
    //                                         <TableCell align="right">Total</TableCell>
    //                                     </TableRow>
    //                                 </TableHead>
    //                                 <TableBody>
    //                                     {order.items.map((item) => (
    //                                         <TableRow key={item.id}>
    //                                             <TableCell component="th" scope="row">
    //                                                 {item.id}
    //                                             </TableCell>
    //                                             <TableCell>{item.title}</TableCell>
    //                                             <TableCell>{item.price}</TableCell>
    //                                             <TableCell align="right">{item.quantity}</TableCell>
    //                                             <TableCell align="right">
    //                                                 {item.price * item.quantity}
    //                                             </TableCell>
    //                                         </TableRow>
    //                                     ))}
    //                                     <TableRow>
    //                                         <TableCell rowSpan={3}/>
    //                                         <TableCell style={{color:"red"}} colSpan={4}>Subtotal</TableCell>
    //                                         <TableCell style={{color:"red"}} align="right">{ccyFormat(invoiceSubtotal)}</TableCell>
    //                                     </TableRow>
    //                                     <TableRow>
    //                                         <TableCell style={{color:"red"}} colSpan={4}>Delivery</TableCell>
    //                                         <TableCell style={{color:"red"}} align="right">{ccyFormat(invoiceDelivery)}</TableCell>
    //                                     </TableRow>
    //                                     <TableRow>
    //                                         <TableCell style={{color:"red"}} colSpan={4}>Total</TableCell>
    //                                         <TableCell style={{color:"red"}} align="right">{ccyFormat(invoiceTotal)}</TableCell>
    //                                     </TableRow>
    //                                 </TableBody>
    //                             </Table>
    //                         </Box>
    //                     </Collapse>
    //                 </TableCell>
    //             </TableRow>
    //         </React.Fragment>
    //     );
    // }
    //
    // return (
    //     <TableContainer component={Paper}>
    //                     <Table style={{marginTop:"30px"}} aria-label="collapsible table"
    //                            options={{
    //                                  search: true}}>
    //             <TableHead>
    //                 <TableRow>
    //                     <TableCell />
    //                     <TableCell style={{fontSize: "xx-large"}}>- Orders -</TableCell>
    //                     <TableCell align="right">Customer name</TableCell>
    //                     <TableCell align="right">Email</TableCell>
    //                     <TableCell align="right">Address</TableCell>
    //                     <TableCell align="right">Phone</TableCell>
    //                     <TableCell align="right">Passport</TableCell>
    //                     <TableCell align="right">Comment</TableCell>
    //                 </TableRow>
    //             </TableHead>
    //             <TableBody>
    //                 {orders.map((order) => (
    //                     <Row key={order.name} order={order} />
    //                 ))}
    //             </TableBody>
    //         </Table>
    //     </TableContainer>
    // );

    ////////////////////////////////////////

    return (
        <MaterialTable
            title="Multiple Detail Panels Preview"
            columns={[
                { title: 'Name', field: 'name' },
                { title: 'Surname', field: 'surname' },
                { title: 'Birth Year', field: 'birthYear', type: 'numeric' },
                {
                    title: 'Birth Place',
                    field: 'birthCity',
                    lookup: { 34: 'İstanbul', 63: 'Şanlıurfa' },
                },
            ]}
            data={[
                { name: 'Mehmet', surname: 'Baran', birthYear: 1987, birthCity: 63 },
                { name: 'Zerya Betül', surname: 'Baran', birthYear: 2017, birthCity: 34 },
            ]}
            detailPanel={[
                {
                    tooltip: 'Show Name',
                    render: rowData => {
                        return (
                            <div
                                style={{
                                    fontSize: 100,
                                    textAlign: 'center',
                                    color: 'white',
                                    backgroundColor: '#43A047',
                                }}
                            >
                                {rowData.name}
                            </div>
                        )
                    },
                },
                {
                    icon: 'account_circle',
                    tooltip: 'Show Surname',
                    render: rowData => {
                        return (
                            <div
                                style={{
                                    fontSize: 100,
                                    textAlign: 'center',
                                    color: 'white',
                                    backgroundColor: '#E53935',
                                }}
                            >
                                {rowData.surname}
                            </div>
                        )
                    },
                },
                {
                    icon: 'favorite_border',
                    openIcon: 'favorite',
                    tooltip: 'Show Both',
                    render: rowData => {
                        return (
                            <div
                                style={{
                                    fontSize: 100,
                                    textAlign: 'center',
                                    color: 'white',
                                    backgroundColor: '#FDD835',
                                }}
                            >
                                {rowData.name} {rowData.surname}
                            </div>
                        )
                    },
                },
            ]}
        />
    )


}