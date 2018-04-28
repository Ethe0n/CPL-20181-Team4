package cdp2.mindle.gui;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import cdp2.mindle.core.CoreManager;
import cdp2.mindle.data.Script;
import cdp2.mindle.data.ScriptComment;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class ScriptCommentDialog extends JDialog{
	
	private final JPanel contentPanel = new JPanel();
	
	private JTextField textField;
	public ScriptCommentDialog() {
		setTitle("주석");
		textField = new JTextField();
		textField.setColumns(10);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JButton confirmBtn = new JButton("확 인");
		confirmBtn.setFont(new Font("Gulim", Font.PLAIN, 15));
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comment = textField.getText();
				Script script = new Script();
				script.setCommand("주석");
				ScriptComment scriptcomment = new ScriptComment();
				scriptcomment.setData(comment);
				script.setObject(scriptcomment);
				ScriptPanel.addRow(script);
				
				dispose();
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(179)
							.addComponent(confirmBtn)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(confirmBtn)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
