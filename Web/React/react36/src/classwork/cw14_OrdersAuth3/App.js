import React, {useState} from 'react';
import './App.css';
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import OrdersNav from "./classwork/cw14_OrdersAuth3/components/OrdersNav";
import Orders from "./classwork/cw14_OrdersAuth3/components/Orders";
import OrdersStatistics from "./classwork/cw14_OrdersAuth3/components/OrdersStatistics";
import OrdersHttpService from "./classwork/cw14_OrdersAuth3/services/OrdersHttpService";
import AuthJwtService from "./classwork/cw14_OrdersAuth3/services/AuthJwtService";
import Login from "./classwork/cw14_OrdersAuth3/components/Login";
import Logout from "./classwork/cw14_OrdersAuth3/components/Logout";

//json-server-auth orders.json -p 3500 --id email -r routes.json



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

export default App;
