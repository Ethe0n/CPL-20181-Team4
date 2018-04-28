package cdp2.mindle.gui;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import cdp2.mindle.data.Script;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ScriptNoticeDialog extends JDialog{
	
	private final JPanel contentPanel = new JPanel();
	
	private JTextField input_ID;
	private JTextField input_Notice;
	
	public ScriptNoticeDialog() {
		
		JLabel lblId = new JLabel("ID");
		
		input_ID = new JTextField();
		input_ID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("안내문");
		lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		input_Notice = new JTextField();
		input_Notice.setColumns(10);
		
		JButton confirmBtn = new JButton("확 인");
		confirmBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = input_ID.getText();
				String notice = input_Notice.getText();
				Script script = new Script();
				script.setCommand("안내문");
				script.appendData(id);
				script.appendData(notice);
				ScriptPanel.addRow(script);
				
				dispose();
			}
		});
		
		
		setBounds(100, 100, 450, 285);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(input_Notice, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
								.addComponent(input_ID, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(183)
							.addComponent(confirmBtn)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(input_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(input_Notice, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(confirmBtn)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
