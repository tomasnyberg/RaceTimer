package result;

import result.marathon.MarathonResult;
import util.FileReader;

import java.util.List;


/**
 * The main program. TODO!
 */
public class ResultProgram {

    public static void main(String[] args) {
        String startTimeFile = args[0];
        String endTimeFile = args[1];

        List<MarathonResult> resultList = FileReader.result(startTimeFile, endTimeFile);
        FileWriter.write(resultList);

        System.out.println("This is the result program!");
    }

}
