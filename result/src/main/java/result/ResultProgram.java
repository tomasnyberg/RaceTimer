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
        String startTimeFile = "../textfiles/starttider.txt";
        String endTimeFile = "../textfiles/maltider.txt";
        String outFile = "../textfiles/resultatFil.txt";

        List<MarathonResult> fileResults = MarathonFileReader.result(startTimeFile, endTimeFile);
        try {
            MarathonResultExporter.export(outFile, fileResults);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("This is the result program!");
    }

}
