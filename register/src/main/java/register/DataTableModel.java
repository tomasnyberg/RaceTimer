package register;

import util.TimeUtils;

import javax.swing.table.AbstractTableModel;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements a TableModel and interrogates a tabular data model
 */
public class DataTableModel extends AbstractTableModel {
    private String[] columnNames = {
            "Startnummer",
            "Tid",
    };
    private List<TimeEntry> data = new ArrayList<>();

    public DataTableModel() {}

    /**
     * Whether or not a cell and be edited
     * @param rowIndex
     * @param columnIndex
     * @return returns false, you can't edit a cell
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     * A method to extract column names
     * @param col what column to work at
     * @return The name of the column
     */
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     *
     * @param c Picks what column to use
     * @return The Class of the column
     */
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /**
     *
     * @return Amount of rows
     */
    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     *
     * @return Amount of Columns
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * A method to set value of a cell
     * @param value
     * @param row
     * @param col
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        data.add(row, (TimeEntry) value);
    }

    /**
     * Delete value at a specified index, corresponding to a cell
     * @param index
     */
    public void deleteValue(int index){
        data.remove(index);
    }

    /**
     * A method which returns the value from a cell
     * @param rowIndex
     * @param columnIndex
     * @return Returns a number/value or a time
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TimeEntry mr = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return mr.getNumber();
            default:
                return TimeUtils.formatTime(mr.getTime());
        }
    }

    /**
     * A method used to get a time from a row
     * @param row
     * @return returns the time at the row
     */
    public LocalTime getTimeFromRow(int row) {
        TimeEntry mr = data.get(row);
        return mr.getTime();
    }

}
