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

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class ScriptQuestionDialog extends JDialog{
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	public ScriptQuestionDialog() {
		
		setBounds(100, 100, 450, 394);
		getContentPane().setLayout(new BorderLayout());
		
		JLabel lblId = new JLabel("ID");
		
		JLabel label = new JLabel("데이터");
		label.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
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
		
		JButton btnNewButton_1 = new JButton("확 인");
		btnNewButton_1.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(customOption)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(presetOption, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(subjectiveOption, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(59))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(181)
					.addComponent(btnNewButton_1)
					.addContainerGap(186, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(presetOption)
						.addComponent(subjectiveOption)
						.addComponent(customOption))
					.addGap(18)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 404, 179);
		layeredPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("필수 답변 개수");
		lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("최대 답변 개수");
		lblNewLabel_1.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("선택지");
		lblNewLabel_2.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JButton btnNewButton = new JButton("추가");
		btnNewButton.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No.", "\uC9C8\uBB38", "\uC120\uD0DD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(10000000);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setMaxWidth(1000);
		table.setFont(new Font("Gulim", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(12)
					.addComponent(lblNewLabel)
					.addGap(6)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addComponent(lblNewLabel_1)
					.addGap(6)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addComponent(lblNewLabel_2)
					.addGap(14)
					.addComponent(btnNewButton))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_2))
						.addComponent(btnNewButton))
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 404, 179);
		layeredPane.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("검색");
		btnNewButton_2.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("오버라이드 여부");
		chckbxNewCheckBox.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JLabel lblNewLabel_4 = new JLabel("답변 개수");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxNewCheckBox)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(btnNewButton_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxNewCheckBox)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 404, 179);
		layeredPane.add(panel_2);
		
		JLabel lblNewLabel_5 = new JLabel("최소 답변 길이");
		lblNewLabel_5.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("최대 답변 길이");
		lblNewLabel_6.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_6)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
			}
		});
		presetOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(true);
				panel_2.setVisible(false);
			}
		});
		subjectiveOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}


