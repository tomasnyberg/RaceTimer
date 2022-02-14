import {useEffect, useState} from 'react';
import { Input, Container, VStack, Button, Heading, useToast,   Table,
  Thead,
  Tbody,
  Tr,
  Th,
  Td, } from '@chakra-ui/react'

function App() {

  const toast = useToast();
  const [name, setName] = useState("");
  const [startNumber, setStartNumber] = useState();
  const [drivers, setDrivers] = useState([]);

  useEffect(() => {
    fetchDrivers();
  }, [])

  function fetchDrivers() {
    fetch('http://localhost:4000/drivers')
      .then((res) => {
        res.json()
          .then((data) => setDrivers(data));
      });
  }

  function handleNameChange(value) {
    setName(value);
  }
  function handleStartNumberChange(value) {
    setStartNumber(value);
  }

  function onSubmit(event) {
    event.preventDefault();
    if (name && startNumber && startNumber > 0) {
      const data = {
        name: name,
        startNumber: startNumber
      }
      fetch('http://localhost:4000/drivers', { 
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }, 
        body: JSON.stringify(data)
      }).then(() => {
        setName("");
        setStartNumber(0);
        fetchDrivers();
        toast({
          title: 'Driver was added',
          description: `${name} with Start Number: ${startNumber} was registered`,
          status: 'success',
          duration: 3000,
          isClosable: true,
        })
      }).catch((err) => {
        toast({
          title: 'There was a problem',
          description: err.message,
          status: 'error',
          duration: 9000,
          isClosable: true,
        })
      })
    } else {
      toast({
        title: 'There was a problem',
        description: "Name and Start Number cannot be empty",
        status: 'error',
        duration: 9000,
        isClosable: true,
      })
    }
  }

  
  return (
    <Container>
      <Heading textAlign="center" marginY="2rem">Register Driver</Heading>
      <form onSubmit={(event) => onSubmit(event)} style={{ marginBottom: '2rem' }}>
        <VStack spacing="1rem">
        <Input value={name} placeholder="Name" size="lg" onChange={(event) => handleNameChange(event.target.value)} />
        <Input value={startNumber} size="lg" placeholder="Start Number" type="number" onChange={(event) => handleStartNumberChange(event.target.value)} />
        <Button type="submit" size="lg" colorScheme="yellow">Add Driver</Button>
        </VStack>
      </form>
      <Table variant='simple'>
        <Thead>
          <Tr>
            <Th>Start Number</Th>
            <Th>Name</Th>
          </Tr>
        </Thead>
        <Tbody>
          {drivers && drivers.map((driver, index) => (
            <Tr key={index}>
              <Td>{driver.startNumber}</Td>
              <Td>{driver.name}</Td>
            </Tr>
          ))}
        </Tbody>
      </Table>
    </Container>
  );
}

export default App;
