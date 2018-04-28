package cdp2.mindle.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cdp2.mindle.data.Script;
import cdp2.mindle.data.ScriptQuestion;

public class ScriptQuestionTableModel extends AbstractTableModel {
	
	private List<ScriptQuestion> data;
	private List<String> columnNames;
	boolean[] columnEditables = new boolean[] { false, true, true };

	public ScriptQuestionTableModel() {
		data = new ArrayList<ScriptQuestion>();
		columnNames = createColumnNames();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		default:
			return boolean.class;
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
			return data.get(rowIndex).getQuestion();
		case 2:
			return data.get(rowIndex).getSelected();
		default:
			return null;
		}
	}

	public void addRow(ScriptQuestion scInfo) {
		data.add(scInfo);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void deleteRow(int row) {
		data.remove(row);
		fireTableDataChanged();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
			data.get(rowIndex).setQuestion(aValue != null ? aValue.toString() : null);
			break;
		case 2:
			data.get(rowIndex).setSelected((Boolean) aValue);
			break;
		default:
			break;
		}
	}
	
	public List<ScriptQuestion> getData() {
		return data;
	}
	
	private ArrayList<String> createColumnNames() {
		ArrayList<String> names = new ArrayList<String>();

		names.add("No.");
		names.add("질문");
		names.add("선택");

		return names;
	}
}