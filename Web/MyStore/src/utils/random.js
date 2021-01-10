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
    return '#'+(0x1000000+(Math.random())*0xffffff).toString(16).substr(1,6);
}