package result.Varvlopp;

import java.util.List;
import java.util.ArrayList;

public class VarvLoppResult {
    private List<VarvLoppDriver> drivers = new ArrayList<>();

    // The only public method visible, reads in all the files and generates the result txt file.
    // Readds from args if they exist for specifying where files are, otherwise from config
    // Takes in a config class (TODO) where we get the file paths etc.
    public void generateResult(){

    }

    // both read start times and end times read the times, so we have a method for the common things
    // returns something that the other methods can use to fill in the info for the drivers
    private void readTimes(){

    }

    // Reads starttimes from a starttimefile, and sets the start times for the respective driver number
    // If we have not seen this drivernumber so far, we create a new driver
    public void readStartTimes(String startTimeFile){
        //TODO
    }

    // Reads endtimes from a file, and adds the end times for the respective driver number
    // If we have not seen this drivernumber so far, we create a new driver
    public void readEndTimes(String endTimeFile){
        //TODO
    }

    // Reads endtimes from a file, and adds the end times for the respective driver number
    // If we have not seen this drivernumber so far, we create a new driver
    public void readNames(String nameFile){
        //TODO
    }

}
