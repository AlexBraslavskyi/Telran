import React from "react";
import {useDispatch} from "react-redux";
import ItemsFirebaseService from "../services/ItemsFirebaseService";
import {actionItems, actionOrders} from "../components/actions/actions";

export const nameMinLength = 4;
export const genOrderNun = 5;
export const pathOrders = '/orders';
export const pathSearch ='/search';
export const pathLogin = '/login';
export const pathLogout = '/logout';
export const pathHome = '/home';
export const pathOrderForm = '/orderForm';
export const pathOrdersTable = '/orderForm';
export const pathShop = '/shop';
export const pathCart = '/cart';
export const pathProducts = '/products';
export const pathStatistics = '/statistics';
export const pathContacts = '/contacts';
export const pathUserProfile = '/userProfile';
export const POLLING_INTERVAL = 1000;
export const DELIVERY = 10;
export const LINKS = [
    {path: pathOrders, label: 'Orders',image:<i style={{cursor: 'pointer'}} className="fa fa-user-circle"/>,isAdmin:false},
    {path: pathSearch, label: 'Orders Search',image:<i style={{cursor: 'pointer'}} className="fa fa-search"/>,isAdmin:false}
]
export const TITLES = ["Customers statistics", "Items statistics"];
export const pathDBOrders = "appFirebase.firestore().collection('Orders')"
