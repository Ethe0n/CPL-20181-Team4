package cdp2.mindle.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import cdp2.mindle.data.Script;
import cdp2.mindle.data.ScriptPreset;
import cdp2.mindle.data.ScriptPresetTable;
import cdp2.mindle.data.ScriptQuestionTable;

public class ScriptPresetDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ScriptPresetTableModel tableModel = new ScriptPresetTableModel();
	private JTextField input_ID;
	private JTextField input_MinAns;
	private JTextField input_MaxAns;
	private JTable table;

	private ScriptPanel panel;
	
	public ScriptPresetDialog(ScriptPanel panel) {
		
		setTitle("프리셋");
		
		this.panel = panel;
		
		setBounds(100, 100, 450, 365);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblId = new JLabel("ID");
		
		JLabel label = new JLabel("최소 답변 개수");
		label.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JLabel lblNewLabel = new JLabel("최대 답변 개수");
		lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		input_ID = new JTextField();
		input_ID.setColumns(10);
		
		input_MinAns = new JTextField();
		input_MinAns.setColumns(10);
		
		input_MaxAns = new JTextField();
		input_MaxAns.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("선택지");
		lblNewLabel_1.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JButton addBtn = new JButton("추가");
		addBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScriptPresetTable scpInfo = new ScriptPresetTable();
				tableModel.addRow(scpInfo);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton confirmBtn = new JButton("확 인");
		confirmBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.deleteRow();	
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblId)
									.addGap(94)
									.addComponent(input_ID, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(input_MinAns, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(input_MaxAns, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(addBtn)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(deleteBtn))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(176)
							.addComponent(confirmBtn)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(input_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(input_MinAns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(input_MaxAns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(addBtn)
						.addComponent(lblNewLabel_1)
						.addComponent(deleteBtn))
					.addGap(14)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(confirmBtn))
		);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setMaxWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setMaxWidth(50);
		table.getColumn("문항").setCellRenderer(new ScriptPresetButtonRenderer());
        table.getColumn("문항").setCellEditor(new ScriptPresetButtonEditor(new JCheckBox(), tableModel));
		table.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = input_ID.getText();
				int minAns = Integer.parseInt(input_MinAns.getText());
				int maxAns = Integer.parseInt(input_MaxAns.getText());
				
				Script script = new Script();
				script.setCommand("프리셋");
				
				ScriptPreset scriptPreset = new ScriptPreset();
				scriptPreset.setId(id);
				scriptPreset.setMinAns(minAns);
				scriptPreset.setMaxAns(maxAns);
			
				scriptPreset.setPresetTable(tableModel.getData());
				
				
				script.setObject(scriptPreset);
				
				panel.addRow(script);
				panel.updateManager();
				dispose();
			}
		});
		
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}

class ScriptPresetButtonRenderer extends JButton implements TableCellRenderer {
	
    public ScriptPresetButtonRenderer() {
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
        setText("+");
        return this;
    }
}

class ScriptPresetButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private int row;
    
    private ScriptPresetTableModel tableModel;
    
    public ScriptPresetButtonEditor(JCheckBox checkBox, ScriptPresetTableModel tableModel) {
        super(checkBox);
        this.tableModel = tableModel;
        
        button = new JButton();
        button.setOpaque(true);
        button.setText("+");
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
        label =  "+";
        button.setText(label);
        this.row = row;
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
//            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        	ScriptPresetGroupDialog dialog = new ScriptPresetGroupDialog(row, 1);
    		dialog.setPresetTable(tableModel);
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
