import firebase from "firebase";
// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyB2FB6ZSD5T096DkM69Mm2o1Lb9E9M6g0M",
    authDomain: "orders-9feb5.firebaseapp.com",
    databaseURL: "https://orders-9feb5.firebaseio.com",
    projectId: "orders-9feb5",
    storageBucket: "orders-9feb5.appspot.com",
    messagingSenderId: "528711246243",
    appId: "1:528711246243:web:f261c63ef0cc0203d9fa16"
};
// Initialize Firebase
const appFirebase = firebase.initializeApp(firebaseConfig);
export default appFirebase;
