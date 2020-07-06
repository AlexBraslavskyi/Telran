  
import React, { Component } from 'react'
import { connect } from 'react-redux'
import { addDelivery } from './actions/cartActions'
import { Link } from 'react-router-dom'
import { pathOrderForm } from '../config/ShopConfig'
class Recipe extends Component{
    
    componentWillUnmount() {
         if(this.refs.delivery.checked)
              this.props.subtractDelivery()
    }

    handleChecked = (e)=>{
        if(e.target.checked){
            this.props.addDelivery();
        }
        else{
            this.props.subtractDelivery();
        }
    }
  

    render(){
  
        return(
            <div className="container">
                <div className="collection">
                    <li className="collection-item">
                            <label>
                                <input type="checkbox" ref="delivery" onChange= {this.handleChecked} />
                                <span>Delivery(+5₪)</span>
                            </label>
                        </li>
                        <li className="collection-item"><b>Total: {this.props.total} ₪</b></li>
                    </div>
                    <div className="checkout">
                        <button className="waves-effect waves-light btn"> 
                        <Link to={pathOrderForm}><span style={{color:'white'}}><i
                            className="material-icons right">send</i>Submit</span></Link>
                        </button></div>
                    </div>
                
        )
    }
}

const mapStateToProps = (state)=>{
    return{
        addedItems: state.addedItems,
        total: state.total
    }
}

const mapDispatchToProps = (dispatch)=>{
    return{
        addDelivery: ()=>{dispatch({type: 'ADD_DELIVERY'})},
        subtractDelivery: ()=>{dispatch({type: 'SUB_DELIVERY'})}
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Recipe)