import React from "react";

export const nameMinLength = 4;
export const genOrderNun = 5;
export const pathOrders = '/orders';
export const pathSearch ='/search';
export const pathLogin = '/login';
export const pathLogout = '/logout';
export const pathHome = '/home';
export const pathOrderForm = '/orderForm';
export const pathShop = '/shop';
export const pathCart = '/cart';
export const POLLING_INTERVAL = 1000;
export const LINKS = [
    {path: pathOrders, label: 'Orders',image:<i style={{cursor: 'pointer'}} className="fa fa-user-circle"/>,isAdmin:false},
    {path: pathSearch, label: 'Orders Search',image:<i style={{cursor: 'pointer'}} className="fa fa-search"/>,isAdmin:false}
]

export const pathDBOrders = "appFirebase.firestore().collection('Orders')"