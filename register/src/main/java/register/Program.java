package register;

import result.TimeEntry;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class Program {

    public static void main(String[] args) {
        // General interface settings
        GUI.run();
    }
}