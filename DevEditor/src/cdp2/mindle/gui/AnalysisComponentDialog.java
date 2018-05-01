package cdp2.mindle.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class AnalysisComponentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private String[] plusMinusCombo = new String[] {
			"+", "-"
	};
	private String[] variableList = new String[] {
			"a", "b", "c"
	};
	
	/**
	 * Create the dialog.
	 */
	public AnalysisComponentDialog() {
		setTitle("평가 항목 편집");
		setBounds(100, 100, 527, 605);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		{
			JPanel panel = new JPanel();
			panel.setBackground(UIManager.getColor("Panel.background"));
			scrollPane.setViewportView(panel);
			JLabel lblNewLabel = new JLabel("헤더 포함 여부");
			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
			
			// 헤더 추가 여부를 결정하는 버튼
			JRadioButton yesContainHeadButton = new JRadioButton("예");
			yesContainHeadButton.setFont(new Font("굴림", Font.BOLD, 15));
			
			JRadioButton noContainHeadButton = new JRadioButton("아니오");
			noContainHeadButton.setFont(new Font("굴림", Font.BOLD, 15));
			noContainHeadButton.setSelected(true);
			
			ButtonGroup buttonGroup = new ButtonGroup();
			buttonGroup.add(yesContainHeadButton);
			buttonGroup.add(noContainHeadButton);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(Color.GRAY));
			
			JLabel label_1 = new JLabel("초기값");
			label_1.setFont(new Font("굴림", Font.BOLD, 15));
			
			JLabel lblNewLabel_2 = new JLabel("평가 항목");
			lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 15));
			
			JButton btnNewButton = new JButton("+");
			btnNewButton.setFont(new Font("Gulim", Font.PLAIN, 12));
			btnNewButton.setBackground(Color.WHITE);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			
			JSpinner spinner_1 = new JSpinner();
			spinner_1.setModel(new SpinnerNumberModel(0, -128, 127, 1));
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblNewLabel)
										.addGap(18)
										.addComponent(yesContainHeadButton)
										.addGap(34)
										.addComponent(noContainHeadButton))
									.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_2)
											.addComponent(label_1))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(btnNewButton)
											.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
										.addGap(58))))
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addContainerGap()
									.addComponent(panel_1, 0, 0, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addGap(14)
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(yesContainHeadButton)
							.addComponent(noContainHeadButton))
						.addGap(6)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_1)
							.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(btnNewButton))
						.addGap(8)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(395, Short.MAX_VALUE))
			);
			
			AnalysisComponentTableModel model = new AnalysisComponentTableModel();
			table = new JTable();
			table.setBorder(new LineBorder(Color.GRAY));
			table.setModel(model);
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(0).setMaxWidth(40);
			table.getColumnModel().getColumn(1).setMaxWidth(100);
			table.getColumnModel().getColumn(2).setMaxWidth(100);
			table.getColumnModel().getColumn(5).setMaxWidth(40);
			scrollPane_1.setViewportView(table);
			JLabel label = new JLabel("항 수");
			label.setFont(new Font("굴림", Font.BOLD, 15));
			JLabel lblNewLabel_1 = new JLabel("수식");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 15));
			
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(2, 2, 5, 1));
			
			JComboBox comboBox = new JComboBox(variableList);
			JComboBox comboBox_1 = new JComboBox(plusMinusCombo);
			JComboBox comboBox_2 = new JComboBox(variableList);
			JComboBox comboBox_3 = new JComboBox(plusMinusCombo);
			JComboBox comboBox_4 = new JComboBox(variableList);
			JComboBox comboBox_5 = new JComboBox(plusMinusCombo);
			JComboBox comboBox_6 = new JComboBox(variableList);
			JComboBox comboBox_7 = new JComboBox();
			
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addComponent(label)
							.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addComponent(spinner, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
							.addComponent(comboBox, 0, 50, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(44))
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(27)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(22, Short.MAX_VALUE))
			);
			panel_1.setLayout(gl_panel_1);
			panel.setLayout(gl_panel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("확인");
				okButton.setFont(new Font("굴림", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("취소");
				cancelButton.setFont(new Font("굴림", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}

