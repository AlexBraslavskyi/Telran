import appFirebase from "../config/firebase";
import {collectionData} from "rxfire/firestore";
import * as firebase from "firebase";
import {map} from "rxjs/operators";
import React from "react";

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

    deleteOrder(orderNumber) {
        return this.db.doc(orderNumber).delete();
    }
    addItemToOrder(orderNumber,item){
        var docRef = this.db.doc(orderNumber)
console.log(item);
        return  docRef.update({
            'items': firebase.firestore.FieldValue.arrayUnion({
                id:item.id,
                img:
                    // <img src={
                        item.img
                    // } alt="" border="3" height="100" width="100" />
                    ,
                title:item.title,
                description:item.description==undefined?"Description not found":item.description,
                price:item.price,
                quantity:item.quantity

            })

        });

    }
    deleteItemFromOrder(orderNumber, item) {

        var docRef = this.db.doc(orderNumber)

      return  docRef.update({
            'items': firebase.firestore.FieldValue.arrayRemove({
                id:item.id,
                img:
                    // <img src={
                        item.img
                    // } alt="" border="3" height="100" width="100" />
                    ,
                title:item.title,
                description:item.description,
                price:item.price,
                quantity:item.quantity

            })
        });

    }
}