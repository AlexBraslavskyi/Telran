import {SET_ORDERS, SET_USER_DATA, SET_FLAG_MOB_MENU} from "./common";

export const actionOrders = (orders)=>{
    return {type: SET_ORDERS, payload:orders}
};
export const actionUserData = (userData)=>{
    return{type:SET_USER_DATA, payload:userData}
};
export const actionFlagMobMenu = (flagMobMenu)=>{
    return{type:SET_FLAG_MOB_MENU,payload:flagMobMenu}
}