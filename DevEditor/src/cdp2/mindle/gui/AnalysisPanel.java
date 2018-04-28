package cdp2.mindle.gui;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.LineBorder;
import java.awt.Color;

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
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
							.addGap(25))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(analysisListLabel)
							.addContainerGap(377, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(analysisListLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		
		analysisTable = new JTable();
		analysisTable.setBorder(new LineBorder(Color.GRAY));
		analysisTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"No.", "\uBCC0\uC218", "\uC218\uC815"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		analysisTable.getColumnModel().getColumn(0).setPreferredWidth(39);
		analysisTable.getColumnModel().getColumn(0).setMaxWidth(60);
		analysisTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		analysisTable.getColumnModel().getColumn(2).setMaxWidth(90);
		analysisTable.getColumn("수정").setCellRenderer(new ButtonRenderer());
        analysisTable.getColumn("수정").setCellEditor(new ButtonEditor(new JCheckBox()));
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
//            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        	AnalysisPopupDialog dialog = new AnalysisPopupDialog();
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
