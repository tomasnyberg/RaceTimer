package result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import result.lap.LapResult;
import result.marathon.*;
import result.config.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/** The main program.*/
public class ResultProgram {

  /**
   * The main method for the program
   * The program uses a config file named config.yaml in the working directory.
   */
  public static void main(String[] args) {
    Config config = new Config();

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

    if (config.getType().equals("marathon")) {
      System.out.println("Programmet är inställt för Marathon");
      MarathonResult marathonResult = new MarathonResult(config);
      marathonResult.generateResult();
    } else if (config.getType().equals("lap")) {
      System.out.println("Programmet är inställt för Varvlopp");
      LapResult lap = new LapResult(config);
      lap.generateResult();
    } else {
      System.out.println("Ingen accepterad programtyp är vald");
    }

    System.out.println("This is the result program!");
  }
}
