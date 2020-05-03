import React from "react";


export const minSalary = 5000;
export const maxSalary = 35000;
export const genders = ['male', 'female'];
export const nameMinLength = 4;
export const digitsId = 5;
export const titlesArr = ['Developer', 'Development Manager', 'QA Tester', 'QA Manager', 'Sales Person', 'Sales Manager'];
export const pathEmployees = '/employees';
export const pathSalaryStatistics ='/salary/statistics';
export const pathSearch ='/search';
export const pathGenerations ='/generations';
export const pathTitleStatistics ='/title/statistics';
export const LINKS = [
    {path: pathEmployees, label: 'Employees',image:<i style={{cursor: 'pointer'}} className="fa fa-user-circle"/>},
    {path: pathGenerations, label: 'Employees Generations',image:<i style={{cursor: 'pointer'}} className="fa fa-users"/>},
    {path: pathSalaryStatistics, label: 'Salary Statistics',image:<i style={{cursor: 'pointer'}} className="fa fa-pie-chart"/>},
    {path: pathTitleStatistics, label: 'Title Statistics',image:<i style={{cursor: 'pointer'}} className="fa fa-line-chart"/>},
    {path: pathSearch, label: 'Employees Search',image:<i style={{cursor: 'pointer'}} className="fa fa-search"/>}
]
export const domainArr = [
    "@gmail.com",
    "@mail.ru",
    "@gov.il",
    "@yahoo.com",
    "@ukr.net",
    "@yandex.ru",
    "@walla.co.il"];
export const genderNames = {
    male: ['Moshe', 'David', 'Yosef', 'Asaf', 'Ivan',  'Yuri', 'Igor', 'Alex'],
    female: ['Sara', 'Rivka', 'Klara', 'Olya', 'Irina', 'Vera',
        'Katya', 'Rita', 'Elena']
}
