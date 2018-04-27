package cdp2.mindle.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cdp2.mindle.data.ExtensionInformation;

public class ExtensionTableModel extends AbstractTableModel {

	private List<ExtensionInformation> data;
	private List<String> columnNames;
	boolean[] columnEditables = new boolean[] { false, true, true, true };

	public ExtensionTableModel() {
		data = new ArrayList<ExtensionInformation>();
		columnNames = createColumnNames();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		default:
			return Boolean.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return columnEditables[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return data.get(rowIndex).getKey();
		case 2:
			return data.get(rowIndex).getValue();
		case 3:
			return data.get(rowIndex).getSelected();
		default:
			return null;
		}
	}

	public void addRow(ExtensionInformation exInfo) {
		data.add(exInfo);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void deleteRow() {
		for (int rowIndex = data.size() - 1; rowIndex >= 0; rowIndex--) {
			if (data.get(rowIndex).getSelected()) {
				data.remove(rowIndex);
			}
		}

		fireTableDataChanged();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
			data.get(rowIndex).setKey(aValue != null ? aValue.toString() : null);
			break;
		case 2:
			data.get(rowIndex).setValue(aValue != null ? aValue.toString() : null);
			break;
		case 3:
			data.get(rowIndex).setSelected((Boolean) aValue);
			break;
		default:
			break;
		}
	}

	private ArrayList<String> createColumnNames() {
		ArrayList<String> names = new ArrayList<String>();

		names.add("No.");
		names.add("타입");
		names.add("정보");
		names.add("선택");

		return names;
	}
}
