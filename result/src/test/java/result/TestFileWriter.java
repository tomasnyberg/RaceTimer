package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.FileWriter;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileWriter {

    private String fileName = "testFileWriter.txt";
    private List<String> list = new ArrayList<String>();

    @BeforeEach
    public void fill() {
        list.add("Test 01 02");
        list.add("Test 02 02");
        list.add("Test 01 Test");
        list.add("////////");
        list.add("Test --------");
    }

    @Test
    public void testDump() throws java.io.IOException {

        FileWriter.dump(fileName, list);
        List<String> readList = Files.readAllLines(new File(fileName).toPath());
        assertEquals(readList, list);

    }

}
