import {SET_ORDERS, SET_USER_DATA} from "./common";

export const actionOrders = (orders) => {
    return {type: SET_ORDERS, payload: orders}
};
export const actionUserData = (userData) => {
    return {type: SET_USER_DATA, payload: userData}
};
