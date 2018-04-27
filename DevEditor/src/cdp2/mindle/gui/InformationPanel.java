package cdp2.mindle.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import cdp2.mindle.core.CoreInformation;
import cdp2.mindle.data.ExtensionInformation;

public class InformationPanel extends JPanel {
	private JTextField nameInputField;
	private JTextField codeInputField;
	private JTable extensionTable;

	/**
	 * Create the panel.
	 */
	public InformationPanel() {
		setBackground(new Color(250, 235, 215));
		setForeground(UIManager.getColor("Button.light"));
		
		JLabel nameLabel = new JLabel("\uC774\uB984");
		nameLabel.setFont(new Font("굴림", Font.BOLD, 15));

		JLabel codeLabel = new JLabel("\uC2DD\uBCC4 \uCF54\uB4DC");
		codeLabel.setFont(new Font("굴림", Font.BOLD, 15));

		JLabel targetLabel = new JLabel("\uB300\uC0C1");
		targetLabel.setFont(new Font("굴림", Font.BOLD, 15));

		JLabel duplicateLabel = new JLabel("(\uC911\uBCF5 \uAC00\uB2A5)");
		duplicateLabel.setFont(new Font("굴림", Font.BOLD, 15));

		JLabel languageLabel = new JLabel("\uC5B8\uC5B4");
		languageLabel.setFont(new Font("굴림", Font.BOLD, 15));

		JLabel extensionLabel = new JLabel("\uCD94\uAC00\uC815\uBCF4");
		extensionLabel.setFont(new Font("굴림", Font.BOLD, 15));

		nameInputField = new JTextField();
		nameInputField.setColumns(10);

		codeInputField = new JTextField();
		codeInputField.setColumns(10);

		JCheckBox maleCheckbox = new JCheckBox("\uB0A8\uC131");
		maleCheckbox.setBackground(new Color(250, 235, 215));
		maleCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));

		JCheckBox femaleCheckbox = new JCheckBox("\uC5EC\uC131");
		femaleCheckbox.setBackground(new Color(250, 235, 215));
		femaleCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));

		JCheckBox oldCheckbox = new JCheckBox("\uB178\uC778");
		oldCheckbox.setBackground(new Color(250, 235, 215));
		oldCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));

		JCheckBox adultCheckbox = new JCheckBox("\uC131\uC778");
		adultCheckbox.setBackground(new Color(250, 235, 215));
		adultCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));

		JCheckBox teenCheckbox = new JCheckBox("\uCCAD\uC18C\uB144");
		teenCheckbox.setBackground(new Color(250, 235, 215));
		teenCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));

		JCheckBox childCheckbox = new JCheckBox("\uC544\uB3D9");
		childCheckbox.setBackground(new Color(250, 235, 215));
		childCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));

		JComboBox languageSelectCombobox = new JComboBox();
		languageSelectCombobox.setModel(new DefaultComboBoxModel(CoreInformation.languageList.keySet().toArray()));

		JScrollPane scrollPane = new JScrollPane();

		final ExtensionTableModel tableModel = new ExtensionTableModel();
		extensionTable = new JTable();
		extensionTable.setBackground(Color.WHITE);
		extensionTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		extensionTable.setFont(new Font("굴림", Font.PLAIN, 15));
		extensionTable.setModel(tableModel);
		extensionTable.getColumnModel().getColumn(0).setPreferredWidth(39);
		extensionTable.getColumnModel().getColumn(0).setMinWidth(10);
		extensionTable.getColumnModel().getColumn(0).setMaxWidth(39);
		extensionTable.getColumnModel().getColumn(3).setPreferredWidth(42);
		extensionTable.getColumnModel().getColumn(3).setMaxWidth(42);
		
		// 추가 버튼
		JButton addButton = new JButton("+");
		addButton.setBackground(Color.WHITE);
		addButton.setFont(new Font("굴림", Font.PLAIN, 10));
		addButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                ExtensionInformation exInfo = new ExtensionInformation();
                tableModel.addRow(exInfo);
            }
		});
		
		// 삭제 버튼
		JButton deleteButton = new JButton("-");
		deleteButton.setBackground(Color.WHITE);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteButton.setFont(new Font("굴림", Font.PLAIN, 10));
		deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.deleteRow();
            }
        });
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(
								groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addContainerGap()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(duplicateLabel).addComponent(extensionLabel)
														.addComponent(codeLabel)))
										.addGroup(groupLayout.createSequentialGroup().addContainerGap()
												.addComponent(nameLabel))
										.addGroup(groupLayout
												.createSequentialGroup().addContainerGap().addComponent(targetLabel))
										.addGroup(groupLayout
												.createSequentialGroup().addContainerGap().addComponent(languageLabel)))
						.addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup()
								.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(deleteButton)
								.addContainerGap()).addGroup(groupLayout.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(groupLayout
										.createSequentialGroup()
										.addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(codeInputField).addComponent(nameInputField,
																GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
												.addGroup(groupLayout.createSequentialGroup().addComponent(maleCheckbox)
														.addGap(18).addComponent(femaleCheckbox))
												.addGroup(groupLayout.createSequentialGroup().addComponent(oldCheckbox)
														.addGap(18).addComponent(adultCheckbox).addGap(18)
														.addComponent(teenCheckbox).addGap(18)
														.addComponent(childCheckbox))
												.addComponent(languageSelectCombobox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(19)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameInputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(nameLabel))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(codeLabel).addComponent(codeInputField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(maleCheckbox)
						.addComponent(femaleCheckbox).addComponent(targetLabel))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(duplicateLabel).addComponent(oldCheckbox)
						.addComponent(adultCheckbox).addComponent(teenCheckbox).addComponent(childCheckbox))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(languageSelectCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(languageLabel))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(extensionLabel)
						.addComponent(addButton, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addGap(33)));

		
		scrollPane.setViewportView(extensionTable);
		setLayout(groupLayout);

	}
}
