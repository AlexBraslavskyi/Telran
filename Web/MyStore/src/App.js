  
import React, {Component, useEffect} from 'react';
import {BrowserRouter, Route, Switch,Redirect} from 'react-router-dom'
import Navbar from './components/Navbar'
import Cart from './components/Cart'
import './style/Style.css'
import { Home } from './components/Home';
import Shop from './components/Shop'
import Login from './components/Login';
import Logout from './components/Logout';
import OrdersFirebaseService from "./services/OrdersFirebaseService";
import AuthFirebaseService from "./services/AuthFirebaseService";
import MobMenuService from "./services/MobMenuService";
import {useDispatch, useSelector} from "react-redux";
import {actionFlagMobMenu, actionOrders, actionUserData, addToCart} from "./components/actions/actions";
import {
    pathCart,
    pathShop,
    pathLogin,
    pathLogout,
    pathHome,
    pathOrderForm,
    pathOrders,
    pathSearch
} from './config/ShopConfig';
import { OrderForm } from './components/OrderForm';
import Orders from './components/Orders';
import {reduce} from "rxjs/operators";
import AuthJwtService from "./services/AuthJwtService";
import OrdersHttpService from "./services/OrdersHttpService";

const App =()=> {
  const ordersService =
  new OrdersFirebaseService('orders');
 const authService =  new AuthFirebaseService();
 const mobMenuService = new MobMenuService();
 const dispatch = useDispatch();
 const userData = useSelector(state=>state.userData);
 useEffect(()=>{
     dispatch(actionFlagMobMenu({flag:mobMenuService.getFlag()}))
     authService.getUserData().subscribe(userData=> {
         dispatch(actionUserData(userData))
         if(userData.username){
             ordersService.getOrders().subscribe(orders=>{
                 dispatch(actionOrders(orders));
             }
             ,error => alert('Data transfer finished')
         )}
     })
 },[]);
    return (
       <BrowserRouter>
              <Navbar userData={userData}/>
                <Switch>
                    <Route exact path={pathHome} render ={() =><Home/>}/>
                    <Route path={pathHome} exact render={() =>
                    {return !userData.username ? <Home/> :
                        <Redirect to={pathCart}/>}}/>
                    <Route path={pathCart} exact render={() =>
                    {return userData.username ? <Cart/> :
                        <Redirect to={pathLogin}/>}}/>
                    {/*<Route path={pathSearch} exact render={() =>*/}
                    {/*{return userData.isAdmin ? <OrdersSearch/> :*/}
                    {/*    <Redirect to={pathHome}/>}}/>*/}
                    <Route path={pathShop} exact render={() =>
                    {return userData.username ? <Shop/> :
                        <Redirect to={pathLogin}/>}}/>
                    <Route path={pathOrders} exact render={() =>
                    {return userData.username ? <Orders ordersService={ordersService} /> :
                        <Redirect to={pathLogin}/>}}/>
                         //isAdmin
                    <Route path={pathLogin} exact render={() =>
                    {return !userData.username ? <Login authService={authService}/> :
                        <Redirect to={pathHome}/>}}/>

                    <Route path={pathLogout} exact render={() =>
                    {return userData.username ? <Logout authService={authService} /> :
                        <Redirect to={pathHome}/>}}/>

                  </Switch>
                    <footer className="page-footer end-align">
          {/* <div class="footer-copyright"> */}
            <div className="container">
            © 2020 Copyright Alex Braslavskyi
            <a className="grey-text text-lighten-4 right" href="#!">More Links</a>
            </div>
          {/* </div> */}
        </footer>
       </BrowserRouter>
    );
  }
export default App;