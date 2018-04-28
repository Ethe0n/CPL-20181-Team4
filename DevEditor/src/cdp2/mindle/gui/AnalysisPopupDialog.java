package cdp2.mindle.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AnalysisPopupDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the dialog.
	 */
	public AnalysisPopupDialog() {
		setBounds(100, 100, 450, 429);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("타입");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("평가 항목 번호");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("평가 항목 이름");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("초기값");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JLabel lblNewLabel_4 = new JLabel("기준 항목");
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblNewLabel_1)
										.addGap(18)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_2)
											.addComponent(lblNewLabel)
											.addComponent(lblNewLabel_3))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))))
								.addGap(31))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblNewLabel_4)
								.addContainerGap(351, Short.MAX_VALUE)))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addGap(18)
					.addComponent(lblNewLabel_4)
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No.", "\uD50C\uB798\uADF8", "\uC0C1\uD0DC", "\uBE44\uAD50 \uB300\uC0C1 \uAC12", "\uD53C\uB4DC\uBC31"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.setFont(new Font("굴림", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
