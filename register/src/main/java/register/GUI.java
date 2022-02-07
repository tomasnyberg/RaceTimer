package register;

import result.TimeEntry;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class GUI {

  public static void run() {
    // General interface settings
    int width = 800;
    int height = 600;
    String filePath = "input/result.txt";
    FileNameExtensionFilter filter = new FileNameExtensionFilter("text files ", "txt");

    JFrame frame = new JFrame();
    frame.setSize(width, height);

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(filter);
    int fileResult = fileChooser.showSaveDialog(frame);

    if (fileResult == JFileChooser.APPROVE_OPTION) {
      if (fileChooser.getSelectedFile().getAbsolutePath().contains(".txt")) {
        filePath = fileChooser.getSelectedFile().toString();
      } else {
        filePath = fileChooser.getSelectedFile().toString() + ".txt";
      }
    }

    JPanel topPanel = new JPanel();
    topPanel.setBackground(Color.LIGHT_GRAY);
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new BorderLayout());
    Font font = new Font("SansSerif", Font.BOLD, 30);

    // Register form and button
    JTextField input = new JTextField(5);
    input.setFont(font);
    JLabel inputLabel = new JLabel("Startnummer: ");
    inputLabel.setFont(font);
    JButton button = new JButton("Registrera");
    button.setFont(font);
    JButton clearEmpty = new JButton("Ta bort tom"); // to remove row with no start number
    clearEmpty.setFont(font);
    clearEmpty.setBackground(Color.RED);
    clearEmpty.setForeground(Color.WHITE);
    clearEmpty.setVisible(false);
    // Marathon table & JTable settings
    MarathonTableModel tableModel = new MarathonTableModel();
    JTable resultTable = new JTable(tableModel);
    resultTable.setFillsViewportHeight(true);
    resultTable.setFont(font);
    resultTable.getTableHeader().setFont(font);
    resultTable.setRowHeight(40);

    // Create file if not exists
    try {
      Files.createFile(Paths.get(filePath));
    } catch (IOException e2) {
      System.out.print("File already exists");
    }

    // Trigger save when clicking on register button
    String finalFile = filePath;
    button.addActionListener(
        e -> {
          LocalTime lt = LocalTime.now();
          String time = lt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
          String startNumber = input.getText();

          // Check validity of input

          if (tableModel.getRowCount() > 0
              && tableModel
                  .getValueAt(0, 0)
                  .equals("Saknar startnummer")
              && startNumber.charAt(0) != '0'
              && startNumber.matches("[0-9]+")) {
            tableModel.setValueAt(
                new TimeEntry(startNumber, tableModel.getTimeFromRow(0)), 1, 1);
            tableModel.deleteValue(0);
            String stampedTime = tableModel.getTimeFromRow(0).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String stringToWrite = startNumber + "; " + stampedTime;
            writeToFile(finalFile, stringToWrite);
            updateTableView(resultTable, input);
          } else if (!startNumber.isEmpty() // if start number not empty, and start number is a number between 0-9 that does not start with 0
              && startNumber.charAt(0) != '0'
              && startNumber.matches("[0-9]+")) {
            String string = startNumber + "; " + time;

            writeToFile(finalFile, string);

            // Update view with new row
            tableModel.setValueAt(new TimeEntry(startNumber, lt), 0, 0);
            updateTableView(resultTable, input);
          } else if (startNumber.isEmpty()) {
            String string = "; " + time;
            System.out.println(startNumber);
            startNumber = "Saknar startnummer";
            tableModel.setValueAt(new TimeEntry(startNumber, lt), 0, 0);
            clearEmpty.setVisible(true);
            updateTableView(resultTable, input);

          }
        });

    // Trigger deletion of row without start number if latest row has no start number
    clearEmpty.addActionListener(e -> {
      if (tableModel.getRowCount() > 0
              && tableModel
              .getValueAt(0, 0)
              .equals("Saknar startnummer")) {
        clearEmpty.setVisible(false);
        tableModel.deleteValue(0);
        updateTableView(resultTable, input);
      }
    });

    frame.add(topPanel, BorderLayout.NORTH);
    frame.add(bottomPanel, BorderLayout.CENTER);
    topPanel.add(inputLabel);
    topPanel.add(input);
    topPanel.add(button);
    topPanel.add(clearEmpty);
    bottomPanel.add(resultTable.getTableHeader(), BorderLayout.PAGE_START);
    bottomPanel.add(resultTable, BorderLayout.CENTER);

    // Defaults
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.getRootPane().setDefaultButton(button);
  }

  private static void updateTableView(JTable resultTable, JTextField input) {
    resultTable.repaint();
    input.setText("");
    input.requestFocusInWindow();
  }

  private static void writeToFile(String filepath, String stringToSave) {
    try {
      Files.write(
              Paths.get(filepath),
              Collections.singleton(stringToSave),
              StandardCharsets.UTF_8,
              StandardOpenOption.APPEND);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }
}
