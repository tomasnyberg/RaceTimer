package result;

import org.junit.jupiter.api.Test;
import result.marathon.MarathonResult;
import result.marathon.MarathonResultExporter;
import result.marathon.MarathonResultRow;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMarathonResultExporter {

    private String fileName = "testMarathonResultExporter.txt";
    private List<MarathonResult> list = new ArrayList<MarathonResult>();

    private List<String> readAllLines() throws java.io.IOException
    {
        return Files.readAllLines(new File(fileName).toPath());
    }

    @Test
    public void testExportEmptyList() throws java.io.IOException {

        MarathonResultExporter.export(fileName, list);
        assertEquals(readAllLines(), new ArrayList<String>());

    }

    @Test
    public void testExport() throws java.io.IOException {

        list.add(new MarathonResultRow("12", "test1", LocalTime.parse("11:00:00"), LocalTime.parse("12:00:00")));
        list.add(new MarathonResultRow("15", "test2", LocalTime.parse("09:00:00"), LocalTime.parse("12:30:00")));
        list.add(new MarathonResultRow("1", "test3", LocalTime.parse("13:00:00"), LocalTime.parse("10:00:00")));
        MarathonResultExporter.export(fileName, list);

        List<String> truth = new ArrayList<String>();
        truth.add("12; test1; 11:00:00; 12:00:00; ");
        truth.add("15; test2; 09:00:00; 12:30:00; ");
        truth.add("1; test3; 13:00:00; 10:00:00; ");

        assertEquals(readAllLines(), truth);

    }

}
