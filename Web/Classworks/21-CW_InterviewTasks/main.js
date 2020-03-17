//"use strict";
// console.log(abc);
// var abc = "hhhh";
// console.log(lmn);
// let lmn = 'hhhh';
// var v1=1000;
// for (var v1 = 0; v1 < 10; v1++) {
//
// };
// console.log(v1);
// //let l1=1000;
// for (let l1 = 0; l1 < 10; l1++) {
//
// };
// console.log(l1);
// const t = 20;
// let t1;
// if (t > 20) {
//     console.log(t);
// }
// const obj = {};
// obj[2] = 10;
// console.log(obj);
// obj.a = 30;
// const a = 40;
// obj[a] = 50;
// console.log(obj);
//obj = {a: 30}; -> compilation error
const obj = {
    x: 5,
    y: function() {
        console.log(this.x)
    },
    z: () => this.x * 2

};
obj.y(); //function call; implied printing 5
console.log(obj.z()); //implied printing 10
const ar = [1,2,3,4];
// const even = ar.filter(function(n) {
//     return n % 2 == 0;
// });
const even = ar.filter(n => n % 2 == 0);
console.log(even);
const a = b => b * 2;
console.log(a(10));
const person = {
   id: 123,
   name: 'Moshe'
}
function f() {
    console.log(this);
}
//f.call(person);
//f.bind(person)();
function f1(fn) {
    fn();
}
f1(f);
class Person {
    constructor(id, name){
        this.id = id;
        this.name = name;
    }
    display() {
        console.log(this.id, this.name);
    }
}
const person1 = new Person(123, 'David');
person1.display();
f1(person1.display.bind(person1));


