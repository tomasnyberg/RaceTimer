import {useEffect, useState} from 'react'
import { Container, Table, Thead, Tbody, Tr, Th, Td} from '@chakra-ui/react'


export default function Result() {
  useEffect(() => {
    fetch('http://localhost:4000/results')
    .then((res) => {
      res.json()
      .then((data) => {
        console.log(data);
      })
    })
  }, [])
  
  
  return (
    <Table>
      <Thead>
        <Tr>
          <Th>Rank</Th>
          <Th>Start Number</Th>
          <Th>Name</Th>
          <Th>Total Time</Th>
          <Th>Start Time</Th>
          <Th>Finish Time</Th>
        </Tr>
      </Thead>
      <Tbody>
        <Tr>
          <Td></Td>
        </Tr>
      </Tbody>
    </Table>
  )
}
