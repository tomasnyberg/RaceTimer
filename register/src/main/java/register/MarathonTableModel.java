package register;

import result.marathon.MarathonResultRow;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MarathonTableModel extends AbstractTableModel {
    private String type;
    private String[] columnNames = {
            "Startnumer",
            "Tid",
    };
    private List<MarathonResultRow> data = new ArrayList<>();

    public MarathonTableModel(String type) {
        this.type = type;
    }

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
        data.add(row, (MarathonResultRow) value);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MarathonResultRow mr = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return mr.getNumber();
            case 1:
                if (type.equals("start")) return mr.getStart();
                else return mr.getEnd();
        }
        return mr.getNumber();
    }
}
