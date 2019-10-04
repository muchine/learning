const fs = require('fs');
const util = require('util');
const fs_readdir = util.promisify(fs.readdir);

const listFiles = (async () => {
  var dir = '.';
  if (process.argv[2]) dir = process.argv[2];
  const files = await fs_readdir(dir);
  for (let fn of files) {
    console.log(fn);
  }
})

console.log(listFiles);

listFiles().catch(err => {
  console.error(err);
});
