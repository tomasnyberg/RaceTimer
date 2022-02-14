import React from 'react'
import { HStack, IconButton, Container, useColorMode, useColorModeValue } from '@chakra-ui/react'
import {SunIcon, MoonIcon} from "@chakra-ui/icons";
import { NavLink } from "react-router-dom";

export default function Layout({children}) {
  const { toggleColorMode } = useColorMode()
  const icon = useColorModeValue(<MoonIcon/>, <SunIcon/>);
  return (
    <>
      <Container maxW='container.xl' paddingY="1rem">
        <HStack justify="space-between" alignItems="center">
          <HStack spacing="2rem">
            <NavLink style={({ isActive }) => {
              return {
                textDecoration: isActive ? 'underline' : 'none'
              };
            }} to="/">Result</NavLink>
            <NavLink style={({ isActive }) => {
              return {
                textDecoration: isActive ? 'underline' : 'none'
              };
            }} to="/registration">Registration</NavLink>
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
