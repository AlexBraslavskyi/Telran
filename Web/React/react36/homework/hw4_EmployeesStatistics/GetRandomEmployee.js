import React from "react";
import _ from 'lodash';
let firstId = 1;
function getRandomElement(array) {
    return array[_.random(0, array.length - 1 )];
}
export function getRandomEmployee() {
    const id = firstId++;
    const gender = getRandomElement(['male', 'female']);
    const name = getRandomName(gender);
    const emailAddress = `${name}@gmail.com`;
    const salary = _.random(5000, 35000);
    const title = getRandomTitle();
    return {id, name, emailAddress, gender, salary, title}
}
function getRandomName(gender) {
    const genderNames = {
        male: ['Moshe', 'David', 'Yosef', 'Asaf',
            'Lee', 'Ivan', 'Nik', 'Yuri', 'Igor', 'Alex'],
        female: ['Sara', 'Rivka', 'Klara', 'Olya', 'Irina', 'Vera',
            'Katya', 'Rita', 'Ida', 'Elena']}
    return getRandomElement(genderNames[gender]);
}
function getRandomTitle() {
    const titles = ['Developer' ,'Development Manager', 'QA Tester', 'QA Manager', 'Sales Person', 'Sales Manager']
    return getRandomElement(titles);
}