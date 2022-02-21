import { useState } from 'react'
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

          {/* Tävlingstitel */}
          <Box height='30px'>
            Tävlingstitel
          </Box>
          <Box height='30px'>
            <Input size="md" placeholder="Tävlingstitel" value={title} onChange={(event) => setTitle(event.target.value)} />
          </Box>

          {/* Footer text för resultatfil */}
          <Box height='30px'>
            Footer-text för resultatfil
          </Box>
          <Box height='30px'>
            <Input size="md" placeholder="Textexempel i footer" value={footer} onChange={(event) => setFooter(event.target.value)} />
          </Box>
          
          {/* Path till resultatfil i server */}
          <Box height='30px'>
            Path till resultatfil i server
          </Box>
          <Box height='30px'>
            <Input size="md" value={resultPath} onChange={(event) => setResultPath(event.target.value)} />
          </Box>

          {/* Path till namnfil i server */}
          <Box height='30px'>
            Path till namnfil i server
          </Box>
          <Box height='30px'>
            <Input size="md" value={namePath} onChange={(event) => setNamePath(event.target.value)} />
          </Box>

          {/* Sortering av resultat */}
          <Box height='30px'>
            Sortering av resultat
          </Box>
          <Box height='30px'>
            <Switch size='lg' colorScheme='yellow' value={sorting} onChange={(event) => setSorting(!sorting)} />
          </Box>

          {/* Välja tävlingstyp */}
          <Box height='30px'>
            Sortering av resultat
          </Box>
          <Box height='30px'>
            <RadioGroup onChange={setType}>
              <Stack direction='row'>
                <Radio value='marathon'>Maraton</Radio>
                <Radio value='lap'>Varvlopp</Radio>
              </Stack>
            </RadioGroup>
          </Box>

          {/* Spara ändringar */}
          <Box height='30px'>
            <Button colorScheme='yellow' size='md' type="submit">Spara</Button>
          </Box>
        </SimpleGrid>
      </Center>
    </form>
  )
}
