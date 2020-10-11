import {combineReducers} from 'redux';
import Item1 from '../../images/anagram-28-frozen-sing-a-tune-foil-balloon-30324-01-a-p-14790223102015_900x.jpg'
import Item2 from '../../images/anagram-28-spider-man-birthday-sing-a-tune-foil-balloon-19531-01-a-p-14240379502655_900x.jpg'
import Item3 from '../../images/anagram-29-sweet-chocolate-sing-a-tune-foil-balloon-31850-01-a-p-14130106073151_900x.jpg'
import Item4 from '../../images/cti-12-all-round-bachelorette-party-latex-balloons-950046-c-14798915436607_900x.jpg'
import Item5 from '../../images/cti-12-all-round-bachelorette-party-latex-balloons-950046-c-14798915436607_900x.jpg'
import Item6 from '../../images/cti-12-pastel-birthday-6-pk-latex-balloons-912609-c-14841568395327_900x.jpg'
import {
    ADD_TO_CART,
    REMOVE_ITEM,
    SUB_QUANTITY,
    ADD_QUANTITY,
    ADD_DELIVERY,
    SUB_DELIVERY,
    SET_FLAG_MOB_MENU,
    SET_USER_DATA,
    SET_ORDERS,
    SET_ITEMS
} from '../actions/common'
import {actionFlagMobMenu, actionItems, actionOrders, actionUserData} from "../actions/actions";
import {DELIVERY} from "../../config/ShopConfig";
import OrdersFirebaseService from "../../services/OrdersFirebaseService";
import {useEffect} from "react";
import ItemsFirebaseService from "../../services/ItemsFirebaseService";
import {useDispatch, useSelector} from "react-redux";



const initState = {
    items:
        [],

    //     [
    //     {id:1,title:"Title", desc: "Descriptions", price:11,img:Item1},
    //     {id:2,title:"Title", desc: "Descriptions", price:8,img: Item2},
    //     {id:3,title:"Title", desc: "Descriptions", price:12,img: Item3},
    //     {id:4,title:"Title", desc: "Descriptions", price:10,img:Item4},
    //     {id:5,title:"Title", desc: "Descriptions", price:16,img: Item5},
    //     {id:6,title:"Title", desc: "Descriptions", price:9,img: Item6}
    // ],
    quantity:0,
    addedItems:[],
    total: 0,
    // orders:[],
    userData:{},
    flagMobile:{},
    delivery:0

}

const Reducers =
    (state = initState, action)=>{
    let existedQuantity = state.quantity;
    let addedItem =
        // state.addedItems.find(item=> action.id === item.id);
        state.items.find(item=> item.id === action.id);
        let existed_item= state.addedItems.find(item=> action.id === item.id);
        let new_items = state.addedItems.filter(item=> action.id !== item.id);
    //INSIDE SHOP COMPONENT

    if(action.type === ADD_TO_CART){
        //check if the action id exists in the addedItems
        if(existed_item)
        {
            if(window.confirm('You already added item with id=' + existed_item.id + '. Do you want to add quantity')){
               addedItem.quantity += 1;
                return{
                    ...state,
                    total: state.total + addedItem.price,
                    quantity: existedQuantity
                }
            }else{
                return{
                    ...state,
                    total: state.total,
                    quantity: existedQuantity
                }
            }}
        else{
          addedItem.quantity = 1;
            //calculating the total
            let newTotal = state.total + addedItem.price

            return{
                ...state,
                addedItems: [...state.addedItems, addedItem],
                total : newTotal,
                quantity: existedQuantity += 1,
            }

        }
    }
    if(action.type === REMOVE_ITEM){
        let itemToRemove= state.addedItems.find(item=> action.id === item.id);
        //calculating the total
        let newTotal = state.total - (itemToRemove.price * itemToRemove.quantity )
        return{
            ...state,
            addedItems: new_items,
            total: newTotal,
            quantity: existedQuantity -= 1,
        }
    }
    //INSIDE CART COMPONENT
    if(action.type=== ADD_QUANTITY){
        addedItem.quantity += 1
        let newTotal = state.total + addedItem.price
        return{
            ...state,
            addedItems: [...state.addedItems],
            total: newTotal
        }
    }
    if(action.type=== SUB_QUANTITY){
        //if the qt == 0 then it should be removed
        if(addedItem.quantity === 1){
            let newTotal = state.total - addedItem.price;
            return{
                ...state,
                addedItems: new_items,
                total: newTotal,
                quantity: existedQuantity -= 1,
            }
        }
        else {
            addedItem.quantity -= 1;
            let newTotal = state.total - addedItem.price
            return{
                ...state,
                addedItems: [...state.addedItems],
                total: newTotal
            }
        }

    }

    if(action.type=== ADD_DELIVERY){
        return{
            ...state,
            delivery: DELIVERY,
            total: state.total + DELIVERY
        }
    }

    if(action.type=== SUB_DELIVERY){
        return{
            ...state,
            delivery: 0,
            total: state.total - state.delivery
        }
    }

    if(action.type=== SET_ORDERS ){
        return {
            ...state,
            orders: action.payload.slice(0),
            quantity: 0
        }
    }

        if(action.type === SET_ITEMS){
            return {
                ...state,
                items: action.payload.slice(0)
            }
        }
        if(action.type=== SET_USER_DATA){
            return {
                ...state,
                userData: {...action.payload}
            }
        }
        if(action.type=== SET_FLAG_MOB_MENU){
            return {
                ...state,
                flagMobile: {...action.payload}
            }
        }
    else{
        return state
    }

}

export default Reducers;
