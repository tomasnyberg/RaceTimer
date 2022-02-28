import { Tabs, Tab, TabList, TabPanel, TabPanels } from "@chakra-ui/react";
import TimeRegisterPanel from "../components/TimeRegisterPanel";

export default function TimeRegistration() {
  return (
    <Tabs variant="soft-rounded" colorScheme="yellow">
      <TabList>
        <Tab>Starttider</Tab>
        <Tab>Måltider</Tab>
      </TabList>
      <TabPanels>
        <TabPanel>
          <TimeRegisterPanel type="start" />
        </TabPanel>
        <TabPanel>
          <TimeRegisterPanel type="end" />
        </TabPanel>
      </TabPanels>
    </Tabs>
  );
}
