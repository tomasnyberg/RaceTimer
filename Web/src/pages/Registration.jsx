import {useEffect, useState} from 'react';
import { Input, VStack, Button, Heading, useToast,   Table,
  Thead,
  Tbody,
  Tr,
  Th,
  SimpleGrid,
  Td,
  Box, } from '@chakra-ui/react'

function Registration() {
  const toast = useToast();
  const [name, setName] = useState("");
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

  function onSubmit(event) {
    event.preventDefault();
    if (name) {
      const data = {
        name: name,
      }
      fetch('http://localhost:4000/drivers', { 
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }, 
        body: JSON.stringify(data)
      }).then((res) => {
        res.json().then((data) => {
          setName("");
          fetchDrivers();
          toast({
            title: 'Driver was added',
            description: `${data.name} with Start Number: ${data.startNumber} was registered`,
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
        description: "Name cannot be empty",
        status: 'error',
        duration: 6000,
        isClosable: true,
      })
    }
  }

  
  return (
    <>
      <SimpleGrid columns={{sm: 1, md: 2}}>
        <Box width="100%" height={{sm: "auto", md: "88vh"}} display="flex" alignItems="center" justifyContent="center">
          <Box width="80%">
            <Heading textAlign="center" marginY="2rem">Registrera Förare</Heading>
            <form onSubmit={(event) => onSubmit(event)} style={{ marginBottom: '2rem' }}>
              <VStack spacing="1rem">
              <Input value={name} placeholder="Name" size="lg" onChange={(event) => handleNameChange(event.target.value)} />
              <Button type="submit" size="lg" colorScheme="yellow">Lägg till förare</Button>
              </VStack>
            </form>
          </Box>
        </Box>
        <Box height={{sm: "auto", md: "88vh"}} overflowY="scroll">
          <Table variant="striped">
            <Thead>
              <Tr>
                <Th>Start Nummer</Th>
                <Th>Namn</Th>
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
        </Box>
      </SimpleGrid>
    </>
  );
}

export default Registration;
