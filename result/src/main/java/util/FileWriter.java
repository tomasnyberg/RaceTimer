package util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWriter {

    public static void dump(String fileName, List<String> lines) throws java.io.IOException
    {
        Files.write(Paths.get(fileName), lines, StandardCharsets.UTF_8);
    }

}
