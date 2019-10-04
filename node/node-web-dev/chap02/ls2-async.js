const fs = require('fs').promises

async function listFiles() {
  try {
    var dir = process.argv[2] || '.';
    const files = await fs.readdir(dir);
    for (let file of files) console.log(file);
  } catch (err) {
    console.error(err);
  }
}

listFiles();
