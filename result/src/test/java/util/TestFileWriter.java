package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.config.Config;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileWriter {

  private String fileName = "testFileWriter.txt";
  private List<String> list = new ArrayList<>();
  private Config config = new Config();

  @BeforeEach
  public void fill() {
    config.setTitle("Test Race");
    config.setResultFile(fileName);
    list.add("Test 01 02");
    list.add("Test 02 02");
    list.add("Test 01 Test");
    list.add("////////");
    list.add("Test --------");
  }

  @Test
  public void testDump() throws java.io.IOException {

    FileWriter.dump(config, list);
    List<String> readList = Files.readAllLines(new File(config.getResultFile()).toPath());
    assertEquals(readList, list);
  }
}
