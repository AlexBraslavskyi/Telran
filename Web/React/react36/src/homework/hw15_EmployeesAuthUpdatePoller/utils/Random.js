import React from "react";
import {digitsId, domainArr, genderNames, genders, maxSalary, minSalary, titlesArr} from "../config/EmployeesConfig";


export function getRandomNumber(min, max) {
    return min +
        Math.round(Math.random() * (max - min));
}
export function getRandomElement(array) {
    return array[getRandomNumber(0, array.length - 1)];
}
export function getRandomEmployee() {
    const id = getRandomNumber(10 ** (digitsId - 1), 10 ** digitsId - 1);
    const gender = getRandomElement(genders);
    const name = getRandomName(gender);
    const emailAddress = getRandomEmail(id,name);
    const salary = getRandomNumber(minSalary, maxSalary);
    const title = getRandomTitle();
    return {id, name, emailAddress, gender, salary, title}

}

function getRandomName(gender) {
    return getRandomElement(genderNames[gender]);
}


function getRandomTitle() {
    const titles = titlesArr;
    return getRandomElement(titles);
}
function getRandomEmail(id, name) {
    const domain = domainArr;
    let newID = id.toString();
    newID = newID.substr(0,3);
    return name.toLowerCase() + newID + getRandomElement(domain);
}
export function getRandomColor() {
    const color = ['black','red','green','blue','yellow','orange','purple','gold','lightgreen'];
    return getRandomElement(color);
}