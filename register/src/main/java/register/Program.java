package register;

import result.marathon.MarathonResultRow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Program {

    public static void main(String[] args)  {
        var width = 800;
        var height = 600;

        JFrame frame = new JFrame();
        frame.setSize(width, height);
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        Font font = new Font("SansSerif", Font.BOLD, 20);

        /*
         * Vi måste göra testfall för registreringsprogrammet!
         *
         * Vi borde dela upp programmet i model / view.
         * Modellen kan testas med junit. Behöver man testa GUI:t? Kan man ens göra det?
         */

        DefaultListModel<MarathonResultRow> listModel = new DefaultListModel<>();
        MarathonTableModel tableModel = new MarathonTableModel("start");

        tableModel.setValueAt(new MarathonResultRow("1", "John", LocalTime.now(), LocalTime.now()), 0, 0);
        tableModel.setValueAt(new MarathonResultRow("12", "Jane", LocalTime.now(), LocalTime.now()), 1, 0);

        JTextField input = new JTextField(5);
        input.setFont(font);
        input.setDocument(new OnlyNumbers());
        JLabel inputLabel = new JLabel("Startnumer: ");
        inputLabel.setFont(font);
        JButton button = new JButton("Registrera");
        button.setFont(font);

        JTable resultTable = new JTable(tableModel);
        resultTable.setFillsViewportHeight(true);
        resultTable.setFont(font);
        resultTable.getTableHeader().setFont(font);
        resultTable.setRowHeight(24);
        try {
            Files.createFile(Paths.get("time.txt"));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        button.addActionListener(e -> {
            LocalTime lt = LocalTime.now();
            String startNumber = input.getText();
            String string = startNumber + ";" + lt.format(DateTimeFormatter.ofPattern("HH.mm.ss"));

            try {
                Files.write(Paths.get("time.txt"), Collections.singleton(string), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // Hur visar vi tiden i GUI:t!?
            var mr = new MarathonResultRow(startNumber, "<empty>", lt, lt);
            listModel.addElement(mr);

        });
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);
        topPanel.add(inputLabel);
        topPanel.add(input);
        topPanel.add(button);
        bottomPanel.add(resultTable.getTableHeader(), BorderLayout.PAGE_START);
        bottomPanel.add(resultTable, BorderLayout.CENTER);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static class OnlyNumbers extends PlainDocument {
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            if (Character.isDigit(str.charAt(0)) && str.charAt(0) != '0') {
                super.insertString(offset, str, attr);
            }
        }
    }
}
