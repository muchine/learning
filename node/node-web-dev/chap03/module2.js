const util = require('util');
const A = "a different value A";
const B = "a different value B";
const m1 = require('./module1');

console.log(`A=${A} B=${B} values = ${util.inspect(m1.values())}`);
console.log(`${m1.A} ${m1.B}`);
const vals = m1.values();
vals.B = "something completely different";
console.log(util.inspect(vals));
console.log(util.inspect(m1.values()));