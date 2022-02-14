import React from 'react'
import { HStack, IconButton, Container, useColorMode, Link as CLink, useColorModeValue } from '@chakra-ui/react'
import {SunIcon, MoonIcon} from "@chakra-ui/icons";
import { Link } from "react-router-dom";

export default function Layout({children}) {
  const { toggleColorMode } = useColorMode()
  const icon = useColorModeValue(<MoonIcon/>, <SunIcon/>);
  return (
    <>
      <Container maxW='container.xl' paddingY="1rem">
        <HStack justify="space-between" alignItems="center">
          <HStack spacing="2rem">
            <Link to="/">Result</Link>
            <Link to="/registration">Registration</Link>
          </HStack>
          <IconButton icon={icon} onClick={toggleColorMode} />
        </HStack>
      </Container>
      <Container maxW='container.xl'>
        {children}
      </Container>
    </>
  )
}
