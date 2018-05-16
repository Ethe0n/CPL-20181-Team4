package cdp2.mindle.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import cdp2.mindle.core.CoreManager;
import cdp2.mindle.data.AnalysisComponent;

public class AnalysisComponentTableModel extends AbstractTableModel
{
	private List<AnalysisComponent> data;
	private List<String> columnNames;
	boolean[] columnEditables = new boolean[] { false, true, true, true, true, true };
	
	public AnalysisComponentTableModel()
	{
		data = new ArrayList<AnalysisComponent>();
		columnNames = createColumnNames();
		data.add(new AnalysisComponent(0, "나머지 경우", "정상", "정상입니다"));
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
			return String.class;
		case 4 :
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
			return data.get(rowIndex).getRefValue();
		case 2:
			return data.get(rowIndex).getOperator();
		case 3:
			return data.get(rowIndex).getStatement();
		case 4 :
			return data.get(rowIndex).getFeedback();
		default:
			return null;
		}
	}
	
	public void addRow(AnalysisComponent comp) {
		data.add(data.size() - 1, comp);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void deleteRow(int rowIndex) {
		if (data.size() - 1 != rowIndex) {
			data.remove(rowIndex);
		}

		fireTableDataChanged();
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
			data.get(rowIndex).setRefValue(aValue != null ? (int) aValue : 0);
			break;
		case 2:
			break;
		case 3:
			data.get(rowIndex).setStatement(aValue != null ? aValue.toString() : null);
			break;
		case 4 :
			data.get(rowIndex).setFeedback(aValue != null ? aValue.toString() : null);
		default:
			break;
		}
	}
	
	public void setOperation(int rowIndex, String op)
	{
		if (rowIndex != data.size() - 1) {
			data.get(rowIndex).setOperator(op);
		}
		else {
			JOptionPane.showMessageDialog(null, "마지막 행의 비교열은 바꿀 수 없습니다!", "Warning",
			        JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private ArrayList<String> createColumnNames() {
		ArrayList<String> names = new ArrayList<String>();
		
		names.add("No.");
		names.add("기준 값");
		names.add("비교");
		names.add("상태");
		names.add("피드백");
		names.add("삭제");
		
		return names;
	}	
	
	public void update(int index)
	{
		CoreManager.getInstance().setAnalysisComponent(index, data);
	}
}
