import { useState } from 'react'
import { Table, Thead, Tbody, Tr, Th, Td, Button, Input, VStack, HStack} from '@chakra-ui/react'


export default function TimeRegistration() {
  const [startNumber, setStartNumber] = useState("")
  const [timeEntries, setTimeEntries] = useState([])
  
  
  function onStartNumberChanged(value) {
    setStartNumber(value);
  }

  function onSubmit(event) {
    event.preventDefault();
    console.log("Hej")
  }

  return (
    <VStack alignItems="center">
      <form onSubmit={onSubmit}>
        <HStack spacing="2">
          <Input w="68vw" placeholder="Start Number" value={startNumber} onChange={(event) => onStartNumberChanged(event.target.value)} />
          <Button colorScheme='yellow' size='md' type="submit">Register</Button>
        </HStack>
      </form>
      <Table variant="striped">
        <Thead>
          <Tr>
            <Th>Start Number</Th>
            <Th>Time</Th>
          </Tr>
        </Thead>
        <Tbody>
        </Tbody>
      </Table>
    </VStack>
  )
}
