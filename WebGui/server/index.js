const express = require('express')
const fs = require('fs')
const cors = require('cors')
const app = express()
const port = 4000
const path = 'drivers.txt'

app.use(cors())
app.use(express.json())

const drivers = []

app.get('/drivers', (req, res) => {
  res.status(200).json(drivers)
})

app.post('/drivers', (req, res) => {
  const data = req.body
  drivers.push(data)
  fs.promises.appendFile(path, `${data.startNumber}; ${data.name}\n`)
    .then(() => console.log("saved driver"))
    .catch((err) => console.error(err))
  res.status(201).json(drivers)
})

app.listen(port, () => {
  createAndLoadFile()
  console.log(`App listening on port ${port}`)
})

function createAndLoadFile() {
  if (fs.existsSync(path)) {
    console.log(`Drivers file already exist at ${path}`)
    fs.promises.readFile(path, "utf-8")
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
    fs.promises.appendFile(path, "StartNr; Namn\n")
      .then(() => console.log(`Created driver file at ${path}`))
      .catch((err) => {
        console.error(err)
        process.exit(1)
      })
  }
}
