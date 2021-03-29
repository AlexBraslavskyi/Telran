import React from "react";
import {addQuantity, removeItem, subtractQuantity} from "./actions/actions";
import {connect} from "react-redux";

const Logout = (props) => {
    const authService = props.authService;
    const items = props.items;
    console.log(items)
    if (window.confirm('You are going to perform logout')) {
        authService.logout();
        if (items.length) {
            items.map(item => {
                props.removeItem(item.id);
            })
        }
    }
    return true;
}
const mapStateToProps = (state) => {
    return {
        items: state.addedItems,
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
        }
    }
}
export default connect(mapStateToProps, mapDispatchToProps)(Logout)
