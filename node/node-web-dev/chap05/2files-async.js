const fs = require('fs-extra');
const util = require('util');
async function twoFiles() {
    const text = [
        await fs.readFile('hello.txt', 'utf8'),
        await fs.readFile('goodbye.txt', 'utf8')
    ];

    console.log(util.inspect(text));
}

twoFiles().catch(err => console.error(err));