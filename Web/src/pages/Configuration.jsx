import React, { useState } from 'react'
import { Switch, Button, Box, Input, SimpleGrid, Center, RadioGroup, Stack, Radio } from '@chakra-ui/react'


export default function Configuration() {
  const [title, setTitle] = useState("")
  const [footer, setFooter] = useState("")
  const [resultPath, setResultPath] = useState("output/resultat.txt")
  const [namePath, setNamePath] = useState("input/namnfil.txt")
  const [sorting, setSorting] = useState(false)
  const [type, setType] = useState("marathon")

  function onSubmit(event) {
    event.preventDefault();
    console.log(title)
    console.log(footer)
    console.log(resultPath)
    console.log(namePath)
    console.log(sorting)
    console.log(type)
  }

  return (
    <form onSubmit={onSubmit}>
      <Center>
        <SimpleGrid columns={2} spacingY={10} maxW="500px">

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
            Path till resultatfil i server
          </Box>
          <Box height='30px'>
            <Input size="md" value={resultPath} onChange={(event) => setResultPath(event.target.value)} />
          </Box>

          {/* Path to name file in server */}
          <Box height='30px'>
            Path till namnfil i server
          </Box>
          <Box height='30px'>
            <Input size="md" value={namePath} onChange={(event) => setNamePath(event.target.value)} />
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
            <React.Fragment>
              {/* Sort result */}
              <Box height='30px'>
                Sortering av resultat
              </Box>
              <Box height='30px'>
                <Switch size='lg' colorScheme='yellow' value={sorting} onChange={(event) => setSorting(!sorting)} />
              </Box>
            </React.Fragment>
            :
            <React.Fragment>
              {/* Sort result */}
              <Box height='30px'>
                Sortering av resultat 2
              </Box>
              <Box height='30px'>
                <Switch size='lg' colorScheme='yellow' value={sorting} onChange={(event) => setSorting(!sorting)} />
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
