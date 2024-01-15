console.log('Hello from script.js');

// primitive data types = string, number, boolean, null, undefined, symbol, bigint

// object in javascript -- non-primitive data type
const student = {
    name: 'John Doe',
    age: 16,
}

console.log(student); // this is an object

console.log(typeof student);

console.log(student.name); // dot notation -> prints John Doe

console.log(student['name']); // bracket notation -> prints John Doe

student.name = 'Jane Doe'; 

console.log(student.name); // prints Jane Doe

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

// for-in loop -> iterate over object
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
let popped = items.pop(); // removes the last element and returns it
console.log(items);
console.log(popped);
items.unshift('Marker'); // adds an element to the beginning of the array
console.log(items);
let shifted = items.shift(); // removes the first element and returns it
console.log(items);
console.log(shifted);
items.splice(1, 2); // start, deleteCount
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

let arr = ["pune", "mumbai", "delhi", "chennai", "kolkata"];

// we pass a function as a parameter to the forEach function
// forEach is called a higher order function -> function that takes another function as a parameter or returns a function
// the function we pass to forEach is called a callback function
arr.forEach((city, index) => {
    console.log(index, city);
});

let arr1 = [1, 2, 3, 4, 5];

// map -> returns a new array with the same number of elements as the original array
arr1.forEach((num) => console.log(num ** 2));

let newarr = arr1.map((num) => num ** 2);

console.log(newarr);

// difference between map and forEach
// map returns a new array
// forEach does not return anything

// filter -> returns a new array with only those elements that satisfy the condition
let evenArr = arr1.filter((num) => num % 2 === 0);

console.log(evenArr);

// reduce -> returns a single value
let sumArr = arr1.reduce((acc, num) => acc + num, 10); // with initial value

console.log(sumArr);

let smallest = arr1.reduce((acc, num) => acc < num ? acc : num, Infinity);

console.log(smallest);

// Js object has a special property called __proto__ which points to the prototype object
const employee = {
    calculateSalary: function() {
        return 10000;
    }
}

const john = {
    name: 'John Doe',
    age: 16,
    __proto__: employee
}

console.log(john.name);
console.log(john.age);
console.log(john.calculateSalary());

// if object and prototype have same property, object property is used

class Toyota {
    constructor(color) {
        console.log('Toyota constructor called');

        this.color = color;
    }

    start () {
        console.log('Toyota started');
    }

    stop () {
        console.log('Toyota stopped');
    }

    setColor(color) {
        this.color = color;
    }
}

let car = new Toyota('purple');
car.start();
car.stop();
console.log(car.color);
car.setColor('red');
console.log(car.color);

class Person {
    constructor(name) {
        this.name = name;
    }

    printName() {
        console.log(this.name);
    }
}

class Boy extends Person{
    constructor(name, age) {
        super(name);
        this.name = name;
        this.age = age;
    }

    printAge() {
        console.log(this.age);
    }
}

let person = new Person('John Doe');
person.printName();

let boy = new Boy('John', 16);
boy.printName(); // parent function invoked
boy.printAge(); // child function invoked

// Boy.prototype.printName = function() {
//     console.log('Hello');
// }

boy.printName(); // child function invoked

console.log(boy.__proto__)

class User {
    constructor(name, email) {
        this.name = name;
        this.email = email;
    }

    viewData() {
        console.log(`User ${this.name} viewed data`);
    }
}

class Admin extends User {
    constructor(name, email) {
        super(name, email);
    }

    viewData() {
        console.log(`Admin ${this.name} viewed data`);
    }
}

let user = new User('shakaal', 'shakaal@darinda.com');
user.viewData();
let admin = new Admin('gabbar', 'gabbar@darinda.com');
admin.viewData();

let x = 1;
let y = 0;
try{
    if (y === 0) {
        throw new Error('y cannot be zero');
    }
    console.log(x / y);
} catch(e) {
    console.log(e.message);
}

// asynchronous programming allows other instructions to run while other instructions are being executed

console.log('one');
setTimeout(() => {
    console.log('Hello');
}, 1000); // prints hello after 1 second
console.log('two'); // does not wait for setTimeout to finish -> prints two before hello

// callbacks -> functions that are passed as parameters to other functions

function printMessage(msg, callback) {
    console.log(msg);
    callback();
}

printMessage('one', () => {
    console.log('two');
});

// callback hell -> when we have multiple nested callbacks

function printData(data, callback) {
    setTimeout(() => {
        console.log(data);
        if (callback) callback();
    }, 1000);
}

printData('one', () => {
    console.log('printing data two');
    printData('two', () => {
        console.log('printing data three');
        printData('three');
    });
});
// pyramid of doom

// promises -> object that represents the eventual completion or failure of an asynchronous operation
// It is a solution to callback hell

let promise = new Promise((resolve, reject) => {
    console.log('Promise started');
    setTimeout(() => {
        resolve('Promise resolved');
    }, 1000);
});

function getData(data) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(`Promise resolved with ${data}`);
        }, 1000);
    });
}

// states of a promise
// pending -> initial state
// fulfilled -> operation completed successfully [resolve]
// rejected -> operation failed [reject]

console.log(promise);

promise.then((data) => {
    console.log(data); // prints Promise resolved if fulfilled
});

promise.catch((error) => {
    console.log(error); // prints error if rejected
});

let promise1 = getData('one');
console.log(promise1);
promise1.then((data) => {
    console.log(data);
});

// chaining promises -> alternative to callback hell

getData('one')
    .then((data) => {
        console.log(data);
        getData('two')
            .then((data) => {
                console.log(data);
                getData('three')
                    .then((data) => {
                        console.log(data);
                    });
            });
    });

// async await -> alternative to chaining promises

// async function always returns a promise
// awat keyword can only be used inside an async function
// await keyword waits for the promise to be resolved or rejected
// it pauses the execution of the async function until the promise is resolved or rejected

async function getDataFromApi() {
    let data = await getData('one');
    console.log(data);
    data = await getData('two');
    console.log(data);
}

getDataFromApi();