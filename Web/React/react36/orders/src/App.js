import React, {useEffect, useState} from 'react';
import './App.css';
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import OrdersNav from "./components/OrdersNav";
import Orders from "./components/Orders";
import OrdersStatistics from "./components/OrdersStatistics";
import Login from "./components/Login";
import Logout from "./components/Logout";
import OrdersFirebaseService from "./services/OrdersFirebaseService";
import {collection} from "rxfire/firestore";
import AuthFirebaseService from "./services/AuthFirebaseService";
import {useDispatch, useSelector} from "react-redux";
import {actionOrders, actionUserData} from "./store/actions";

//json-server-auth orders.json -p 3500 --id email -r routes.json

// Project Console: https://console.firebase.google.com/project/orders-83d5a/overview
//     Hosting URL: https://orders-83d5a.web.app



const App = () => {
    const ordersService =
    new OrdersFirebaseService('orders');
    const authService = new AuthFirebaseService();
    const dispatch = useDispatch();//hook for possibility to updating global store
    const userData = useSelector(state=>
        state.userData);//hook for getting global store
    useEffect(()=>{
        authService.getUserData().subscribe(userData=>{
           dispatch(actionUserData(userData))//update state
            if(userData.username){
                ordersService.getOrders().subscribe(orders=>{
                    dispatch(actionOrders(orders));
                },error => alert(JSON.stringify(error)))
            }
        })

    },[]);


 return <BrowserRouter>
    <OrdersNav userData={userData}/>
    <Switch>
      <Route path={'/orders'} exact render={() => {
     return userData.username ? <Orders  ordersService={ordersService} /> :
         <Redirect to={'/login'}/>
     }}/>
     <Route path={'/statistics'} exact render={
      () => {
       return userData.isAdmin ? <OrdersStatistics/> :
           <Redirect to={'/login'}></Redirect>
     }
     }>

    </Route>
        <Route path={'/login'} exact render={
            () => {
                return !userData.username ? <Login authService={authService}/> :
                    <Redirect to={'/orders'}></Redirect>
            }
        }>

        </Route>
        <Route path={'/logout'} exact render={
            () => {
                return userData.username ? <Logout authService={authService}/> :
                    <Redirect to={'/login'}></Redirect>
            }
        }>

        </Route>
    </Switch>
   </BrowserRouter>
  }

export default App;
