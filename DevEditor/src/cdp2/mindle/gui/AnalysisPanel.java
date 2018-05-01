package cdp2.mindle.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class AnalysisPanel extends JPanel {
	private JTable analysisTable;

	/**
	 * Create the panel.
	 */
	public AnalysisPanel() {
		setBorder(new LineBorder(Color.GRAY));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel analysisListLabel = new JLabel("해석 목록");
		analysisListLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JButton button = new JButton("+");
		
		JButton button_1 = new JButton("-");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
							.addGap(25))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(analysisListLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(269, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(analysisListLabel)
						.addComponent(button)
						.addComponent(button_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		
		analysisTable = new JTable();
		analysisTable.setBorder(new LineBorder(Color.GRAY));
		analysisTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"No.", "\uBC88\uD638", "\uC774\uB984", "\uD3C9\uAC00\uD56D\uBAA9", "\uC0AD\uC81C"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
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
		analysisTable.getColumn("삭제").setCellRenderer(new ButtonRenderer());
        analysisTable.getColumn("삭제").setCellEditor(new ButtonEditor(new JCheckBox()));
		analysisTable.setFont(new Font("굴림", Font.PLAIN, 12));
		scrollPane.setViewportView(analysisTable);
		setLayout(groupLayout);
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

class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;

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
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
        	AnalysisComponentDialog dialog = new AnalysisComponentDialog();
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

