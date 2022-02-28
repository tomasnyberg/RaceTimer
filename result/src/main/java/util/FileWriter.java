package util;

import result.config.Config;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for writing text to a file
 */
public class FileWriter {

  /**
   * Puts a list of strings into the file
   * @param config config file
   * @param lines list of strings
   * @throws java.io.IOException
   */
  public static void dump(Config config, List<String> lines) throws java.io.IOException {
    Files.write(Paths.get(config.getResultFile()), lines, StandardCharsets.UTF_8);
  }
}
