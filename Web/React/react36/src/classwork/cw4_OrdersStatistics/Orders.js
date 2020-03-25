import React from "react";
import _ from 'lodash';
import OrdersStatistics from "./ordersStatistics";

let id = 1;
function getRandomNumber(min,max) {
return min + Math.round(Math.random()*(max-min))
}
function getRandomOrder() {
const emailId = id++;
const email = `${emailId}@gmail.com`;
    const size = getRandomNumber(10,30);
    const flavor = "flavor" + getRandomNumber(1,6);
    const coffee = "coffee" + getRandomNumber(1,20);
    const strength = "strength"+getRandomNumber(1,15);
    return {coffee,email,size,flavor,strength};
}
class Orders extends React.Component {

    constructor(props) {
        super(props);
        this.state = {orders: [],
            coffeeStatistics:{}
        }
    }

    addOrder() {
        const order = getRandomOrder();
        const orders = this.state.orders;
        orders.push(order);
        const coffeeStatistics = _.countBy(orders,'coffee');
        this.setState({orders,coffeeStatistics})    //orders:orders
    }

    deleteOrder(email){
        const orders = this.state.orders;
        if(window.confirm `You are going to delete order ${email}`){
            _.remove(orders, order => order.email == email)
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

            return <div>
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
                <i style={styleCursor} onClick={this.addOrder.bind(this)} className="fa fa-plus-circle"></i>
                <OrdersStatistics statistics={this.state.coffeeStatistics} />
            </div>

    }
}
export default Orders;