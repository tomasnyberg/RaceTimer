import { useEffect, useState } from "react";
import {
  VStack,
  Text,
  HStack,
  Input,
  Button,
  Table,
  Thead,
  Tr,
  Th,
  Tbody,
  Td,
  useToast,
} from "@chakra-ui/react";

export default function TimeRegisterPanel({ type }) {
  const toast = useToast();
  const [startNumber, setStartNumber] = useState("");
  const [timeEntries, setTimeEntries] = useState([]);
  const [time, setTime] = useState(undefined);

  useEffect(() => {
    fetchTimes();
  }, []);

  function fetchTimes() {
    fetch("http://localhost:4000/" + type).then((res) => {
      res.json().then((data) => {
        setTimeEntries(data.reverse());
      });
    });
  }

  function onRemoveClick() {
    setTime(undefined);
  }

  function formatTime(date) {
    return `${(date.getHours() < 10 ? "0" : "") + date.getHours()}:${
      (date.getMinutes() < 10 ? "0" : "") + date.getMinutes()
    }:${(date.getSeconds() < 10 ? "0" : "") + date.getSeconds()}`;
  }

  function postTime(date) {
    const data = {
      startNumber: startNumber,
      time: formatTime(date),
    };
    fetch("http://localhost:4000/" + type, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    })
      .then((res) => {
        res.json().then((data) => {
          setStartNumber("");
          fetchTimes();
          toast({
            title: "Tid lades till",
            description: `#${data.startNumber} med tid: ${data.time} laddes till`,
            status: "success",
            duration: 3000,
            isClosable: true,
          });
        });
      })
      .catch((err) => {
        toast({
          title: "Det uppstod ett problem",
          description: err.message,
          status: "error",
          duration: 6000,
          isClosable: true,
        });
      });
  }

  function onStartNumberChanged(value) {
    setStartNumber(value);
  }

  function onSubmit(event) {
    event.preventDefault();
    if (time) {
      if (startNumber) {
        postTime(time);
        setTime(undefined);
      } else {
        toast({
          title: "Det uppstod ett problem",
          description: "Saknar startnummer",
          status: "error",
          duration: 3000,
          isClosable: true,
        });
      }
    } else if (startNumber) {
      postTime(new Date());
    } else {
      setTime(new Date());
    }
  }

  return (
    <VStack alignItems="center">
      <form onSubmit={onSubmit}>
        <HStack spacing="2">
          <Input
            w="60vw"
            placeholder="Startnummer"
            type="number"
            value={startNumber}
            onChange={(event) => onStartNumberChanged(event.target.value)}
          />
          <Button colorScheme="yellow" size="md" type="submit">
            Registrera
          </Button>
          {time && (
            <Button colorScheme="red" onClick={onRemoveClick} size="md">
              Ta bort tom
            </Button>
          )}
        </HStack>
        {time && (
          <Text textAlign="center" fontSize="2xl" mt="0.5rem">
            Det finns en tid registrerad utan startnummer. Tid:{" "}
            {formatTime(time)}
          </Text>
        )}
      </form>
      <Table variant="striped">
        <Thead>
          <Tr>
            <Th>Startnummer</Th>
            <Th>Tid</Th>
          </Tr>
        </Thead>
        <Tbody>
          {timeEntries &&
            timeEntries.map((timeEntry, index) => (
              <Tr key={index}>
                <Td>{timeEntry.startNumber}</Td>
                <Td>{timeEntry.time}</Td>
              </Tr>
            ))}
        </Tbody>
      </Table>
    </VStack>
  );
}
