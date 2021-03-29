import appFirebase from "../config/firebase";
import {collectionData} from "rxfire/firestore";

export default class CommentsFirebaseService {

    constructor(collections) {
        this.db = appFirebase.firestore().collection(collections);
    }

    getComments() {
        return collectionData(this.db)

    }

    addNewComment(comment) {
        return this.db.doc(comment.id).set(comment);
    }

    deleteComment(id) {
        return this.db.doc(id).delete();
    }

}