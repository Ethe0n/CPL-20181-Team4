package cdp2.mindle.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;

import cdp2.mindle.core.CoreManager;
import cdp2.mindle.data.Analysis;

public class AnalysisPanel extends JPanel {
	private JTable analysisTable;
	private AnalysisTableModel model;
	
	/**
	 * Create the panel.
	 */
	public AnalysisPanel() {
		setBorder(new LineBorder(Color.GRAY));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel analysisListLabel = new JLabel("해석 목록");
		analysisListLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		
		model = new AnalysisTableModel();
		analysisTable = new JTable();
		analysisTable.setBorder(new LineBorder(Color.GRAY));
		analysisTable.setModel(model);
		analysisTable.getColumnModel().getColumn(0).setPreferredWidth(39);
		analysisTable.getColumnModel().getColumn(0).setMaxWidth(60);
		analysisTable.getColumnModel().getColumn(1).setPreferredWidth(40);
		analysisTable.getColumnModel().getColumn(1).setMaxWidth(40);
		analysisTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		analysisTable.getColumnModel().getColumn(2).setMaxWidth(1000);
		analysisTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		analysisTable.getColumnModel().getColumn(3).setMaxWidth(200);
		analysisTable.getColumnModel().getColumn(4).setPreferredWidth(40);
		analysisTable.getColumnModel().getColumn(4).setMaxWidth(40);
		analysisTable.getColumn("평가항목").setCellRenderer(new ButtonRenderer());
        analysisTable.getColumn("평가항목").setCellEditor(new ButtonEditor(new JCheckBox()));
        analysisTable.getColumn("삭제").setCellRenderer(new ButtonRenderer());
        analysisTable.getColumn("삭제").setCellEditor(new RemoveButton(new JCheckBox(), model));
		analysisTable.setFont(new Font("굴림", Font.PLAIN, 12));
		analysisTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				model.update();
			}
		});
		analysisTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				model.update();
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				model.update();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				model.update();
			}
		});
		analysisTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				model.update();
			}
		});
		
		scrollPane.setViewportView(analysisTable);
		
		JButton addButton = new JButton("+");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.addRow(new Analysis());
				model.update();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
							.addGap(25))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(analysisListLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(addButton))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(analysisListLabel)
						.addComponent(addButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	class ButtonEditor extends DefaultCellEditor {

	    protected JButton button;
	    private String label;
	    private boolean isPushed;
	    private int rowIdx;
	    
	    public ButtonEditor(JCheckBox checkBox) {
	        super(checkBox);
	        button = new JButton();
	        button.setOpaque(true);
	        button.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                fireEditingStopped();
	            }
	        });
	    }

	    public void addActionListener(ActionListener e)
	    {
	    	button.addActionListener(e);
	    }
	    
	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int row, int column) {
	        if (isSelected) {
	            button.setForeground(table.getSelectionForeground());
	            button.setBackground(table.getSelectionBackground());
	        } else {
	            button.setForeground(table.getForeground());
	            button.setBackground(table.getBackground());
	        }
	        label = (value == null) ? "" : value.toString();
	        button.setText(label);
	        isPushed = true;
	        rowIdx = row;
	        return button;
	    }

	    @Override
	    public Object getCellEditorValue() {
	        if (isPushed) {
	        	AnalysisComponentDialog dialog = new AnalysisComponentDialog(model, rowIdx);
	        	dialog.setLocationRelativeTo(null);
	        }
	        isPushed = false;
	        return label;
	    }

	    @Override
	    public boolean stopCellEditing() {
	        isPushed = false;
	        return super.stopCellEditing();
	    }
	}
}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class RemoveButton extends DefaultCellEditor
{
	protected JButton button;
	private String label;
	private boolean isPushed;
	int selectedRow;
	AnalysisTableModel model;
	
	public RemoveButton(JCheckBox checkBox, AnalysisTableModel model) {
        super(checkBox);
        this.model = model;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public void addActionListener(ActionListener e)
    {
    	button.addActionListener(e);
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        selectedRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
        	if (model.getRowCount() >= 1 && selectedRow < model.getRowCount()) {
        		model.deleteRow(selectedRow);
        	}
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}

