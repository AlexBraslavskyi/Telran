import {combineReducers} from 'redux';
import {SET_ORDERS, SET_FLAG_MOB_MENU, SET_USER_DATA,SET_CARD_REDUCER} from "../actions/common";
import Item1 from '../../images/anagram-28-frozen-sing-a-tune-foil-balloon-30324-01-a-p-14790223102015_900x.jpg'
import Item2 from '../../images/anagram-28-spider-man-birthday-sing-a-tune-foil-balloon-19531-01-a-p-14240379502655_900x.jpg'
import Item3 from '../../images/anagram-29-sweet-chocolate-sing-a-tune-foil-balloon-31850-01-a-p-14130106073151_900x.jpg'
import Item4 from '../../images/cti-12-all-round-bachelorette-party-latex-balloons-950046-c-14798915436607_900x.jpg'
import Item5 from '../../images/cti-12-all-round-bachelorette-party-latex-balloons-950046-c-14798915436607_900x.jpg'
import Item6 from '../../images/cti-12-pastel-birthday-6-pk-latex-balloons-912609-c-14841568395327_900x.jpg'
import { ADD_TO_CART,REMOVE_ITEM,SUB_QUANTITY,ADD_QUANTITY,ADD_DELIVERY,SUB_DELIVERY} from '../actions/action-types/cart-actions'


const initState = {
    items: [
        {id:1,title:"Title", desc: "Descriptions", price:11,img:Item1},
        {id:2,title:"Title", desc: "Descriptions", price:8,img: Item2},
        {id:3,title:"Title", desc: "Descriptions", price:12,img: Item3},
        {id:4,title:"Title", desc: "Descriptions", price:10,img:Item4},
        {id:5,title:"Title", desc: "Descriptions", price:16,img: Item5},
        {id:6,title:"Title", desc: "Descriptions", price:9,img: Item6}
    ],
    addedItems:[],
    total: 0

}
const reducerOrders = (state=[],action)=>{
    return action.type === SET_ORDERS ? action.payload.slice(0) : state;// slice or spread
};
const reducerUserData = (state ={},action)=>{
    return action.type === SET_USER_DATA ? {...action.payload} : state;
}
const reducerFlagMobMenu = (state ={},action)=>{
    return action.type === SET_FLAG_MOB_MENU ? {...action.payload}:state;
}
const reducerCart= (state = initState,action)=>{

    //INSIDE HOME COMPONENT
    if(action.type === ADD_TO_CART){
          let addedItem = state.items.find(item=> item.id === action.id)
          //check if the action id exists in the addedItems
         let existed_item= state.addedItems.find(item=> action.id === item.id)
         if(existed_item)
         {
           if(window.confirm('You allready added item with id=' + existed_item.id + '. Do you want to add quantity')){
            addedItem.quantity += 1 
             return{
                ...state,
                 total: state.total + addedItem.price,
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
            }
            
        }
    }
    if(action.type === REMOVE_ITEM){
        let itemToRemove= state.addedItems.find(item=> action.id === item.id)
        let new_items = state.addedItems.filter(item=> action.id !== item.id)
        
        //calculating the total
        let newTotal = state.total - (itemToRemove.price * itemToRemove.quantity )
        console.log(itemToRemove)
        return{
            ...state,
            addedItems: new_items,
            total: newTotal
        }
    }
    //INSIDE CART COMPONENT
    if(action.type=== ADD_QUANTITY){
        let addedItem = state.items.find(item=> item.id === action.id)
          addedItem.quantity += 1 
          let newTotal = state.total + addedItem.price
          return{
              ...state,
              total: newTotal
          }
    }
    if(action.type=== SUB_QUANTITY){  
        let addedItem = state.items.find(item=> item.id === action.id) 
        //if the qt == 0 then it should be removed
        if(addedItem.quantity === 1){
            let new_items = state.addedItems.filter(item=>item.id !== action.id);
            let newTotal = state.total - addedItem.price
            return{
                ...state,
                addedItems: new_items,
                total: newTotal
            }
        }
        else {
            addedItem.quantity -= 1;
            let newTotal = state.total - addedItem.price
            return{
                ...state,
                total: newTotal
            }
        }
        
    }

    if(action.type=== ADD_DELIVERY){
          return{
              ...state,
              total: state.total + 5
          }
    }

    if(action.type=== SUB_DELIVERY){
        return{
            ...state,
            total: state.total - 5
        }
  }
    
  else{
    return state
    }
    
}

export default combineReducers({
    orders: reducerOrders,
    userData: reducerUserData,
    flagMobMenu:reducerFlagMobMenu,
    cardReducer:reducerCart
})