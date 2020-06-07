import {combineReducers} from "redux";
import {SET_ORDERS, SET_USER_DATA} from "./common";
const reducerOrders = (state = [], action) => {
    return action.type === SET_ORDERS ? action.payload.slice(0) : state;
};
const reducerUserData = (state = {}, action) => {
    return action.type === SET_USER_DATA ? {...action.payload} : state;
};
export default combineReducers({
    orders: reducerOrders,
    userData: reducerUserData
})
