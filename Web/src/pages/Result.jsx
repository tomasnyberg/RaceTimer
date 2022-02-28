import {useEffect, useState} from 'react'
import { Table, Thead, Tbody, Tr, Th, Td, Box} from '@chakra-ui/react'
import basePath from '..'


export default function Result() {
  const [header, setHeader] = useState([])
  const [results, setResults] = useState([])
  useEffect(() => {
    fetch(basePath + '/results')
    .then((res) => {
      res.json()
      .then((data) => {
        setHeader(data.header);
        setResults(data.results);
      })
    })
  }, [])
    
  return (
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
  )
}
