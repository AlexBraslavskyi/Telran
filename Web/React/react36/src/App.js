import React, {useState} from 'react';
import './App.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Orders from "./classwork/cw12_Orders/Components/Orders";
import OrdersStatistics from "./classwork/cw12_Orders/Components/ordersStatistics";
import OrdersNav from "./classwork/cw12_Orders/Components/OrdersNav";
import OrdersHttpService from "./classwork/cw12_Orders/Service/OrdersHttpService";
import AuthJwtService from "./classwork/cw12_Orders/Service/AuthJwtService";

// json-server-auth -p 3500 -w --id email orders.json


const App=()=>{
 const ordersService = new OrdersHttpService('http://localhost:3500/orders/');
const authService = new AuthJwtService('http://localhost:3500/');
authService.register([
 {email:'user@telran.co.il',password:'user'},
 {email:'admin@telran.co.il',password:'admin'}
])
 authService.login({email:'user@telran.co.il',password:'user'}).subscribe(jwt=>{
  localStorage.setItem('accessToken',jwt)
  console.log(authService.getUsername())
 })

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
