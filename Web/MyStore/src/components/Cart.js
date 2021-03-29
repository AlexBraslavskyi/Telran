import React, {useState} from 'react';
import {connect} from 'react-redux'
import {Link} from 'react-router-dom'
import {removeItem, addQuantity, subtractQuantity} from './actions/actions'
import {DELIVERY} from "../config/ShopConfig";
import {OrderForm} from "./OrderForm";
import OrdersFirebaseService from "../services/OrdersFirebaseService";

const Cart = (props) => {

    //to remove the item completely
    const handleRemove = (id) => {
        props.removeItem(id);
    }
    //to add the quantity
    const handleAddQuantity = (id) => {
        props.addQuantity(id);
    }
    //to subtract from the quantity
    const handleSubtractQuantity = (id) => {
        props.subtractQuantity(id);
    }

    const ordersService =
        new OrdersFirebaseService('orders');
    let [ordersSwitch, setOrdersSwitch] = useState(0)

    const handleChecked = (e) => {
        if (e.target.checked) {
            props.addDelivery();
        } else {
            props.subtractDelivery();
        }
    }
    const hideCart = () => {
        setOrdersSwitch(1)
        document.getElementById("cart").style.display = "none";
    }

    const items = props.items;
    let addedItems = items.length ?
        (items.map(item => {
                return (
                    <li className="collection-item avatar" key={item.id}>
                        <div className="item-img">
                            <img src={item.img} alt={item.img} className=""/>
                        </div>
                        <div className="item-desc">
                            <span className="title">{item.title}</span>
                            <p>{item.desc}</p>
                            <p><b>Price: {item.price}₪</b></p>
                            <p>
                                <b>Quantity: {item.quantity}</b>
                            </p>
                            <div className="add-remove">
                                <Link to="/cart"><i className="material-icons"
                                                    onClick={() => {
                                                        handleAddQuantity(item.id)
                                                    }}>arrow_drop_up</i></Link>
                                <Link to="/cart"><i className="material-icons"
                                                    onClick={() => {
                                                        handleSubtractQuantity(item.id)
                                                    }}>arrow_drop_down</i></Link>
                            </div>
                            <button className="waves-effect waves-light btn pink remove"
                                    onClick={() => {
                                        handleRemove(item.id)
                                    }}>Remove
                            </button>
                        </div>

                    </li>

                )
            })
        ) :

        (
            <p>Nothing.</p>
        )
    const showForm = () => {
        return <OrderForm addedItems={props.items}
                          ordersService={ordersService}
                          total={props.total}
                          delivery={props.delivery}/>
    }

    if (ordersSwitch == 1) {
        return showForm()
    }
    return (<div className="content">
            <div className="container">
                <div className="cart" id="cart">
                    <h1 className="center" style={{fontFamily: "fantasy",}}>Your order</h1>
                    <ul className="collection">
                        {addedItems}
                    </ul>
                    <div className="collection">
                        <li className="collection-item">
                            <label>
                                <input id="deliveryCheckbox" type="checkbox"
                                       checked={props.delivery == 0 ? false : true} onChange={handleChecked}/>
                                <span>Delivery : {DELIVERY} ₪</span>
                            </label>
                        </li>
                        <li className="collection-item"><b>Total: {props.total} ₪</b></li>
                    </div>
                    <div className="checkout">
                        <button className="waves-effect waves-light btn" style={{backgroundColor: "grey"}}
                                onClick={hideCart}>
                        <span style={{color: 'white'}}><i
                            className="material-icons right">send</i>Next</span>
                        </button>
                    </div>
                </div>
                {showForm}
            </div>
        </div>
    )
}

// export default Cart;
const mapStateToProps = (state) => {
    return {
        items: state.addedItems,

        total: state.total,
        delivery: state.delivery,
    }
}
const mapDispatchToProps = (dispatch) => {
    return {
        removeItem: (id) => {
            dispatch(removeItem(id))
        },
        addQuantity: (id) => {
            dispatch(addQuantity(id))
        },
        subtractQuantity: (id) => {
            dispatch(subtractQuantity(id))
        },
        addDelivery: () => {
            dispatch({type: 'ADD_DELIVERY'})
        },
        subtractDelivery: () => {
            dispatch({type: 'SUB_DELIVERY'})
        }
    }
}
export default connect(mapStateToProps, mapDispatchToProps)(Cart)