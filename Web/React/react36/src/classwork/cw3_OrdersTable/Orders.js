import React from "react";
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
class Orders extends React.Component{

constructor(props) {
    super(props);
    this.state = {orders:[]}
}
addOrder(){
    const order = getRandomOrder();
    const orders = this.state.orders;
    orders.push(order);
    this.setState({orders})    //orders:orders
}
componentDidMount() {
    this.intervalID = setInterval(this.addOrder.bind(this),5000)
}
componentWillUnmount() {
    clearInterval(this.intervalID);
}
render() {
    const orderTableRecords = this.state.orders.map((order)=>{
        return <tr>
            <td>{order.coffee}</td>
            <td>{order.email}</td>
            <td>{order.size}</td>
            <td>{order.flavor}</td>
            <td>{order.strength}</td>
        </tr>})
    return <table className="table">
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
}
}

export default Orders;