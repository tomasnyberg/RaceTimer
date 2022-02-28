import {useEffect, useState} from 'react'
import { Table, Thead, Tbody, Modal, useToast, useClipboard, ModalOverlay, ModalContent, ModalCloseButton, ModalFooter, ModalBody, ModalHeader, Tr, Th, Td, Box, HStack, Heading, Button, useDisclosure} from '@chakra-ui/react'
import basePath from '..'

export default function Result() {
  const { isOpen, onOpen, onClose } = useDisclosure()
  const [title, setTitle] = useState("Titel")
  const [resultText, setResultText] = useState("")
  const [header, setHeader] = useState([])
  const [results, setResults] = useState([])

  const { hasCopied, onCopy } = useClipboard(resultText)

  useEffect(() => {
    fetch(basePath + '/results')
    .then((res) => {
      res.json()
      .then((data) => {
        setHeader(data.header);
        setResults(data.results);
        setTitle(data.title)
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
      <HStack alignContent="center" justifyContent="space-between" my="1rem">
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
          <ModalFooter>
            <Button colorScheme="green" mr="1rem" onClick={onCopy}>
            <a href={"mailto:ulf.asklund@cs.lth.se?subject=PVG - Resultat Team 05&body=" + encodeURIComponent(resultText)}>Skicka email till Ulf</a>
            </Button>
            <Button colorScheme="yellow">
              {hasCopied ? 'Kopierad' : 'Kopiera'}
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </Box>
  )
}
