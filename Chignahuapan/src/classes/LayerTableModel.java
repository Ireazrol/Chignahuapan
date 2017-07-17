package classes;

import javax.swing.table.AbstractTableModel;

public class LayerTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	int NUM_LAYERS = 4;
	private String[] columnNames = { "Name", "Layer Order" };

	private Object[][] data = new Object[NUM_LAYERS][columnNames.length];

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

}
