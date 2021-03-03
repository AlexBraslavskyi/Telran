import appFirebase from "../config/firebase";
import {collectionData} from "rxfire/firestore";
import * as firebase from "firebase";

export default class UsersFirebaseService {

    constructor(collections) {
        this.db = appFirebase.firestore().collection(collections);
    }

    getUsers() {
        return collectionData(this.db)

    }
    updateUser(emailAddress,user){
    return this.db.doc(emailAddress).update(user);
}
    addUser(user) {
        firebase.auth().createUserWithEmailAndPassword(user.emailAddress, user.password);
        return this.db.doc(user.emailAddress).set(user);
    }

    deleteUser(passport) {
        return this.db.doc(passport).delete();
    }
}