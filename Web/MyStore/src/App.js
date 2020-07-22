  
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
import {actionFlagMobMenu, actionOrders, actionUserData} from "./components/actions/actions";
import { pathCart, pathShop, pathLogin, pathLogout, pathHome, pathOrderForm, pathOrders } from './config/ShopConfig';
import { OrderForm } from './components/OrderForm';
import Orders from './components/Orders';

// class App extends Component {
//   render() {
//     return (
//        <BrowserRouter>
//             <div className="App">
            
//               <Navbar/>
//                 <Switch>
//                     <Route exact path="/" component={Home}/>
//                     <Route path="/cart" component={Cart}/>
//                     <Route path="/shop" component={Shop}/>
//                   </Switch>
//              </div>
//        </BrowserRouter>
      
//     );
//   }
// }
const App =()=> {
  const ordersService =
  new OrdersFirebaseService('orders');
 const authService =  new AuthFirebaseService();
 const mobMenuService = new MobMenuService();
 const dispatch = useDispatch();
 const userData = useSelector(state=>
     state.userData);
 useEffect(()=>{

     dispatch(actionFlagMobMenu({flag:mobMenuService.getFlag()}))
     authService.getUserData().subscribe(userData=>{
         dispatch(actionUserData(userData))
         if(userData.username){
             ordersService.getOrders().subscribe(orders=>{
                 dispatch(actionOrders(orders));
             },error => alert('Data transfer finished'))
         }
     })

 },[]);
    return (
       <BrowserRouter>
              <Navbar/>
                <Switch>
                    <Route exact path={pathHome} render ={() =><Home/>}/>
                    <Route exact path={pathCart} render ={() =><Cart/>}/>
                    <Route exact path={pathShop} render ={() =><Shop/>}/>
                    <Route exact path={pathOrderForm} render ={() =><OrderForm/>}/>
                    <Route exact path={pathOrders} render ={() =>
        {return userData.isAdmin ? <Orders ordersService = {ordersService}/> :
        <Redirect to={pathHome}/>}}/>
                    <Route exact path={pathLogin}render ={() =><Login/>}/>
                    <Route exact path={pathLogout} render ={() =><Logout/>}/>

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