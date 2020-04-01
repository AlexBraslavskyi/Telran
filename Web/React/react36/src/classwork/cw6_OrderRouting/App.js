import React from 'react';
import './App.css';
import {BrowserRouter,Switch,Route} from "react-router-dom";
import OrdersNav from "./classwork/cw6_OrderRouting/OrdersNav";
import Orders from "./classwork/cw6_OrderRouting/Orders";
import OrdersStatistics from "./classwork/cw6_OrderRouting/ordersStatistics"


class App extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      orders:[]
    }
    }
  ordersUpdate(orders){
this.setState({orders})
  }

render() {
  return <BrowserRouter>
    <OrdersNav></OrdersNav>
    <Switch>
      <Route path={'/orders'} exact render = {()=>{
        return <Orders orders = {this.state.orders} updateOrdersFn={this.ordersUpdate.bind(this)}/>}}/>
      <Route path={'/statistics'} exact render = {()=>{
        return <OrdersStatistics orders={this.state.orders}/>}}/>
      {/*not using in employee */}
    </Switch>
  </BrowserRouter>
}
}

export default App;