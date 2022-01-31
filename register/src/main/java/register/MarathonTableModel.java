package register;

import result.TimeEntry;
import result.marathon.MarathonResultRow;
import util.TimeUtils;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MarathonTableModel extends AbstractTableModel {
    private String[] columnNames = {
            "Startnummer",
            "Tid",
    };
    private List<TimeEntry> data = new ArrayList<>();

    public MarathonTableModel() {}

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data.add(row, (TimeEntry) value);
    }

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
}
