import React from "react";
import _ from 'lodash';
import OrdersStatistics from "./ordersStatistics";
import OrderForm from "./OrderForm";

class Orders extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
       flAddOrder:false
        }
        this.orders = this.props.orders;
    }
    showOrderForm(){
        this.setState({flAddOrder:true})
    }
    addOrder(order) {
        const orders = this.orders;
        const ind = orders.findIndex(o=>o.email===order.email)
        if(ind>=0){
            return false;
        }
        orders.push(order);
        this.props.updateOrdersFn(this.orders);
        this.setState({flAddOrder:false});
        return true;
    }
    deleteOrder(email){
        const orders = this.orders;
        if(window.confirm `You are going to delete order ${email}`){
            _.remove(orders, order => order.email === email)
            this.props.updateOrdersFn(this.orders);
        }
    }
    render() {
        const styleCursor = {cursor:"pointer"}
        const orderTableRecords = this.orders.map((order) => {
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
            </div>

    }
}
export default Orders;