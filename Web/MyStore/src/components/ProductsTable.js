
import React, {useState} from "react";
import {useSelector} from "react-redux";
import columnsMediaObject from "../config/columnsMediaConfig";
import useColumnsMedia from "../utils/mediaHook";
import DetailsTable from "./DetailsTable";
import columnsContent from "../config/tableConfig";
import MaterialTable, {MTableBody, MTableBodyRow, MTableCell, MTableHeader, MTablePagination} from "material-table";
import {render} from "react-dom";
import _ from "lodash";
import {throwError} from "rxjs";


export default function ProductsTable(props) {
    // const userData = useSelector(state=>state.userData);
    const products = useSelector(state=>state.items);
    // let [rowData,setRowData] = useState({});
    // const columns = useColumnsMedia(columnsMediaObject);
    // const [order,setOrder] = useState({});
    // const showDetails=(order)=>
    //     setOrder({...order});
    // const backFn = () => setOrder({});
    const columnValues = Object.values(columnsMediaObject);
    const maxColumns = Math.max(...columnValues);
    function removeProduct(productNumber) {
            props.itemsService.deleteItem(productNumber)
                .then(() => {
                })
    }
    function addProduct(item) {
        const index = _.findIndex(products,p => p.id === item.id);
        if (index >= 0) {
            return false;
        }
        if(window.confirm('you are going to add item with number = ' + item.id)) {
            props.itemsService.addItem(item)
                .then(() => {
                        alert(`item with number ${item.id} added successfully`)
                    }
                    , error => {
                        alert(`item with number ${item.id} already exists`)
                    })
        }
            return true;
    }
    function updateProduct(item) {
            props.itemsService.addItem(item)
                .then(() => {
                    })

        return true;
    }
    const { useState } = React;

    const [columns, setColumns] = useState([

        { title: 'ID', field: 'id', width: 10},
        { title: '', field: 'img', width: 80},
        { title: 'Title', field: 'title',width: 120 },
        { title: 'Price', field: 'price', width: 10,},
        { title: 'Description', field: 'description',
                width: 400},
            ]);

    const [data, setData] = useState(products.map((item)=>({id: item.id, title:
        item.title, price:item.price, description:item.description,img:
            // <img src={
                item.img
            // } alt="" border="3" height="100" width="100" />
    })))


    return  (
        <MaterialTable style={{marginTop:'50px'}}
            title="- Products - "
                       options={{
                           headerStyle:{fontWeight:"bold", fontSize:16},
                       }}
            columns={columns}
            data = {data}

            editable={{
                onRowAdd: newData =>
                    new Promise((resolve, reject) => {
                        setTimeout(() => {
                            const newItems = [...data];
                            if(addProduct(newData)){
                                newItems.push(newData)
                                setData([...newItems]);
                            }else{
                                alert(`item with number ${newData.id} already exists`)
                            }

                            resolve();
                        }, 1000)
                    }),
                onRowUpdate: (newData,oldData) =>
                    new Promise((resolve, reject) => {
                        setTimeout(() => {
                            if(window.confirm(`You are going to update item with number ${oldData.id}`)) {
                                removeProduct(oldData.id);
                                updateProduct(newData);
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
                            removeProduct(oldData.id);
                            setData([...dataDelete]);
                            resolve()
                        }, 1000)
                    }),
            }}
                            />


            )
}

