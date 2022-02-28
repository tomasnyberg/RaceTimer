import {useEffect, useState} from 'react'
import { VStack, HStack, Input, Button, Table, Thead, Tr, Th, Tbody, Td, useToast } from '@chakra-ui/react'

export default function TimeRegisterPanel({ type }) {
  const toast = useToast();
  const [startNumber, setStartNumber] = useState("")
  const [timeEntries, setTimeEntries] = useState([])

  useEffect(() => {
    fetchTimes();
  }, [])

  function fetchTimes() {
    fetch('http://localhost:4000/' + type)
    .then((res) => {
      res.json()
      .then((data) => {
        setTimeEntries(data);
      })
    })
  }
  
  function onStartNumberChanged(value) {
    setStartNumber(value);
  }

  function onSubmit(event) {
    event.preventDefault();
    if (startNumber) {
      const date = new Date();
      const data = {
        startNumber: startNumber,
        time: `${(date.getHours()<10?'0':'') + date.getHours()}:${(date.getMinutes()<10?'0':'') + date.getMinutes()}:${(date.getSeconds()<10?'0':'') + date.getSeconds()}`
      }
      fetch('http://localhost:4000/' + type, { 
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }, 
        body: JSON.stringify(data)
      }).then((res) => {
        res.json().then((data) => {
          setStartNumber("");
          fetchTimes();
          toast({
            title: 'Time was added',
            description: `#${data.startNumber} with time: ${data.time} was added`,
            status: 'success',
            duration: 3000,
            isClosable: true,
          })
        })
      }).catch((err) => {
        toast({
          title: 'There was a problem',
          description: err.message,
          status: 'error',
          duration: 6000,
          isClosable: true,
        })
      })
    } else {
      toast({
        title: 'There was a problem',
        description: "Start number cannot be empty",
        status: 'error',
        duration: 6000,
        isClosable: true,
      })
    }
  }

  return (
    <VStack alignItems="center">
        <form onSubmit={onSubmit}>
          <HStack spacing="2">
            <Input w="68vw" placeholder="Start Number" type="number" value={startNumber} onChange={(event) => onStartNumberChanged(event.target.value)} />
            <Button colorScheme='yellow' size='md' type="submit">Registrera</Button>
          </HStack>
        </form>
        <Table variant="striped">
          <Thead>
          <Tr>
              <Th>Start Nummer</Th>
              <Th>Tid</Th>
          </Tr>
          </Thead>
          <Tbody>
            {timeEntries && timeEntries.map((timeEntry, index) => (
              <Tr key={index}>
                <Td>{timeEntry.startNumber}</Td>
                <Td>{timeEntry.time}</Td>
              </Tr>
            ))}
          </Tbody>
        </Table>
    </VStack>
  )
}
