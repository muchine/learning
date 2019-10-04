const fs = require('fs');
const util = require('util');
const fs_readdir = util.promisify(fs.readdir);

const listFiles = (async () => {
  const files = await fs_readdir('.');
  for (let fn of files) {
    console.log(fn);
  }
})

console.log(listFiles);

listFiles().catch(err => {
  console.error(err);
});
