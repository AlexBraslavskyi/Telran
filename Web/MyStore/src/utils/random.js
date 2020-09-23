import React from "react";
import { now } from "jquery";
// import {digitsId, domainArr, genderNames, genders, maxSalary, minSalary, titlesArr} from "../config/EmployeesConfig";


export function getRandomNumber(min, max) {
    return min +
        Math.round(Math.random() * (max - min));
}
export function getRandomElement(array) {
    return array[getRandomNumber(0, array.length - 1)];
}
export function getRandomOrderNumb() {
    let numb = Date.now()+getRandomNumber(10,1000);
    return numb.toString();
}

export function getRandomColor() {
    const color = ['black','red','green','blue','yellow','orange','purple','gold','lightgreen'];
    return getRandomElement(color);
}