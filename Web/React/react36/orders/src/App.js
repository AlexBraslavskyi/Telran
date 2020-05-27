import React, {useEffect, useState} from 'react';
import './App.css';
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import OrdersNav from "./components/OrdersNav";
import Orders from "./components/Orders";
import OrdersStatistics from "./components/OrdersStatistics";
import OrdersHttpService from "./services/OrdersHttpService";
import AuthJwtService from "./services/AuthJwtService";
import Login from "./components/Login";
import Logout from "./components/Logout";
import OrdersFirebaseService from "./services/OrdersFirebaseService";
import {collection} from "rxfire/firestore";
import AuthFirebaseService from "./services/AuthFirebaseService";

//json-server-auth orders.json -p 3500 --id email -r routes.json

//Project Console: https://console.firebase.google.com/project/orders-83d5a/overview
 //   Hosting URL: https://orders-83d5a.web.app


const App = () => {
    const ordersService =
        // new OrdersHttpService('http://localhost:3500/orders/', () => {
        //     setUserData({})
        // }, () => {
        //     alert("Server is unavailable, please retry again later on")
        // });
    new OrdersFirebaseService('orders');
    const authService =
        new AuthFirebaseService();
        // new AuthJwtService('http://localhost:3500/');

    const [userData, setUserData] = useState({});
    const userDataUpdateFn = (userData) => {
    setUserData(userData);
    }
    useEffect(()=>{
        // authService.register([
        //     {email:'user@tel-ran.co.il', password:'user'},
        //     {email:'admin@tel-ran.co.il', password:'admin'}
        // ])
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
