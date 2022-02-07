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

    // Trigger save when clicking on button
    String finalFile = filePath;
    button.addActionListener(
        e -> {
          LocalTime lt = LocalTime.now();
          String time = lt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
          String startNumber = input.getText();

          // Check validity of input


          if(tableModel.getRowCount() >=1 &&  tableModel.getValueAt(0,0).equals("")){
            tableModel.setValueAt(new TimeEntry(startNumber, tableModel.getMissingValueAt(0,1)), 1, 1);
            tableModel.deleteValue(0);
            resultTable.repaint();
            input.setText("");
            input.requestFocusInWindow();
          }

          else if (!startNumber.isEmpty()
              && startNumber.charAt(0) != '0'
              && startNumber.matches("[0-9]+")) {
            String string = startNumber + "; " + time;

            try {
              Files.write(
                  Paths.get(finalFile),
                  Collections.singleton(string),
                  StandardCharsets.UTF_8,
                  StandardOpenOption.APPEND);
            } catch (IOException e1) {
              e1.printStackTrace();
            }

            // Update view with new row
            tableModel.setValueAt(new TimeEntry(startNumber, lt), 0, 0);
            resultTable.repaint();
            input.setText("");
            input.requestFocusInWindow();
          } else if(startNumber.isEmpty()){
            String string =  "; " + time;
            tableModel.setValueAt(new TimeEntry(startNumber, lt), 0, 0);
            resultTable.repaint();
            input.setText("");
            input.requestFocusInWindow();

          }


        });

    frame.add(topPanel, BorderLayout.NORTH);
    frame.add(bottomPanel, BorderLayout.CENTER);
    topPanel.add(inputLabel);
    topPanel.add(input);
    topPanel.add(button);
    bottomPanel.add(resultTable.getTableHeader(), BorderLayout.PAGE_START);
    bottomPanel.add(resultTable, BorderLayout.CENTER);

    // Defaults
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.getRootPane().setDefaultButton(button);
  }
}
