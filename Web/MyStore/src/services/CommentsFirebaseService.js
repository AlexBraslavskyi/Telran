import appFirebase from "../config/firebase";
import {collectionData} from "rxfire/firestore";
import * as firebase from "firebase";

export default class CommentsFirebaseService {

    constructor(collections) {
        this.db = appFirebase.firestore().collection(collections);
    }

    getComments() {
        return collectionData(this.db)

    }

    addComment(comment) {
        return this.db.doc(comment.id).set(comment);
    }

    deleteComment(id) {
        return this.db.doc(id).delete();
    }

}