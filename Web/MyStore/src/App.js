  
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
import {
    actionFlagMobMenu,
    actionItems,
    actionOrders,
    actionProducts,
    actionUserData,
    addToCart
} from "./components/actions/actions";
import {
    pathCart,
    pathShop,
    pathLogin,
    pathLogout,
    pathHome,
    pathOrderForm,
    pathOrders,
    pathSearch, pathProducts
} from './config/ShopConfig';
import { OrderForm } from './components/OrderForm';
import Orders from './components/Orders';
import {reduce} from "rxjs/operators";
import ItemsFirebaseService from "./services/ItemsFirebaseService";
import ProductsTable from "./components/ProductsTable";

const App =()=> {
  const ordersService =
  new OrdersFirebaseService('orders');
  const itemsService = new ItemsFirebaseService('items');
 const authService =  new AuthFirebaseService();
 const mobMenuService = new MobMenuService();
 const dispatch = useDispatch();
 const userData = useSelector(state=>state.userData);
 useEffect(()=>{
     dispatch(actionFlagMobMenu({flag:mobMenuService.getFlag()}))
     authService.getUserData().subscribe(userData=> {
         dispatch(actionUserData(userData))
         if(userData.username) {
             ordersService.getOrders().subscribe(orders => {
                     dispatch(actionOrders(orders));
                 }
                 , error => alert('Data transfer finished')
             );
             itemsService.getItems().subscribe(items => {
                     dispatch(actionItems(items));
                 }
                 , error => alert(JSON.stringify(error))
             )
         }
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
                    {return userData.username ? <Shop
                            itemsService = {itemsService}
                        /> :
                        <Redirect to={pathLogin}/>}}/>
                    <Route path={pathOrders} exact render={() =>
                    {return userData.username ? <Orders ordersService={ordersService}/> :
                        <Redirect to={pathLogin}/>}}/>
                         //isAdmin
                    <Route path={pathProducts} exact render={() =>
                    {return userData.username ? <ProductsTable itemsService={itemsService}/> :
                        <Redirect to={pathLogin}/>}}/>
                    <Route path={pathLogin} exact render={() =>
                    {return !userData.username ? <Login authService={authService}/> :
                        <Redirect to={pathHome}/>}}/>

                    <Route path={pathLogout} exact render={() =>
                    {return userData.username ? <Logout authService={authService} /> :
                        <Redirect to={pathHome}/>}}/>

                  </Switch>
                    <footer  className="page-footer align-content-end">
           <div className="footer-copyright">
            <div className="container">
            © 2020 Copyright Alex Braslavskyi
            <a className="grey-text text-lighten-4 right" href="#!">More Links</a>
            </div>
           </div>
        </footer>
       </BrowserRouter>
    );
  }
export default App;