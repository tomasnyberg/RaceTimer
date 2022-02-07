package util;

import result.config.Config;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWriter {

  public static void dump(Config config, List<String> lines) throws java.io.IOException {
    Files.write(Paths.get(config.getResultFile()), lines, StandardCharsets.UTF_8);
  }
}
