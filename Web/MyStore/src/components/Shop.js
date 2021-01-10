import React, { Component,useEffect } from 'react';
import {connect, useDispatch, useSelector} from 'react-redux'
import { addToCart } from './actions/actions'

const Shop = (props) => {

   const handleClick = (id)=>{
        props.addToCart(id);
    }
    const items = props.items;
        let itemList = items.map(item=>{
            return(
                <div className="card" key={item.id}>
                        <div className="card-image">
                            <img className = 'itemImg' src={item.img} alt={item.title}/>
                            <span className="card-title">{item.title}</span>
                            <span to="/" className="btn-floating halfway-fab waves-effect waves-light red"
                                  onClick={()=>{handleClick(item.id)}}><i className="material-icons">add</i></span>
                        </div>

                        <div className="card-content">
                            <p>{item.desc}</p>
                            <p><b>Price: {item.price}₪</b></p>
                        </div>
                 </div>


            )
        })

        return(
            <div className="body">
                <h3 className="bodyTitle" style={{
                    // color:"#ee6e73",
                    fontFamily:"fantasy", fontWeigh:"bold"}}> - Our balloons - </h3>
                <div className="box">
                    {itemList}
                </div>
            </div>
        )
}

const mapStateToProps = (state)=>{

    return {
      items: state.items,
    }
  }
const mapDispatchToProps = (dispatch)=>{

    return{
        addToCart: (id)=>{dispatch(addToCart(id))}
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps)(Shop);
