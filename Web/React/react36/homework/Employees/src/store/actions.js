import {SET_EMPLOYEES, SET_USER_DATA, SET_FLAG_MOB_MENU} from "./common";

export const actionEmployees = (employees)=>{
    return {type: SET_EMPLOYEES, payload:employees}
};
export const actionUserData = (userData)=>{
    return{type:SET_USER_DATA, payload:userData}
};
export const actionFlagMobMenu = (flagMobMenu)=>{
    return{type:SET_FLAG_MOB_MENU,payload:flagMobMenu}
}