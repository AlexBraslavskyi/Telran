import appFirebase from "../config/firebase";
import {collectionData} from "rxfire/firestore";
import * as firebase from "firebase";

export default class OrdersFirebaseService {
    constructor(collection) {
        this.db = appFirebase.firestore().collection(collection);
    }

    getOrders() {
        return collectionData(this.db)
    }

    addOrder(order) {
        return this.db.doc(order.orderNumber).set(order);
    }

    deleteOrders(orderNumber) {
        return this.db.doc(orderNumber).delete();
    }

}