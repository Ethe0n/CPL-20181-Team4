package cdp2.mindle.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import cdp2.mindle.data.Script;

public class ScriptPanel extends JPanel
{
	private JTable table_1;
	private ScriptTableModel tableModel = new ScriptTableModel();
	
	private ScriptPanel thisPanel;
	
	public ScriptPanel()
	{
		thisPanel = this;
		setBorder(new LineBorder(Color.GRAY));

		setBackground(new Color(250, 235, 215));
		
		JButton noticeBtn = new JButton("안내문");
		noticeBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		noticeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptNoticeDialog dialog = new ScriptNoticeDialog(thisPanel);
//				AnalysisPopupDialog dialog = new AnalysisPopupDialog();
				dialog.setLocationRelativeTo(null);
			}
		});
		
		JButton questionBtn = new JButton("질문");
		questionBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		questionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptQuestionDialog dialog = new ScriptQuestionDialog(thisPanel);
				dialog.setLocationRelativeTo(null);
			}
		});
		
		JButton presetBtn = new JButton("프리셋");
		presetBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		presetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptPresetDialog dialog = new ScriptPresetDialog(thisPanel);
				dialog.setLocationRelativeTo(null);
			}
		});
		
		JButton commentBtn = new JButton("주석");
		commentBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		commentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptCommentDialog dialog = new ScriptCommentDialog(thisPanel);
				dialog.setLocationRelativeTo(null);
			}
		});
		
		JLabel label = new JLabel("추가");
		label.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(commentBtn, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(noticeBtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(questionBtn, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(presetBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
					.addGap(23))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(commentBtn)
						.addComponent(noticeBtn)
						.addComponent(questionBtn)
						.addComponent(presetBtn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table_1 = new JTable();
		table_1.setBackground(Color.WHITE);
		table_1.setBorder(new LineBorder(Color.GRAY));
		table_1.setFont(new Font("굴림", Font.PLAIN, 12));
		table_1.setModel(tableModel);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(40);
		table_1.getColumnModel().getColumn(0).setMaxWidth(40);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(1).setMaxWidth(60);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(297);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(3).setMaxWidth(60);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(4).setMaxWidth(60);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(5).setMaxWidth(60);
		table_1.getColumn("삭제").setCellRenderer(new ScriptButtonRenderer());
        table_1.getColumn("삭제").setCellEditor(new ScriptButtonEditor(new JCheckBox(), tableModel));
        table_1.getColumn("위로").setCellRenderer(new ScriptButtonRenderer());
        table_1.getColumn("위로").setCellEditor(new ScriptButtonEditor(new JCheckBox(), tableModel));
        table_1.getColumn("아래로").setCellRenderer(new ScriptButtonRenderer());
        table_1.getColumn("아래로").setCellEditor(new ScriptButtonEditor(new JCheckBox(), tableModel));
		
		scrollPane.setViewportView(table_1);
		setLayout(groupLayout);
	}
	
	public void deleteRow(int row) {
		tableModel.deleteRow(row);
	}
	
	public void addRow(Script scInfo) {
		tableModel.addRow(scInfo);
	}
}

class ScriptButtonRenderer extends JButton implements TableCellRenderer {

    public ScriptButtonRenderer() {
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
        
        switch(column) {
        case 3 : setText((value == null) ? "↑" : value.toString()); break;
        case 4 : setText((value == null) ? "↓" : value.toString()); break;
        case 5 : setText((value == null) ? "x" : value.toString()); break;
        }
        return this;
    }
}

class ScriptButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private int row;
    private int col;
    
    private ScriptTableModel tableModel;

    public ScriptButtonEditor(JCheckBox checkBox, ScriptTableModel tableModel) {
        super(checkBox);
        
        this.tableModel = tableModel;
        
        button = new JButton();
        button.setOpaque(true);
        
        switch(col) {
        case 3 : button.setText("↑"); break;
        case 4 : button.setText("↓"); break;
        case 5 : button.setText("x"); break;
        }
        
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
        
        switch(column) {
        case 3 : label = ((value == null) ? "↑" : value.toString()); break;
        case 4 : label = ((value == null) ? "↓" : value.toString()); break;
        case 5 : label = ((value == null) ? "x" : value.toString()); break;
        }
        button.setText(label);
        this.row = row;
        this.col = column;
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
//            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        	switch(col) {
        	case 3 : 
        		tableModel.upperRow(row);
        		break;
        	case 4 : 
        		tableModel.lowerRow(row);
        		break;
        	case 5 :
        		tableModel.deleteRow(row);
        		break;
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
