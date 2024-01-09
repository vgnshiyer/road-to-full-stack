console.log('Hello from script.js');

// primitive data types = string, number, boolean, null, undefined, symbol, bigint

// object in javascript -- non-primitive data type
const student = {
    name: 'John Doe',
    age: 16,
}

console.log(student);

console.log(typeof student);

console.log(student.name);

console.log(student['name']);

student.name = 'Jane Doe';

console.log(student.name);

const product = {
    title: "Ball Pen",
    rating: 4,
    offer: 5,
    price: 250
}

console.log(product);
console.log(typeof product);

let a = 1;
let b = 2;

console.log("a + b =", a + b);
console.log("a - b =", a - b);
console.log("a * b =", a * b);
console.log("a / b =", a / b);
console.log("a % b =", a % b);
console.log("a ** b =", a ** b);

console.log("a++ =", a++);
console.log("a =", a);
console.log("++a =", ++a);
console.log("a =", a);

console.log("b-- =", b--);
console.log("b =", b);
console.log("--b =", --b);
console.log("b =", b);

for (let i = 0; i < 10; i++) {
    console.log(i);
}

let sum = 0
for (let i = 0; i < 10; i++) {
    sum += i;    
}
console.log(sum);

// for-of loop -> iterate over arrays or strings
for (let c of 'Hello World') {
    console.log(c);
}

// for-in loop -> iterate over objects
for (let key in student) {
    console.log("key =", key, "value =", student[key]);
}

for (let i = 0; i < 100; i++) {
    if (i % 2 == 0) {
        console.log(i);
    }
}

let str = 'Hello World';
console.log(str.length);
console.log(str.toUpperCase());
console.log(str.toLowerCase());
console.log(str.indexOf('o'));
console.log(str.lastIndexOf('o'));
console.log(str.includes('World'));
console.log(str.slice(6));
console.log(str.slice(-5));
console.log(str[0]);

// template literals
let name =  'John Doe';
let age = 16;
console.log(`Hello ${name}, you are ${age} years old`); // this is a template literal

str[0] = 'x';
console.log(str);
str = str.replace('H', 'x');
console.log(str);

let items = ['Pen', 'Pencil', 21, true, null, undefined, [1, 2, 3]];
console.log(items);
console.log(items[0]);
console.log(items.length);
items[0] = 'Ball Pen';
console.log(items);

for (let item of items) {
    console.log(item);
}

items.push('Marker');
console.log(items);
let popped = items.pop();
console.log(items);
console.log(popped);
items.unshift('Marker');
console.log(items);
let shifted = items.shift();
console.log(items);
console.log(shifted);
items.splice(1, 2);
console.log(items);
items.splice(1, 0, 'Pencil', 'Eraser'); // start, deleteCount, items to add (comma separated)
console.log(items);

function sum_(a, b) {
    return a + b;
}

console.log(sum_(1, 2));

let sum1 = function(a, b) {
    return a + b;
}

console.log(sum1(1, 2));

let sum2 = (a, b) => {
    return a + b;    
}

console.log(sum2(1, 2));

let sum3 = (a, b) => a + b;

console.log(sum3(1, 2));

console.log(sum1, sum2, sum3);