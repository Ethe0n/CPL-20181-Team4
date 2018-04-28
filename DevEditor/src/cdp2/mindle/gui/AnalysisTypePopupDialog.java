package cdp2.mindle.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnalysisTypePopupDialog extends JFrame {

	private final JPanel contentPanel = new JPanel();
	
	private JPanel contentPane;
	private JTable table;
	
	/**
	 * Create the frame.
	 */
	public AnalysisTypePopupDialog() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("항 수");
		label.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"이항", "삼항", "사항", "오항"}));
	

		
		JLabel label_1 = new JLabel("항 사이 뺄셈 여부");
		label_1.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JCheckBox checkBox = new JCheckBox("(1)-(2)");
		
		JCheckBox checkBox_1 = new JCheckBox("(2)-(3)");
		checkBox_1.setVisible(false);
		
		JCheckBox checkBox_2 = new JCheckBox("(3)-(4)");
		checkBox_2.setVisible(false);
		JCheckBox checkBox_3 = new JCheckBox("(4)-(5)");
		checkBox_3.setVisible(false);


		JLabel label_2 = new JLabel("변수");
		label_2.setFont(new Font("Gulim", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_1)
						.addComponent(label_2)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(checkBox)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBox_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBox_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBox_3)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox)
						.addComponent(checkBox_1)
						.addComponent(checkBox_2)
						.addComponent(checkBox_3))
					.addGap(18)
					.addComponent(label_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{null, null},
			},
			new String[] {
				"No.", "\uBCC0\uC218(\uC2DD\uBCC4\uC790)"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(41);
		table.getColumnModel().getColumn(0).setMaxWidth(41);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		//
		comboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				int selected = comboBox.getSelectedIndex();
				System.out.println("Selected Item  = " + selected);
				
				if(selected == 0)
				{	
					checkBox.setVisible(true);
					checkBox_1.setVisible(false);
					checkBox_2.setVisible(false);
					checkBox_3.setVisible(false);
				
					
				}
				else if(selected == 1)
				{
					checkBox_1.setVisible(true);
					checkBox_2.setVisible(false);
					checkBox_3.setVisible(false);
					
				}
				else if(selected == 2)
				{
					checkBox_1.setVisible(true);
					checkBox_2.setVisible(true);
					checkBox_3.setVisible(false);
					
				}
				else if(selected == 3)
				{
					checkBox_1.setVisible(true);
					checkBox_2.setVisible(true);
					checkBox_3.setVisible(true);
					
				}
			}
		
		});
		
//		
	}
	
	
}
