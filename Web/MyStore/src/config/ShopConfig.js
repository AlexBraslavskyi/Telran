import React from "react";

export const nameMinLength = 4;
export const digitsId = 5;
export const pathOrders = '/orders';
export const pathSearch ='/search';
export const pathLogin = '/login';
export const pathLogout = '/logout';
export const pathWelcome = '/welcome'
export const POLLING_INTERVAL = 1000;
export const LINKS = [
    {path: pathOrders, label: 'Orders',image:<i style={{cursor: 'pointer'}} className="fa fa-user-circle"/>,isAdmin:false},
    {path: pathSearch, label: 'Orders Search',image:<i style={{cursor: 'pointer'}} className="fa fa-search"/>,isAdmin:false}
]

export const pathDBEmployees = "appFirebase.firestore().collection('TODO Name of db')"