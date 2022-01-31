package result;

import result.marathon.MarathonResultExporter;
import result.marathon.MarathonResult;
import result.marathon.MarathonFileReader;

import java.io.IOException;
import java.util.List;
import java.time.LocalTime;


/**
 * The main program. TODO!
 */
public class ResultProgram {

    public static void main(String[] args) {
        String startTimeFile = "../starttider.txt";
        String endTimeFile = "../maltider.txt";
        String outFile = "../resultatFil.txt";
        String minimumTime = "00:00:00";

        try {
            minimumTime = args[0];
            System.out.println(minimumTime);
            LocalTime.parse(minimumTime);
        } catch (Exception e) {
            System.out.println("Invalid minimum time format. Enter time according to [hh:mm:ss]");
            System.exit(0);
        }

        List<MarathonResult> fileResults = MarathonFileReader.result(startTimeFile, endTimeFile, minimumTime);
        try {
            MarathonResultExporter.export(outFile, fileResults);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("This is the result program!");
    }

}
