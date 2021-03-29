import React from "react";

export function getRandomNumber(min, max) {
    return min +
        Math.round(Math.random() * (max - min));
}

export function getRandomOrderNumb() {
    let numb = Date.now() + getRandomNumber(10, 1000);
    return numb.toString();
}

export function getRandomColor() {
    return '#' + (0x1000000 + (Math.random()) * 0xffffff).toString(16).substr(1, 6);
}