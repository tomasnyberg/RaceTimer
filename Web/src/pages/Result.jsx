import {useEffect, useState} from 'react'
import { Table, Thead, Tbody, Modal, useToast, Input, ModalOverlay, ModalContent, ModalCloseButton, ModalFooter, ModalBody, ModalHeader, Tr, Th, Td, Box, HStack, Heading, Button, useDisclosure} from '@chakra-ui/react'


export default function Result() {
  const toast = useToast();
  const { isOpen, onOpen, onClose } = useDisclosure()
  const [title, setTitle] = useState("Titel")
  const [resultText, setResultText] = useState("")
  const [email, setEmail] = useState('')
  const [header, setHeader] = useState([])
  const [results, setResults] = useState([])
  useEffect(() => {
    fetch('http://localhost:4000/results')
    .then((res) => {
      res.json()
      .then((data) => {
        setHeader(data.header);
        setResults(data.results);
      })
    })
  }, [])

  useEffect(() => {
    fetch('http://localhost:4000/text')
    .then((res) => {
      res.text()
      .then((data) => {
        setResultText(data)
      })
    })
  }, [isOpen])
    
  return (
    <Box>
      <HStack justifyContent="space-between" my="1rem">
        <Heading>{title}</Heading>
        <Button colorScheme="yellow" onClick={onOpen}>Resultat i text</Button>
      </HStack>
      <Box overflowX="scroll">
        <Table variant="striped">
          <Thead>
            <Tr>
              {header && header.map((headerItem, index) => <Th key={index}>{headerItem}</Th>)}
            </Tr>
          </Thead>
          <Tbody>
            {results && results.map((resultRow, index) => (
              <Tr key={index}>
                {resultRow.map((bodyItem, index) => <Td key={index}>{bodyItem}</Td>)}
              </Tr>
            ))}
          </Tbody>
        </Table>
      </Box>

      <Modal size="6xl" isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Resultatet i text</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <Box padding="1rem">
              <pre>
              {resultText && resultText}
              </pre>
            </Box>
          </ModalBody>
        </ModalContent>
      </Modal>
    </Box>
  )
}
