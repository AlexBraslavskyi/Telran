import appFirebase from "../config/firebase";
import {collectionData} from "rxfire/firestore";
import * as firebase from "firebase";
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

    addItemToOrder(orderNumber, item) {
        var docRef = this.db.doc(orderNumber)
        return docRef.update({
            'items': firebase.firestore.FieldValue.arrayUnion({
                id: item.id,
                img: item.img,
                title: item.title,
                description: item.description == undefined ? "Description not found" : item.description,
                price: item.price,
                quantity: item.quantity

            })

        });

    }

    deleteItemFromOrder(orderNumber, item) {

        var docRef = this.db.doc(orderNumber)

        return docRef.update({
            'items': firebase.firestore.FieldValue.arrayRemove({
                id: item.id,
                img: item.img,
                title: item.title,
                description: item.description,
                price: item.price,
                quantity: item.quantity

            })
        });

    }
}