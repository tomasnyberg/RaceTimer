package result;

import result.marathon.MarathonResultExporter;
import result.marathon.MarathonResultSorter;
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
        String startTimeFile = "input/starttider.txt";
        String endTimeFile = "input/maltider.txt";
        String outFile = "output/resultatFil.txt";
        String minimumTime = "00:00:00";
        boolean shouldSort = true;

        try {
            minimumTime = args[0];
            shouldSort = args[1].equals("true");
            System.out.println(minimumTime);
            LocalTime.parse(minimumTime);
        } catch (Exception e) {
            System.out.println("Invalid minimum time format. Enter time according to [hh:mm:ss]");
            System.exit(0);
        }

        List<MarathonResult> fileResults = MarathonFileReader.result(startTimeFile, endTimeFile, minimumTime);
        fileResults = new MarathonResultSorter().sortResults(fileResults);
        try {
            MarathonResultExporter.export(outFile, fileResults, shouldSort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("This is the result program!");
    }

}
