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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ScriptPresetGroupDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public ScriptPresetGroupDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("그룹 추가");
		lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 15));
		JButton btnNewButton = new JButton("추가");
		btnNewButton.setFont(new Font("Gulim", Font.PLAIN, 15));
		JScrollPane scrollPane = new JScrollPane();
		JButton btnNewButton_1 = new JButton("확 인");
		btnNewButton_1.setFont(new Font("Gulim", Font.PLAIN, 15));
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
									.addComponent(btnNewButton))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(168)
							.addComponent(btnNewButton_1)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		{
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"No.", "\uD56D\uBAA9 \uC774\uB984", "\uBCC0\uD654\uB7C9", "\uC120\uD0DD"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Boolean.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(0).setMaxWidth(40);
			table.getColumnModel().getColumn(3).setPreferredWidth(50);
			table.getColumnModel().getColumn(3).setMaxWidth(50);
			table.setFont(new Font("Gulim", Font.PLAIN, 15));
			scrollPane.setViewportView(table);
		}
		contentPanel.setLayout(gl_contentPanel);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
