const express = require('express')
const fs = require('fs')
const cors = require('cors')
const app = express()
const port = 4000
const pathDrivers = 'drivers.txt'
const pathResult = 'resultatMarathon.txt'

app.use(cors())
app.use(express.json())

const drivers = []
const result = []
let resultHeader = []

app.get('/results', async (req, res) => {
  await readResultFile();
  res.status(200).json({header: resultHeader, results:result});
})

app.get('/drivers', (req, res) => {
  res.status(200).json(drivers)
})

app.post('/drivers', (req, res) => {
  const data = {name: req.body.name, startNumber: drivers.length + 1}
  drivers.push(data)
  fs.promises.appendFile(pathDrivers, `${data.startNumber}; ${data.name}\n`)
    .then(() => console.log("saved driver"))
    .catch((err) => console.error(err))
  res.status(201).json(data)
})

app.listen(port, () => {
  createAndLoadFile()
  console.log(`App listening on port ${port}`)
})

function createAndLoadFile() {
  if (fs.existsSync(pathDrivers)) {
    console.log(`Drivers file already exist at ${pathDrivers}`)
    fs.promises.readFile(pathDrivers, "utf-8")
      .then((contents) => {
        contents.split(/\r?\n/).forEach((line, index) => {
          if (index !== 0 && line !== "") {
            const [startNumber, name] = line.split("; ");
            drivers.push({
              startNumber: parseInt(startNumber),
              name: name
            })
          }
        })
      })
  } else {
    fs.promises.appendFile(pathDrivers, "StartNr; Namn\n")
      .then(() => console.log(`Created driver file at ${pathDrivers}`))
      .catch((err) => {
        console.error(err)
        process.exit(1)
      })
  }
}

async function readResultFile(){
  result.splice(0, result.length);
  resultHeader.splice(0, resultHeader.length);
  if (fs.existsSync(pathResult)) {
    const contents = await fs.promises.readFile(pathResult, "utf-8")
    contents.split(/\r?\n/).forEach((line, index) => {
      if(index === 2){
        resultHeader = resultHeader.concat(line.split("; "));
      }
      if (index > 2 && index < (contents.split(/\r?\n/).length - 2) && line !== "") {
        result.push(line.split("; "));
      }
    })
  } else {
    console.error("result file does not exist at " + pathResult);
  }
}
