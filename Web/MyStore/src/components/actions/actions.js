import {SET_ORDERS, SET_USER_DATA, SET_FLAG_MOB_MENU} from "./common";
import { ADD_TO_CART,REMOVE_ITEM,SUB_QUANTITY,ADD_QUANTITY,ADD_DELIVERY,SUB_DELIVERY} from './action-types/cart-actions'
export const actionOrders = (orders)=>{
    return {type: SET_ORDERS, payload:orders}
};
export const actionUserData = (userData)=>{
    return{type:SET_USER_DATA, payload:userData}
};
export const actionFlagMobMenu = (flagMobMenu)=>{
    return{type:SET_FLAG_MOB_MENU,payload:flagMobMenu}
}
//add cart action
export const addToCart= (id)=>{
    return{
        type: ADD_TO_CART,
        id
    }
}
//remove item action
export const removeItem=(id)=>{
    return{
        type: REMOVE_ITEM,
        id
    }
}
//subtract qt action
export const subtractQuantity=(id)=>{
    return{
        type: SUB_QUANTITY,
        id
    }
}
//add qt action
export const addQuantity=(id)=>{
    return{
        type: ADD_QUANTITY,
        id
    }
}
//add delivery action
export const addDelivery=(id)=>{
    return{
        type: ADD_DELIVERY,
        id
    }
}
//subtract qt action
export const subtractDelivery=(id)=>{
    return{
        type: SUB_DELIVERY,
        id
    }
}