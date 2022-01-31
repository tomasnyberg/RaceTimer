package result;

import result.marathon.MarathonResultExporter;
import result.marathon.MarathonResult;
import result.marathon.MarathonFileReader;

import java.io.IOException;
import java.util.List;


/**
 * The main program. TODO!
 */
public class ResultProgram {

    public static void main(String[] args) {
        String startTimeFile = "../starttider.txt";
        String endTimeFile = "../maltider.txt";
        String outFile = "../resultatFil.txt";
        String minimumTime = "01:10:03";

        List<MarathonResult> fileResults = MarathonFileReader.result(startTimeFile, endTimeFile, minimumTime);
        try {
            MarathonResultExporter.export(outFile, fileResults);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("This is the result program!");
    }

}
