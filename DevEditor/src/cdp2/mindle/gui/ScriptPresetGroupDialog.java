package cdp2.mindle.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cdp2.mindle.data.Script;
import cdp2.mindle.data.ScriptPreset;
import cdp2.mindle.data.ScriptPresetGroupTable;
import cdp2.mindle.data.ScriptPresetTable;
import cdp2.mindle.data.ScriptQuestion;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class ScriptPresetGroupDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ScriptPresetGroupTableModel tableModel = new ScriptPresetGroupTableModel();
	private JTable table;
	private List<ScriptPresetGroupTable> data;
	private int curRow;
	/**
	 * Create the dialog.
	 */
	public ScriptPresetGroupDialog(int row) {
		this.curRow = row;
		setTitle("프리셋 그룹");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("그룹 추가");
		lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 15));
		JButton addBtn = new JButton("추가");
		addBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScriptPresetGroupTable scpgInfo = new ScriptPresetGroupTable();
				tableModel.addRow(scpgInfo);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		JButton confirmBtn = new JButton("확 인");
		confirmBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.deleteRow();	
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(addBtn)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(deleteBtn))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(168)
							.addComponent(confirmBtn)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(addBtn)
						.addComponent(deleteBtn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(confirmBtn)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
			table = new JTable();
			table.setBorder(new LineBorder(new Color(0, 0, 0)));
			table.setModel(tableModel);
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(0).setMaxWidth(40);
			table.getColumnModel().getColumn(3).setPreferredWidth(50);
			table.getColumnModel().getColumn(3).setMaxWidth(50);
			table.setFont(new Font("Gulim", Font.PLAIN, 15));
			scrollPane.setViewportView(table);
		
		
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data = tableModel.getData();
				dispose();
			}
		});
		
		contentPanel.setLayout(gl_contentPanel);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public List<ScriptPresetGroupTable> getData() {
		return data;
	}
	
	public int getRow() {
		return curRow;
	}
}
