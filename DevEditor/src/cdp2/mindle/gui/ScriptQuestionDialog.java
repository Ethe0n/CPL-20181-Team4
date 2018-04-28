package cdp2.mindle.gui;

import javax.swing.JDialog;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cdp2.mindle.data.ExtensionInformation;
import cdp2.mindle.data.Script;
import cdp2.mindle.data.ScriptQuestion;
import cdp2.mindle.data.ScriptQuestionTable;

import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ScriptQuestionDialog extends JDialog{
	
	private JTextField input_ID;
	private JTextField input_Data;
	private JTextField input_MinAns;
	private JTextField input_MaxAns;
	private JTable table;
	private JTextField input_PresetID;
	private JTextField input_NumAns;
	private JTextField input_MinLen;
	private JTextField input_MaxLen;
	
	private int curMethod = 0;
	
	final ScriptQuestionTableModel tableModel = new ScriptQuestionTableModel();
	
	public ScriptQuestionDialog() {
		
		setTitle("질문");
		
		setBounds(100, 100, 450, 536);
		
		JLabel lblId = new JLabel("ID");
		
		JLabel label = new JLabel("데이터");
		label.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		input_ID = new JTextField();
		input_ID.setColumns(10);
		
		input_Data = new JTextField();
		input_Data.setColumns(10);
		
		Container Cntnr = getContentPane(); 
        ButtonGroup BtnGrp = new ButtonGroup(); 
		
		JToggleButton customOption = new JToggleButton("직접 제공");
		customOption.setFont(new Font("Gulim", Font.PLAIN, 15));
		customOption.setSelected(true);
		
		JToggleButton presetOption = new JToggleButton("프리셋");
		presetOption.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JToggleButton subjectiveOption = new JToggleButton("주관식");
		subjectiveOption.setFont(new Font("굴림", Font.PLAIN, 15));
		
		BtnGrp.add(customOption);
		BtnGrp.add(presetOption);
		BtnGrp.add(subjectiveOption);
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		JButton confirmBtn = new JButton("확 인");
		confirmBtn.setFont(new Font("Gulim", Font.PLAIN, 15));	
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(184)
					.addComponent(confirmBtn)
					.addContainerGap(183, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(input_Data, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
								.addComponent(input_ID, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(customOption)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(presetOption, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(subjectiveOption, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(59))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(input_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(input_Data, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(presetOption)
						.addComponent(subjectiveOption)
						.addComponent(customOption))
					.addGap(18)
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(confirmBtn)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 404, 308);
		layeredPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("필수 답변 개수");
		lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("최대 답변 개수");
		lblNewLabel_1.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		input_MinAns = new JTextField();
		input_MinAns.setColumns(10);
		
		input_MaxAns = new JTextField();
		input_MaxAns.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("선택지");
		lblNewLabel_2.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JButton addBtn = new JButton("추가");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScriptQuestionTable scqInfo = new ScriptQuestionTable();
				tableModel.addRow(scqInfo);
			}
		});
		addBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(10000000);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setMaxWidth(1000);
		table.setFont(new Font("Gulim", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.deleteRow();	
			}
		});
		deleteBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(14)
							.addComponent(lblNewLabel_2)
							.addGap(14)
							.addComponent(addBtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(deleteBtn))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(12)
									.addComponent(lblNewLabel))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(14)
									.addComponent(lblNewLabel_1)))
							.addGap(4)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(input_MaxAns, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
								.addComponent(input_MinAns, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addComponent(input_MinAns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(input_MaxAns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(addBtn)
							.addComponent(deleteBtn)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 404, 308);
		layeredPane.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		
		input_PresetID = new JTextField();
		input_PresetID.setColumns(10);
		
		JButton searchBtn = new JButton("검색");
		searchBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JCheckBox checkOverride = new JCheckBox("오버라이드 여부");
		checkOverride.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JLabel num_Ans = new JLabel("답변 개수");
		
		input_NumAns = new JTextField();
		input_NumAns.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(checkOverride)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(input_PresetID, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchBtn))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(num_Ans)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(input_NumAns, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(input_PresetID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(searchBtn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(checkOverride)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(num_Ans)
						.addComponent(input_NumAns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 404, 308);
		layeredPane.add(panel_2);
		
		JLabel lblNewLabel_5 = new JLabel("최소 답변 길이");
		lblNewLabel_5.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		input_MinLen = new JTextField();
		input_MinLen.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("최대 답변 길이");
		lblNewLabel_6.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		input_MaxLen = new JTextField();
		input_MaxLen.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(input_MinLen, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_6)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(input_MaxLen, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(input_MinLen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(input_MaxLen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(99, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		getContentPane().setLayout(groupLayout);
		
		panel.setVisible(true);
		panel_1.setVisible(false);
		panel_2.setVisible(false);
		
		customOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				curMethod = 0;
			}
		});
		presetOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(true);
				panel_2.setVisible(false);
				curMethod = 1;
			}
		});
		subjectiveOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(false);
				panel_2.setVisible(true);
				curMethod = 2;
			}
		});
		
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = input_ID.getText();
				String data = input_Data.getText();
				Script script = new Script();
				script.setCommand("질문");
				
				ScriptQuestion scriptQuestion = new ScriptQuestion();
				scriptQuestion.setCurMethod(curMethod);     // 0 : 직접제공, 1 : 프리셋, 2 : 주관식
				scriptQuestion.setId(id);
				scriptQuestion.setQuestion(data);
				
				switch(curMethod) {
				case 0 : 
					String minAns = input_MinAns.getText();
					String maxAns = input_MaxAns.getText();
					scriptQuestion.setMinAns(Integer.parseInt(minAns));
					scriptQuestion.setMaxAns(Integer.parseInt(maxAns));
					scriptQuestion.setCustom(tableModel.getData());
					break;
				case 1 :
					String presetId = input_PresetID.getText();
					boolean checkOver = checkOverride.isSelected();
					int numAns = Integer.parseInt(input_NumAns.getText());
					scriptQuestion.setPresetId(presetId);
					scriptQuestion.setCheckOverride(checkOver);
					scriptQuestion.setNumAns(numAns);
					break;
				case 2:
					int minLen = Integer.parseInt(input_MinLen.getText());
					int maxLen = Integer.parseInt(input_MaxLen.getText());
					scriptQuestion.setMinLen(minLen);
					scriptQuestion.setMaxLen(maxLen);
					break;
				}
				   
				script.setObject(scriptQuestion);
				
				ScriptPanel.addRow(script);
				
				dispose();
			}
		});
				
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}


