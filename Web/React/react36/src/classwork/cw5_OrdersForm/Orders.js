import React from "react";
import _ from 'lodash';
import OrdersStatistics from "./ordersStatistics";
import OrderForm from "./OrderForm";

class Orders extends React.Component {

    constructor(props) {
        super(props);
        this.state = {orders: [],
            coffeeStatistics:{}, flAddOrder:false
        }
    }
    showOrderForm(){
        this.setState({flAddOrder:true})
    }
    addOrder(order) {
        const orders = this.state.orders;
        const ind = orders.findIndex(o=>o.email===order.email)
        if(ind>=0){
            return false;
        }
        orders.push(order);
        const coffeeStatistics = _.countBy(orders,'coffee');
        this.setState({orders,coffeeStatistics,flAddOrder:false});
        return true;
    }
    deleteOrder(email){
        const orders = this.state.orders;
        if(window.confirm `You are going to delete order ${email}`){
            _.remove(orders, order => order.email === email)
            const coffeeStatistics = _.countBy(orders,'coffee');
            this.setState({orders,coffeeStatistics});
        }
    }
    render() {
        const styleCursor = {cursor:"pointer"}
        const orderTableRecords = this.state.orders.map((order) => {
            return <tr key={order.email}>
                     <td>{order.coffee}</td>
                     <td>{order.email}</td>
                     <td>{order.size}</td>
                     <td>{order.flavor}</td>
                     <td>{order.strength}</td>
                <td>
                <i style={styleCursor}
                   onClick={this.deleteOrder.bind(this,order.email)}
                className="fa fa-trash"></i>
                </td>
                     </tr>})

            return this.state.flAddOrder? <OrderForm addFn={this.addOrder.bind(this)}/>:<div>
                <table className="table">
                    <thead>
                    <tr>
                        <th>Coffee</th>
                        <th>Email</th>
                        <th>Size</th>
                        <th>Flavor</th>
                        <th>Strength</th>
                    </tr>
                    </thead>
                    <tbody>
                    {orderTableRecords}
                    </tbody>
                </table>
                <i style={styleCursor} onClick={this.showOrderForm.bind(this)} className="fa fa-plus-circle"></i>
                <OrdersStatistics statistics={this.state.coffeeStatistics} />
            </div>

    }
}
export default Orders;