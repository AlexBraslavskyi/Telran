import appFirebase from "../config/firebase";
import {collectionData} from "rxfire/firestore";

export default class ItemsFirebaseService {

    constructor(collections) {
        this.db = appFirebase.firestore().collection(collections);
    }

    getItems() {
        return collectionData(this.db)

    }

    addItem(item) {
        return this.db.doc(item.id).set(item);
    }

    deleteItem(id) {
        return this.db.doc(id).delete();
    }

}