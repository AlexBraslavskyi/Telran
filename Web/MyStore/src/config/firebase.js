import firebase from "firebase";
// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyDQi8_YbJaIxQUklY_fLwAQ679Brj60HKI",
    authDomain: "orders-83d5a.firebaseapp.com",
    databaseURL: "https://orders-83d5a.firebaseio.com",
    projectId: "orders-83d5a",
    storageBucket: "orders-83d5a.appspot.com",
    messagingSenderId: "990674942552",
    appId: "1:990674942552:web:59572a7cbc138a5a6e282d"
};
// Initialize Firebase

const appFirebase = firebase.initializeApp(firebaseConfig);
export default appFirebase;

