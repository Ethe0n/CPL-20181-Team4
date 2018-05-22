package cdp2.mindle.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cdp2.mindle.core.CoreManager;
import cdp2.mindle.data.ExtensionInformation;
import cdp2.mindle.data.Script;

public class ScriptTableModel extends AbstractTableModel {
	
	private List<Script> data;
	private List<String> columnNames;
	boolean[] columnEditables = new boolean[] { false, true, true, true, true, true };

	public ScriptTableModel() {
		data = new ArrayList<Script>();
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
			return Object.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return columnEditables[column];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
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
			return data.get(rowIndex).getCommand();
		case 2:
			return data.get(rowIndex).getData();
		default:
			return null;
		}
	}

	public void addRow(Script scInfo) {
		data.add(scInfo);
		CoreManager.getInstance().setScriptList(data);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void deleteRow(int row) {
		data.remove(row);
		CoreManager.getInstance().setScriptList(data);
		fireTableDataChanged();
	}
	
	public void upperRow(int row) {
		if(row == 0) return;
		Script temp = new Script();
		temp = data.get(row);
		data.set(row, data.get(row - 1));
		data.set(row - 1, temp);
		CoreManager.getInstance().setScriptList(data);
		fireTableDataChanged();
	}
	
	public void lowerRow(int row) {
		if(row == data.size() - 1) return; 
		Script temp = new Script();
		temp = data.get(row);
		data.set(row, data.get(row + 1));
		data.set(row + 1, temp);
		CoreManager.getInstance().setScriptList(data);
		fireTableDataChanged();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
			data.get(rowIndex).setCommand(aValue != null ? aValue.toString() : null);
			break;
		case 2:
			data.get(rowIndex).setObject(aValue != null ? aValue.toString() : null);
			break;
		case 3:
			break;
		default:
			break;
		}
	}
	
	public List<Script> getData() {
		return data;
	}
	
	private ArrayList<String> createColumnNames() {
		ArrayList<String> names = new ArrayList<String>();

		names.add("No.");
		names.add("명령어");
		names.add("데이터");
		names.add("위로");
		names.add("아래로");
		names.add("삭제");

		return names;
	}
}
