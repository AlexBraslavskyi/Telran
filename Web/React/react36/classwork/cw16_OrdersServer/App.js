import React, {useEffect, useState} from 'react';
import './classwork/cw16_OrdersServer/App.css';
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import OrdersNav from "./classwork/cw16_OrdersServer/components/OrdersNav";
import Orders from "./classwork/cw16_OrdersServer/components/Orders";
import OrdersStatistics from "./classwork/cw16_OrdersServer/components/OrdersStatistics";
import OrdersHttpService from "./classwork/cw16_OrdersServer/services/OrdersHttpService";
import AuthJwtService from "./classwork/cw16_OrdersServer/services/AuthJwtService";
import Login from "./classwork/cw16_OrdersServer/components/Login";
import Logout from "./classwork/cw16_OrdersServer/components/Logout";
import OrdersFirebaseService from "./classwork/cw16_OrdersServer/services/OrdersFirebaseService";
import {collection} from "rxfire/firestore";

//json-server-auth orders.json -p 3500 --id email -r routes.json



const App = () => {
    const ordersService =
        // new OrdersHttpService('http://localhost:3500/orders/', () => {
        //     setUserData({})
        // }, () => {
        //     alert("Server is unavailable, please retry again later on")
        // });
    new OrdersFirebaseService('orders');
    const authService =
        new AuthJwtService('http://localhost:3500/');

    const [userData, setUserData] = useState({});
    const userDataUpdateFn = (userData) => {
    setUserData(userData);
    }
    useEffect(()=>{
        authService.register([
            {email:'user@tel-ran.co.il', password:'user'},
            {email:'admin@tel-ran.co.il', password:'admin'}
        ])
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
