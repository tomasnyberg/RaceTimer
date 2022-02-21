const express = require('express')
const fs = require('fs')
const cors = require('cors')
const path = require('path')
const app = express()
const port = 4000
const pathDrivers = 'drivers.txt'
const pathResult = './output/resultat.txt'
const pathStartTime = './input/starttider.txt'
const pathEndTime = './input/maltider.txt'

app.use(cors())
app.use(express.json())
app.use(express.static(path.join(__dirname, 'web')));

const drivers = []
const startTimes = []
const endTimes = []
const result = []
let resultHeader = []

app.get('/results', async (req, res) => {
  var child = require('child_process').spawn('java', ['-jar', 'result-v0.1b.jar']);
  child.on('exit', async (code) => {
    if(child.exitCode === 0){
      await readResultFile();
      res.status(200).json({header: resultHeader, results:result});
    } else {
      console.log("problem with running java result program");
    }
  })
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

app.get('/start', (req, res) => {
  res.status(200).json(startTimes)
})

app.post('/start', (req, res) => {
  const data = {startNumber: req.body.startNumber, time: req.body.time}
  startTimes.push(data)
  fs.promises.appendFile(pathStartTime, `${data.startNumber}; ${data.time}\n`)
    .then(() => console.log("Saved time"))
    .catch((err) => console.error(err))
  res.status(201).json(data)
})

app.get('/start', (req, res) => {
  res.status(200).json(startTimes)
})

app.post('/start', (req, res) => {
  const data = {startNumber: req.body.startNumber, time: req.body.time}
  startTimes.push(data)
  fs.promises.appendFile(pathStartTime, `${data.startNumber}; ${data.time}\n`)
    .then(() => console.log("Saved time"))
    .catch((err) => console.error(err))
  res.status(201).json(data)
})

app.get('/end', (req, res) => {
  res.status(200).json(endTimes)
})

app.post('/end', (req, res) => {
  const data = {startNumber: req.body.startNumber, time: req.body.time}
  endTimes.push(data)
  fs.promises.appendFile(pathEndTime, `${data.startNumber}; ${data.time}\n`)
    .then(() => console.log("Saved time"))
    .catch((err) => console.error(err))
  res.status(201).json(data)
})

app.get('/*', function (req, res) {
  res.sendFile(path.join(__dirname, 'web', 'index.html'));
});

app.listen(port, () => {
  createAndLoadNameFile()
  createAndLoadStartFile()
  createAndLoadEndFile()
  console.log(`App listening on port ${port}`)
})

function createAndLoadStartFile() {
  if (fs.existsSync(pathStartTime)) {
    console.log(`StartTime file already exist at ${pathStartTime}`)
    fs.promises.readFile(pathStartTime, "utf-8")
      .then((contents) => {
        contents.split(/\r?\n/).forEach((line, index) => {
          if (line !== "") {
            const [startNumber, time] = line.split("; ");
            startTimes.push({
              startNumber: parseInt(startNumber),
              time: time
            })
          }
        })
      })
  } else {
    fs.promises.writeFile(pathStartTime, "")
      .then(() => console.log(`Created StartTime file at ${pathStartTime}`))
      .catch((err) => {
        console.error(err)
        process.exit(1)
      })
  }
}

function createAndLoadEndFile() {
  if (fs.existsSync(pathEndTime)) {
    console.log(`StartTime file already exist at ${pathEndTime}`)
    fs.promises.readFile(pathEndTime, "utf-8")
      .then((contents) => {
        contents.split(/\r?\n/).forEach((line, index) => {
          if (line !== "") {
            const [startNumber, time] = line.split("; ");
            endTimes.push({
              startNumber: parseInt(startNumber),
              time: time
            })
          }
        })
      })
  } else {
    fs.promises.writeFile(pathEndTime, "")
      .then(() => console.log(`Created StartTime file at ${pathEndTime}`))
      .catch((err) => {
        console.error(err)
        process.exit(1)
      })
  }
}

function createAndLoadNameFile() {
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
