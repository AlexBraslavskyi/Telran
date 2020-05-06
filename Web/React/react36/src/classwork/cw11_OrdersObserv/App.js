import React, {useState} from 'react';
import './App.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Orders from "./classwork/cw10_OrdersObs/Components/Orders";
import OrdersStatistics from "./classwork/cw10_OrdersObs/Components/ordersStatistics";
import OrdersNav from "./classwork/cw10_OrdersObs/Components/OrdersNav";
import OrdersHttpService from "./classwork/cw10_OrdersObs/Service/EmployeesHttpService";

// json-server-auth -p 3500 -w --id email orders.json


const App=()=>{
 const ordersService = new OrdersHttpService('http://localhost:3500/orders/')
 // let orders;
 // let setOrders;
 // [orders,setOrders]=useState([]);

 // const ordersUpdate = (orders) => {
 //  setOrders(orders)
 // }
 return <BrowserRouter>
  <OrdersNav></OrdersNav>
  <Switch>
   <Route path={'/orders'} exact render={() => {
    return <Orders ordersService={ordersService}/>}}/>
   <Route path={'/statistics'} exact render={
    () => {
     return <OrdersStatistics ordersService={ordersService}/>}}>
   </Route>
  </Switch>
 </BrowserRouter>
}


export default App;
