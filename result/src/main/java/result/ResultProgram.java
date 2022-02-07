package result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import result.marathon.MarathonResultExporter;
import result.marathon.MarathonResultSorter;
import result.marathon.MarathonResult;
import result.config.*;
import result.marathon.MarathonFileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.time.LocalTime;

/** The main program. TODO! */
public class ResultProgram {
  // ./gradlew :result:run --args='00:00:00 input/namnfil.txt input/starttider.txt
  // input/maltider.txt output/resultatFil.txt true'
  public static void main(String[] args) {
    Config config = new Config();

    if (args.length > 0) {
      try {
        config.setMaraton(new Maraton(args[0], args[2], args[3]));
        config.setNameFile(args[1]);
        config.setResultFile(args[4]);
        config.setSorting(args[5].equals("true"));
      } catch (Exception e) {
        System.out.println(
            "Invalid arguments. Arguments should follow format: minimumTime: <hh:mm:ss> nameFile: <File path with file extension> startTimeFile: <File path with file extension> endTimeFile: <File path with file extension> resultFile <File path with file extension>");
        System.exit(0);
      }
    } else {
      ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
      try {
        String configFile = Files.readString(Paths.get("config.yaml"));
        config = mapper.readValue(configFile, Config.class);
      } catch (JsonProcessingException e) {
        System.out.println(e.getMessage());
        System.exit(0);
      } catch (IOException e) {
        e.printStackTrace();
        System.exit(0);
      }
    }

    if (config.getType().equals("maraton")) {
      System.out.println("Programmet är inställt för Maraton");
      List<MarathonResult> fileResults =
              MarathonFileReader.result(
                      config.getNameFile(),
                      config.getMaraton().getStartTimesFile(),
                      config.getMaraton().getEndTimesFile(),
                      config.getMaraton().getMinimumTime());
      fileResults = new MarathonResultSorter().sortResults(fileResults);
      try {
        MarathonResultExporter.export(config, fileResults);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (config.getType().equals("varv")) {
      System.out.println("Programmet är inställt för Varvlopp");
    } else {
      System.out.println("Ingen accepterad programtyp är vald");
    }

    System.out.println("This is the result program!");
  }
}
