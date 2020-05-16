import React, {useState} from 'react';
import './App.css';
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import OrdersNav from "./components/OrdersNav";
import Orders from "./components/Orders";
import OrdersStatistics from "./components/OrdersStatistics";
import OrdersHttpService from "./services/OrdersHttpService";
import AuthJwtService from "./services/AuthJwtService";
import Login from "./components/Login";
import Logout from "./components/Logout";
const App = () => {
 const ordersService =
     new OrdersHttpService('http://localhost:3500/orders/');
 const authService =
     new AuthJwtService('http://localhost:3500/');
 authService.register([
     {email:'user@tel-ran.co.il', password:'user'},
     {email:'admin@tel-ran.co.il', password:'admin'}
 ])
    const [username, setUsername] = useState(authService.getUsername());
    const usernameUpdateFn = (username) => {
    setUsername(username);
    }


 return <BrowserRouter>
    <OrdersNav username={username}></OrdersNav>
    <Switch>
      <Route path={'/orders'} exact render={() => {
     return username ? <Orders ordersService={ordersService} /> :
         <Redirect to={'/login'}/>
     }}/>
     <Route path={'/statistics'} exact render={
      () => {
       return username ? <OrdersStatistics ordersService={ordersService}/> :
           <Redirect to={'/login'}></Redirect>
     }
     }>

    </Route>
        <Route path={'/login'} exact render={
            () => {
                return !username ? <Login authService={authService}
                              usernameUpdateFn={usernameUpdateFn}/> :
                    <Redirect to={'/orders'}></Redirect>
            }
        }>

        </Route>
        <Route path={'/logout'} exact render={
            () => {
                return username ? <Logout authService={authService}
                                          usernameUpdateFn={usernameUpdateFn}/> :
                    <Redirect to={'/login'}></Redirect>
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
