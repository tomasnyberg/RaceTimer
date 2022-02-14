import {useEffect, useState} from 'react';
import { Input, Container, VStack, HStack, Button, IconButton, Heading, useToast,   Table,
  Thead,
  Tbody,
  Tr,
  Th,
  useColorMode,
  useColorModeValue,
  Td, } from '@chakra-ui/react'
import {SunIcon, MoonIcon} from "@chakra-ui/icons";

function App() {

  const { colorMode, toggleColorMode } = useColorMode()
  const icon = useColorModeValue(<MoonIcon/>, <SunIcon/>);
  

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
          duration: 9000,
          isClosable: true,
        })
      })
    } else {
      toast({
        title: 'There was a problem',
        description: "Name cannot be empty",
        status: 'error',
        duration: 9000,
        isClosable: true,
      })
    }
  }

  
  return (
    <>
    <HStack position="fixed" right="1rem" top="1rem" justify="end">
      <IconButton icon={icon} onClick={toggleColorMode} />
    </HStack>
    <Container>
      <Heading textAlign="center" marginY="2rem">Register Driver</Heading>
      <form onSubmit={(event) => onSubmit(event)} style={{ marginBottom: '2rem' }}>
        <VStack spacing="1rem">
        <Input value={name} placeholder="Name" size="lg" onChange={(event) => handleNameChange(event.target.value)} />
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
    </>
  );
}

export default App;
