import React, {useEffect, useState} from 'react';
import _ from 'lodash';
import $ from "jquery";
import M from 'materialize-css/dist/js/materialize';
import {useSelector} from "react-redux";
import {getRandomColor} from "../utils/random";
import {TITLES} from "../config/ShopConfig";
import {getSelectElement} from "../utils/inputElements";

export default function Statistics() {

    const orders = useSelector(state => state.orders);
    let itemsStat, setItemsStat, customersStat, setCustomerStat, switchView, setSwitchView, viewTableStat,
        setViewTableStat;
    [itemsStat, setItemsStat] = useState([]);
    [customersStat, setCustomerStat] = useState([]);
    [switchView, setSwitchView] = useState(0);
    [viewTableStat, setViewTableStat] = useState(0);
    let statisticsObjCustomers = {};
    let tableRecords = {};
    let objStatItems = {};

    function inputHandler(event) {
        event.preventDefault();
        switch (event.target.value) {
            case 'Customers statistics':
                setSwitchView(1);
                setViewTableStat(0);
                break;
            case 'Items statistics':
                setSwitchView(2);
                setViewTableStat(0);
                break;
            default:
                setSwitchView(0);
        }
    }


    function submit(event) {

        event.preventDefault();
        calcStatistics();
        if (switchView > 0) {
            setViewTableStat(1)
            setItemsStat(objStatItems);
            setCustomerStat(statisticsObjCustomers);
        }

    }

    function calcStatistics() {
        let itemsStatTemp = [];
        statisticsObjCustomers = _.countBy(orders, 'name');
        let itemsArr = orders.map(({items}) => items);
        let flat = itemsArr.reduce((acc, it) => [...acc, ...it], []);
        flat.forEach((currentObj) => {
            const alreadyExists = itemsStatTemp.findIndex(item => currentObj.title === item.title) > -1;
            if (!alreadyExists) {
                const filtered = flat.filter(item => item.title === currentObj.title);
                const newObject = filtered.reduce((acc, curr) => {
                    return {...acc, quantity: acc.quantity += curr.quantity}
                }, {...currentObj, quantity: 0})
                itemsStatTemp.push(newObject);
            }
        })
        let statisticsItems = itemsStatTemp.map((k) => {
            return {title: k.title, quantity: k.quantity}
        });
        objStatItems = Object.fromEntries(statisticsItems.map(item => [item.title, item.quantity]));
        itemsStatTemp = [];
    }


    function viewTable() {
        if (switchView != 0) {

            let data = switchView == 1 ? customersStat : itemsStat;
            let total = Object.keys(data).reduce((sum, key) => sum + parseFloat(data[key] || 0), 0);
            const sorted = Object.fromEntries(
                Object.entries(data).sort(([, a], [, b]) => b - a)
            )
            tableRecords = Object.entries(sorted).map(
                (e) => {
                    const width = (e[1] * 100) / total + "%";
                    const color = getRandomColor();
                    return <tr key={e[0]}>
                        <td style={{width: '30%'}}>{e[0]}</td>
                        <td>
                            <div className="bar-container">
                                <div style={{width: width, height: '18px', backgroundColor: color}}
                                     className="bar"></div>
                            </div>
                        </td>
                        <td style={{width: '30%'}}>{e[1]}</td>
                    </tr>
                }
            )
        }

        return <div>
            <div className="center">
                <header className="card-header">
                    {switchView == 1 ? <h4>Statistics of the Customers</h4> :
                        switchView == 2 ? <h4>Statistics of the Items</h4> : null}
                </header>
                <div className="card-body">
                    <table className="table table-sm" style={{'textAlign': 'center'}}>
                        <thead>
                        <tr>
                            {switchView == 1 ? <th>Customers</th>
                                : switchView == 2 ? <th>Items</th> : null}
                            <th></th>
                            {switchView == 1 ? <th>Orders quantity</th>
                                : switchView == 2 ? <th>Quantity</th> : null}
                        </tr>
                        </thead>
                        <tbody>
                        {tableRecords.length ? tableRecords : null}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    }

    return <div className="content">

        <div className="row" style={{width: "60%"}}>
            <form className="col s12" onSubmit={submit}>
                <div className="center">
                    <h3 style={{color: "#ee6e73", marginTop: "5vh"}}> - Statistics -</h3>
                </div>
                <label>Statistic types</label>
                <select className="browser-default" onChange={inputHandler}>
                    <option value="" disabled selected>Choose statistic type</option>
                    <option value="Customers statistics">Customers statistics</option>
                    <option value="Items statistics">Items statistics</option>
                </select>

                <button type="submit" name="action" className="btn waves-effect waves-light"
                ><i className="material-icons right">send</i>Submit
                </button>
            </form>
            <div>
                {viewTableStat == 1 ? viewTable() : null}
            </div>
        </div>
    </div>
}