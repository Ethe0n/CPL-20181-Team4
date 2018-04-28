package cdp2.mindle.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ScriptPanel extends JPanel
{
	private JTable table_1;
	public ScriptPanel()
	{
		setBorder(new LineBorder(Color.GRAY));

		setBackground(new Color(250, 235, 215));
		
		JButton noticeBtn = new JButton("안내문");
		noticeBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		noticeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptNoticeDialog dialog = new ScriptNoticeDialog();
//				AnalysisPopupDialog dialog = new AnalysisPopupDialog();
				dialog.setLocationRelativeTo(null);
			}
		});
		
		JButton questionBtn = new JButton("질문");
		questionBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		questionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptQuestionDialog dialog = new ScriptQuestionDialog();
				dialog.setLocationRelativeTo(null);
			}
		});
		
		JButton presetBtn = new JButton("프리셋");
		presetBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		presetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptPresetDialog dialog = new ScriptPresetDialog();
				dialog.setLocationRelativeTo(null);
			}
		});
		
		JButton commentBtn = new JButton("주석");
		commentBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		commentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptCommentDialog dialog = new ScriptCommentDialog();
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
		table_1.setFont(new Font("Gulim", Font.PLAIN, 15));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"No.", "\uBA85\uB839\uC5B4", "\uD130\uC774\uD130", "\uC120\uD0DD"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(40);
		table_1.getColumnModel().getColumn(0).setMaxWidth(40);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(1).setMaxWidth(60);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(297);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(40);
		table_1.getColumnModel().getColumn(3).setMaxWidth(40);
		scrollPane.setViewportView(table_1);
		setLayout(groupLayout);
	}
}
