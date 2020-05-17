import React, {useEffect, useState} from 'react';
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
     new OrdersHttpService('http://localhost:3500/orders/',()=>{setUserData({})});
 const authService =
     new AuthJwtService('http://localhost:3500/');
 authService.register([
     {email:'user@tel-ran.co.il', password:'user'},
     {email:'admin@tel-ran.co.il', password:'admin'}
 ])
    const [userData, setUserData] = useState({});
    const userDataUpdateFn = (userData) => {
    setUserData(userData);
    }
    useEffect(()=>{
        authService.getUserData().subscribe(userData =>setUserData(userData));
    },[])


 return <BrowserRouter>
    <OrdersNav userData={userData}/>
    <Switch>
      <Route path={'/orders'} exact render={() => {
     return userData.username ? <Orders isAdmin={userData.isAdmin} ordersService={ordersService} /> :
         <Redirect to={'/login'}/>
     }}/>
     <Route path={'/statistics'} exact render={
      () => {
       return userData.isAdmin ? <OrdersStatistics ordersService={ordersService}/> :
           <Redirect to={'/login'}></Redirect>
     }
     }>

    </Route>
        <Route path={'/login'} exact render={
            () => {
                return !userData.username ? <Login authService={authService}
                              userDataUpdateFn={userDataUpdateFn}/> :
                    <Redirect to={'/orders'}></Redirect>
            }
        }>

        </Route>
        <Route path={'/logout'} exact render={
            () => {
                return userData.username ? <Logout authService={authService}
                                          userDataUpdateFn={userDataUpdateFn}/> :
                    <Redirect to={'/login'}></Redirect>
            }
        }>

        </Route>
    </Switch>
   </BrowserRouter>
  }

export default App;
