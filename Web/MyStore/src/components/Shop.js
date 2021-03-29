import React from 'react';
import {connect} from 'react-redux'
import {addToCart} from './actions/actions'

const Shop = (props) => {

    const handleClick = (id) => {
        props.addToCart(id);
    }
    const items = props.items;

    let itemList = items.map(item => {
        return (
            <div className="wrapper" key={item.id}>
                <div className="card">
                    <div className="card-image">
                        <img className='itemImg' src={item.img} alt={item.title}/>
                        <span to="/" className="btn-floating halfway-fab waves-effect waves-light red"
                              onClick={() => {
                                  handleClick(item.id)
                              }}><i className="material-icons">add</i></span>
                    </div>

                    <div className="card-content">
                        <p style={{marginTop: '5px'}}><b>Price: {item.price}₪</b></p>
                        <span className="card-title"
                              style={{
                                  display: 'block',
                                  textOverflow: 'ellipsis',
                                  width: '400',
                                  overflow: 'hidden',
                                  whiteSpace: 'nowrap'
                              }}
                        >{item.title}</span>
                    </div>
                    <div className="inside">
                        <div className="icon"><i className="material-icons">info</i></div>
                        <div className="contents">
                            <span className="card-title">
                            {item.description}</span>
                        </div>
                    </div>
                </div>
            </div>


        )
    })

    return (
        <div className="body">
            <h2 className="bodyTitle" style={{
                fontFamily: "fantasy", fontWeigh: "bold"
            }}> - Our balloons - </h2>
            <div className="box">
                {itemList}
            </div>
        </div>
    )
}

const mapStateToProps = (state) => {

    return {
        items: state.items,
    }
}
const mapDispatchToProps = (dispatch) => {

    return {
        addToCart: (id) => {
            dispatch(addToCart(id))
        }
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps)(Shop);
