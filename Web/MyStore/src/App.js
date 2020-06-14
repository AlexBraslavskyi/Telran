  
import React, { Component } from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom'
import Navbar from './components/Navbar'
import Cart from './components/Cart'
import './style/Style.css'
import { Home } from './components/Home';
import Shop from './components/Shop'
import Login from './components/Login';
import Logout from './components/Logout';

const App =()=> {

    return (
       <BrowserRouter>
              <Navbar/>
                <Switch>
                    <Route exact path="/" render ={() =><Home/>}/>
                    <Route exact path="/cart" render ={() =><Cart/>}/>
                    <Route exact path="/shop" render ={() =><Shop/>}/>
                    <Route exact path="/login" render ={() =><Login/>}/>
                    <Route exact path="/logout" render ={() =><Logout/>}/>



                  </Switch>
       </BrowserRouter>
    );
  }
export default App;