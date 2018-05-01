package cdp2.mindle.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cdp2.mindle.data.Script;
import cdp2.mindle.data.ScriptPresetGroupTable;
import cdp2.mindle.data.ScriptQuestion;
import cdp2.mindle.data.ScriptQuestionTable;

public class ScriptQuestionTableModel extends AbstractTableModel {
	
	private List<ScriptQuestionTable> data;
	private List<String> columnNames;
	boolean[] columnEditables = new boolean[] { false, true, true, true };

	public ScriptQuestionTableModel() {
		data = new ArrayList<ScriptQuestionTable>();
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
			return data.get(rowIndex).getGroup();
		case 2:
			return data.get(rowIndex).getItem();
		case 3:
			return data.get(rowIndex).getSelected();
		default:
			return null;
		}
	}

	public void addRow(ScriptQuestionTable scInfo) {
		data.add(scInfo);
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
		case 1: break;
		case 2:
			data.get(rowIndex).setItem(aValue != null ? aValue.toString() : null);
			break;	
		case 3:
			data.get(rowIndex).setSelected((Boolean) aValue);
			break;
		default:
			break;
		}
	}
	
	public void setData(List<ScriptPresetGroupTable> data, int row) {
		this.data.get(row).setGroup(data);
		
		this.data.get(row).setItem(data.toString());
		fireTableDataChanged();
	}
	
	public List<ScriptQuestionTable> getData() {
		return data;
	}
	
	private ArrayList<String> createColumnNames() {
		ArrayList<String> names = new ArrayList<String>();

		names.add("No.");
		names.add("문항");
		names.add("문항 데이터");
		names.add("선택");

		return names;
	}
}