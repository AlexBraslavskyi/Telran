import appFirebase from "../config/firebase";
import {collectionData} from "rxfire/firestore";

export default class OrdersFirebaseService{
    constructor(collection) {
        this.db = appFirebase.firestore().collection(collection);
    }
    getOrders(){
        return collectionData(this.db)
    }
    addOrder(order){
     return this.db.doc(order.email).set(order);
    }
    deleteOrder(email){
        return this.db.doc(email).delete();
    }
}