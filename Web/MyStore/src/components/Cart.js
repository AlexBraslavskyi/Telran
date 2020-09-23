import React, { Component } from 'react';
import {connect, useSelector} from 'react-redux'
import { Link } from 'react-router-dom'
import { removeItem,addQuantity,subtractQuantity} from './actions/actions'
import Recipe from './Recipe'
import {getRandomOrderNumb} from "../utils/random";
const Cart = (props) =>{
// let orderNumber = getRandomOrderNumb();
    //to remove the item completely
const handleRemove = (id)=>{
        props.removeItem(id);
    }
    //to add the quantity
const handleAddQuantity = (id)=>{
    props.addQuantity(id);
    }
    //to subtract from the quantity
   const handleSubtractQuantity = (id)=>{
       props.subtractQuantity(id);
    }
    const items = props.items;
        let addedItems = items.length ?
            (  
                items.map(item=>{
                    return(
                       
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
                                             onClick={()=>{handleAddQuantity(item.id)}}>arrow_drop_up</i></Link>
                                            <Link to="/cart"><i className="material-icons" 
                                            onClick={()=>{handleSubtractQuantity(item.id)}}>arrow_drop_down</i></Link>
                                        </div>
                                        <button className="waves-effect waves-light btn pink remove" 
                                        onClick={()=>{handleRemove(item.id)}}>Remove</button>
                                    </div>
                                    
                                </li>
                         
                    )
                })
            ):

             (
                <p>Nothing.</p>
             )
       return(<div className="content">
            <div className="container">
                <div className="cart">
                    <h5 style={{color:"#ee6e73",fontFamily:"fantasy"}}>Your order</h5>
                    <ul className="collection">
                        {addedItems}
                    </ul>
                </div> 
                <Recipe items = {addedItems}/>
            </div>
            </div>
       )
    }

// export default Cart;
const mapStateToProps = (state)=>{
    return{
        items: state.addedItems,
    }
}
const mapDispatchToProps = (dispatch)=>{
    return{
        removeItem: (id)=>{dispatch(removeItem(id))},
        addQuantity: (id)=>{dispatch(addQuantity(id))},
        subtractQuantity: (id)=>{dispatch(subtractQuantity(id))}
    }
}
export default connect(mapStateToProps,mapDispatchToProps)(Cart)