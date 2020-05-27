import React, {useState} from 'react';
import './App.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Orders from "./classwork/cw8_OrdersHooks/Components/Orders";
import OrdersStatistics from "./classwork/cw8_OrdersHooks/Components/ordersStatistics";
import OrdersNav from "./classwork/cw8_OrdersHooks/Components/OrdersNav";

const App=(props)=>{
 let orders;
 let setOrders;
 [orders,setOrders]=useState([]);

 const ordersUpdate = (orders) => {
  setOrders(orders)
 }
 return <BrowserRouter>
  <OrdersNav></OrdersNav>
  <Switch>
   <Route path={'/orders'} exact render={() => {
    return <Orders orders={orders} updateOrdersFn={ordersUpdate}/>}}/>
   <Route path={'/statistics'} exact render={
    () => {
     return <OrdersStatistics orders={orders}/>}}>
   </Route>
  </Switch>
 </BrowserRouter>
}

// class App extends React.Component {
//  constructor(props) {
//   super(props);
//   this.state = {
//    orders: []
//   }
//  }
//  ordersUpdate(orders) {
//   this.setState({
//    orders
//   })
//  }
//  render() {
//   return <BrowserRouter>
//    <OrdersNav></OrdersNav>
//    <Switch>
//     <Route path={'/orders'} exact render={() => {
//      return <Orders orders={this.state.orders} updateOrdersFn={this.ordersUpdate.bind(this)}/>
//     }}/>
//     <Route path={'/statistics'} exact render={
//      () => {
//       return <OrdersStatistics orders={this.state.orders}/>
//      }
//     }>
//
//     </Route>
//    </Switch>
//   </BrowserRouter>
//  }
// }

export default App;
