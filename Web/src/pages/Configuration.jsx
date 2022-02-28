import React, { useState, useEffect } from 'react'
import { Switch, Button, Box, Input, SimpleGrid, Center, RadioGroup, Stack, Radio } from '@chakra-ui/react'


export default function Configuration() {
  const [title, setTitle] = useState("")
  const [footer, setFooter] = useState("")
  const [resultFile, setResultFile] = useState("output/resultat.txt")
  const [nameFile, setNameFile] = useState("input/namnfil.txt")
  const [sorting, setSorting] = useState(false)
  const [type, setType] = useState("lap")
  const [typeSpecificData, setTypeSpecificData] = useState({
    marathon: {
      minimumTime: "",
      startTimesFile: "input/starttider.txt",
      goalTimesFile: "input/maltider1.txt"
    },
    lap: {
      minimumTime: "",
      massStart: false,
      timeForMassStart: "",
      startTimesFile: "input/starttider.txt",
      goalTimesFiles: "",
      raceEndTime: ""
    }
  })

  useEffect(() => {
    fetchConfig();
  }, [])

  function fetchConfig() {
    fetch('http://localhost:4000/config')
      .then((res) => {
        res.json()
          .then((data) => {
            setTitle(data.title);
            setFooter(data.footer);
            setResultFile(data.resultFile);
            setNameFile(data.nameFile);
            setSorting(data.sorting);
            setType(data.type);
            
            // Marathon
            onTypeSpecificDataChanged("marathon", "minimumTime", data.marathon.minimumTime)
            onTypeSpecificDataChanged("marathon", "startTimesFile", data.marathon.startTimesFile)
            onTypeSpecificDataChanged("marathon", "goalTimesFile", data.marathon.goalTimesFile)

            // Lap
            onTypeSpecificDataChanged("lap", "minimumTime", data.lap.minimumTime)
            onTypeSpecificDataChanged("lap", "massStart", data.lap.massStart)
            onTypeSpecificDataChanged("lap", "timeForMassStart", data.lap.timeForMassStart)
            onTypeSpecificDataChanged("lap", "startTimesFile", data.lap.startTimesFile)
            onTypeSpecificDataChanged("lap", "goalTimesFiles", data.lap.goalTimesFiles[0])
            onTypeSpecificDataChanged("lap", "raceEndTime", data.lap.raceEndTime)
          });

      });
  }

  function onSubmit(event) {
    event.preventDefault();
    console.log(title)
    console.log(footer)
    console.log(resultFile)
    console.log(nameFile)
    console.log(sorting)
    console.log(type)
    console.log(typeSpecificData)
  }

  function onTypeSpecificDataChanged(currentType, key, value) {
    let updatedValue = typeSpecificData

    if(currentType === "lap" && key === "goalTimesFiles") {
      updatedValue[currentType][key] = value.split(",")
    } else {
      updatedValue[currentType][key] = value;
    }
    
    setTypeSpecificData(typeSpecificData => ({
      ...typeSpecificData,
      ...updatedValue
    }));
  }

  return (
    <form onSubmit={onSubmit}>
      <Center>
        <SimpleGrid columns={2} spacingY={10} spacingX={20} maxW="750px" style={{marginBottom: 50}}>

          {/* Competition title */}
          <Box height='30px'>
            Tävlingstitel
          </Box>
          <Box height='30px'>
            <Input size="md" placeholder="Tävlingstitel" value={title} onChange={(event) => setTitle(event.target.value)} />
          </Box>

          {/* Footer text for result file */}
          <Box height='30px'>
            Footer-text för resultatfil
          </Box>
          <Box height='30px'>
            <Input size="md" placeholder="Textexempel i footer" value={footer} onChange={(event) => setFooter(event.target.value)} />
          </Box>
          
          {/* Path to result file in server */}
          <Box height='30px'>
            Filväg till resultatfil i server
          </Box>
          <Box height='30px'>
            <Input size="md" value={resultFile} onChange={(event) => setResultFile(event.target.value)} />
          </Box>

          {/* Path to name file in server */}
          <Box height='30px'>
            Filväg till namnfil i server
          </Box>
          <Box height='30px'>
            <Input size="md" value={nameFile} onChange={(event) => setNameFile(event.target.value)} />
          </Box>

          {/* Sort result */}
          <Box height='30px'>
            Sortering av resultat
          </Box>
          <Box height='30px'>
            <Switch size='lg' colorScheme='yellow' value={sorting} onChange={(event) => setSorting(!sorting)} />
          </Box>

          {/* Choose competition type */}
          <Box height='30px'>
            Tävlingstyp
          </Box>
          <Box height='30px'>
            <RadioGroup onChange={setType} defaultValue={type}>
              <Stack direction='row'>
                <Radio value='marathon'>Maraton</Radio>
                <Radio value='lap'>Varvlopp</Radio>
              </Stack>
            </RadioGroup>
          </Box>

          {/* Marathon form or lap form depending on competition type */}
          { type === "lap" ?

            // Lap
            <React.Fragment>

              {/* Minimum time for lap */}
              <Box height='30px'>
                Minimumtid per varv
              </Box>
              <Box height='30px'>
                <Input size="md" placeholder="Använd formatet hh:mm:ss" value={typeSpecificData[type].minimumTime} onChange={(event) => onTypeSpecificDataChanged(type, "minimumTime", event.target.value)} />
              </Box>

              {/* Mass start true or false */}
              <Box height='30px'>
                Mass-start
              </Box>
              <Box height='30px'>
                <Switch size='lg' colorScheme='yellow' value={typeSpecificData[type].massStart} onChange={(event) => setSorting(!typeSpecificData[type].massStart)} />
              </Box>

              {/* Time for mass start */}
              <Box height='30px'>
                Tid för mass-start
              </Box>
              <Box height='30px'>
                <Input size="md" placeholder="Använd formatet hh:mm:ss" value={typeSpecificData[type].timeForMassStart} onChange={(event) => onTypeSpecificDataChanged(type, "timeForMassStart", event.target.value)} />
              </Box>

              {/* Start time file */}
              <Box height='30px'>
                Filväg till fil med starttider i server
              </Box>
              <Box height='30px'>
                <Input size="md" value={typeSpecificData[type].startTimesFile} onChange={(event) => onTypeSpecificDataChanged(type, "startTimesFile", event.target.value)} />
              </Box>

              {/* End time file */}
              <Box height='30px'>
                Filväg till fil med måltider i server
              </Box>
              <Box height='30px'>
                <Input size="md"  placeholder="T.ex: fil1.txt" value={typeSpecificData[type].goalTimesFiles} onChange={(event) => onTypeSpecificDataChanged(type, "goalTimesFiles", event.target.value)} />
              </Box>

              {/* Race end time */}
              <Box height='30px'>
                Slut-tid för lopp
              </Box>
              <Box height='30px'>
                <Input size="md" placeholder="Använd formatet hh:mm:ss" value={typeSpecificData[type].raceEndTime} onChange={(event) => onTypeSpecificDataChanged(type, "raceEndTime", event.target.value)} />
              </Box>
            
            </React.Fragment>

            :

            // Marathon
            <React.Fragment>

              {/* Minimum time for race */}
              <Box height='30px'>
                Minimumtid för helt lopp
              </Box>
              <Box height='30px'>
                <Input size="md" placeholder="Använd formatet hh:mm:ss" value={typeSpecificData[type].minimumTime} onChange={(event) => onTypeSpecificDataChanged(type, "minimumTime", event.target.value)} />
              </Box>

              {/* Start time file */}
              <Box height='30px'>
                Filväg till fil med starttider i server
              </Box>
              <Box height='30px'>
                <Input size="md" value={typeSpecificData[type].startTimesFile} onChange={(event) => onTypeSpecificDataChanged(type, "startTimesFile", event.target.value)} />
              </Box>

              {/* End time file */}
              <Box height='30px'>
                Filväg till fil med måltider i server
              </Box>
              <Box height='30px'>
                <Input size="md" value={typeSpecificData[type].goalTimesFile} onChange={(event) => onTypeSpecificDataChanged(type, "goalTimesFile", event.target.value)} />
              </Box>
            
            </React.Fragment>

          }


          {/* Save config */}
          <Box height='30px'>
            <Button colorScheme='yellow' size='md' type="submit">Spara</Button>
          </Box>
        </SimpleGrid>
      </Center>
    </form>
  )
}
