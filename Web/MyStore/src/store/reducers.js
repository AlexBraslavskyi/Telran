// import {combineReducers} from 'redux';
// import {SET_ORDERS, SET_FLAG_MOB_MENU, SET_USER_DATA} from "./common";

// const reducerEmployees = (state=[],action)=>{
//     return action.type === SET_ORDERS ? action.payload.slice(0) : state;// slice or spread
// };
// const reducerUserData = (state ={},action)=>{
//     return action.type === SET_USER_DATA ? {...action.payload} : state;
// }
// const reducerFlagMobMenu = (state ={},action)=>{
//     return action.type === SET_FLAG_MOB_MENU ? {...action.payload}:state;
// }
// export default combineReducers({
//     employees: reducerOrders,
//     userData: reducerUserData,
//     flagMobMenu:reducerFlagMobMenu
// })