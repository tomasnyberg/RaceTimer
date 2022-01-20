package register;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class Program {

    public static void main(String[] args)  {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        /*
         * Vi måste göra testfall för registreringsprogrammet!
         *
         * Vi borde dela upp programmet i model / view.
         * Modellen kan testas med junit. Behöver man testa GUI:t? Kan man ens göra det?
         */

        JTextField input = new JTextField("Startnummer");
        JButton button = new JButton("Registrera");
        try {
            Files.createFile(Paths.get("time.txt"));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        button.addActionListener(e -> {
            LocalTime lt = LocalTime.now();
                String string = input.getText() + ";" + lt.format(DateTimeFormatter.ofPattern("HH.mm.ss")) + "\n";
            try {
                Files.write(Paths.get("time.txt"), Collections.singleton(string), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // Hur visar vi tiden i GUI:t!?

        });
        frame.add(panel);
        panel.add(input);
        panel.add(button);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
