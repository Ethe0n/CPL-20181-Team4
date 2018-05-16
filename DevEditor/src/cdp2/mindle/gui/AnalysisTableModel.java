package cdp2.mindle.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cdp2.mindle.core.CoreManager;
import cdp2.mindle.data.Analysis;
import cdp2.mindle.data.AnalysisComponent;

public class AnalysisTableModel extends AbstractTableModel
{
	private List<Analysis> data;
	private List<String> columnNames;
	boolean[] columnEditables = new boolean[] { false, true, false, true, true, true };
	
	public AnalysisTableModel() {
		data = new ArrayList<Analysis>();
		columnNames = createColumnNames();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return Integer.class;
		case 2:
			return String.class;
		case 3 :
			return Object.class;
		case 4 :
			return Object.class;
		default :
			return null;
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
			return data.get(rowIndex).getNumber();
		case 2:
			return data.get(rowIndex).toString();
		default :
			return null;
		}
	}

	public void addRow(Analysis comp) {
		comp.setNumber(data.size());
		data.add(comp);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void deleteRow(int rowIndex) {
		if (rowIndex < data.size()) {
			data.remove(rowIndex);
		}

		fireTableDataChanged();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
			data.get(rowIndex).setNumber(aValue != null ? (int) aValue : 0);
			break;
		case 2:
			data.get(rowIndex).setName(aValue != null ? aValue.toString() : null);
			break;
		case 3:
			//data.get(rowIndex).setStatement(aValue != null ? aValue.toString() : null);
			break;
		case 4 :
			//data.get(rowIndex).setFeedback(aValue != null ? aValue.toString() : null);
		default:
			break;
		}
	}

	public void update()
	{
		CoreManager.getInstance().setAnalysis(data);
	}
	
	private ArrayList<String> createColumnNames() {
		ArrayList<String> names = new ArrayList<String>();
		
		names.add("No.");
		names.add("번호");
		names.add("이름");
		names.add("평가항목");
		names.add("삭제");
		
		return names;
	}	
}
