import firebase from 'firebase';
const firebaseConfig = {
    apiKey: "AIzaSyCsfWMzglTf0t-D9SNU6Yw3ROLHpR9kWq8",
    authDomain: "employees-b0d46.firebaseapp.com",
    databaseURL: "https://employees-b0d46.firebaseio.com",
    projectId: "employees-b0d46",
    storageBucket: "employees-b0d46.appspot.com",
    messagingSenderId: "949873423147",
    appId: "1:949873423147:web:82c25d1ab2a65c8767cb6e"
};
// Initialize Firebase

const appFirebase = firebase.initializeApp(firebaseConfig);
export default appFirebase;