import React, {useState} from 'react';
import './App.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import OrdersNav from "./components/OrdersNav";
import Orders from "./components/Orders";
import OrdersStatistics from "./components/OrdersStatistics";
import OrdersHttpService from "./services/OrdersHttpService";
import AuthJwtService from "./services/AuthJwtService";
const App = () => {
 const ordersService =
     new OrdersHttpService('http://localhost:3500/orders/');
 const authService =
     new AuthJwtService('http://localhost:3500/');
 authService.register([
     {email:'user@tel-ran.co.il', password:'user'},
     {email:'admin@tel-ran.co.il', password:'admin'}
 ])
    authService.login({email:'user@tel-ran.co.il', password:'user'})
        .subscribe(jwt => {
            localStorage.setItem("accessToken", jwt);
            console.log(authService.getUsername());
        })

 return <BrowserRouter>
    <OrdersNav></OrdersNav>
    <Switch>
      <Route path={'/orders'} exact render={() => {
     return <Orders ordersService={ordersService} />
     }}/>
     <Route path={'/statistics'} exact render={
      () => {
       return <OrdersStatistics ordersService={ordersService}/>
     }
     }>

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
//     <Route path={'/employees'} exact render={() => {
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
