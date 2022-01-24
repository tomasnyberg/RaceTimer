package result;

import result.marathon.MarathonResultExporter;
import result.marathon.MarathonResult;
import result.marathon.MarathonFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import util.FileWriter;


/**
 * The main program. TODO!
 */
public class ResultProgram {

    public static void main(String[] args) {
        String startTimeFile = "../starttider.txt";
        String endTimeFile = "../maltider.txt";
        String outFile = "../resultatFil.txt";

        List<MarathonResult> fileResults = MarathonFileReader.result(startTimeFile, endTimeFile);
        try {
            MarathonResultExporter.export(outFile, fileResults);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("This is the result program!");
    }

}
