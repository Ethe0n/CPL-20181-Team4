package cdp2.mindle.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellRenderer;

import cdp2.mindle.core.CoreInformation;
import cdp2.mindle.data.AnalysisComponent;

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
		setBounds(100, 100, 674, 605);
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
			
			JPanel headerPanel = new JPanel();
			headerPanel.setBorder(new LineBorder(Color.GRAY));
			
			JLabel label_1 = new JLabel("초기값");
			label_1.setFont(new Font("굴림", Font.BOLD, 15));
			
			JLabel lblNewLabel_2 = new JLabel("평가 항목");
			lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 15));
			
			// 헤더 추가 여부를 결정하는 버튼
			JRadioButton yesContainHeadButton = new JRadioButton("예");
			yesContainHeadButton.setFont(new Font("굴림", Font.BOLD, 15));
			yesContainHeadButton.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						headerPanel.setVisible(true);
					}
					else {
						headerPanel.setVisible(false);
					}
				}
			});
			JRadioButton noContainHeadButton = new JRadioButton("아니오");
			noContainHeadButton.setFont(new Font("굴림", Font.BOLD, 15));
			noContainHeadButton.setSelected(true);
			headerPanel.setVisible(false);
			
			ButtonGroup buttonGroup = new ButtonGroup();
			buttonGroup.add(yesContainHeadButton);
			buttonGroup.add(noContainHeadButton);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			
			AnalysisComponentTableModel model = new AnalysisComponentTableModel();
			table = new JTable();
			table.setBorder(new LineBorder(Color.GRAY));
			table.setModel(model);
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(0).setMaxWidth(40);
			table.getColumnModel().getColumn(1).setMaxWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setMinWidth(50);
			table.getColumnModel().getColumn(2).setMaxWidth(200);
			table.getColumnModel().getColumn(5).setPreferredWidth(40);
			table.getColumnModel().getColumn(5).setMaxWidth(40);
			table.getColumn("비교").setCellRenderer(new ComboBoxRenderer(CoreInformation.operationList));
			table.getColumn("비교").setCellEditor(new ComboBoxEditor(CoreInformation.operationList, model));
			table.getColumn("삭제").setCellRenderer(new ButtonRenderer());
			table.getColumn("삭제").setCellEditor(new RemoveComponentButton(new JCheckBox(), model));
			scrollPane_1.setViewportView(table);
			
			JButton addButton = new JButton("+");
			addButton.setFont(new Font("굴림", Font.PLAIN, 12));
			addButton.setBackground(Color.WHITE);
			addButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					model.addRow(new AnalysisComponent());
				}
			});
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
											.addComponent(addButton)
											.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(headerPanel, 0, 0, Short.MAX_VALUE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(14)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)))
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
						.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_1)
							.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(addButton))
						.addGap(8)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(395, Short.MAX_VALUE))
			);
			
			
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
			JComboBox comboBox_7 = new JComboBox(plusMinusCombo);
			JComboBox comboBox_8 = new JComboBox(variableList);
			
			GroupLayout gl_headerPanel = new GroupLayout(headerPanel);
			gl_headerPanel.setHorizontalGroup(
				gl_headerPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_headerPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_headerPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(label)
							.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_headerPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_headerPanel.createSequentialGroup()
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox_8, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addGap(39))
			);
			gl_headerPanel.setVerticalGroup(
				gl_headerPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_headerPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_headerPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(27)
						.addGroup(gl_headerPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(37, Short.MAX_VALUE))
			);
			headerPanel.setLayout(gl_headerPanel);
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

class ComboBoxRenderer extends JComboBox implements TableCellRenderer {
	public ComboBoxRenderer(String[] items) {
		super(items);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		setSelectedItem(value);
		return this;
	}
}

class ComboBoxEditor extends DefaultCellEditor {
	private AnalysisComponentTableModel model;
	
	ComboBoxEditor(String[] items, AnalysisComponentTableModel model) {
        super(new JComboBox(items));
        
        this.model = model;
    }
}

class RemoveComponentButton extends DefaultCellEditor
{
	protected JButton button;
	private String label;
	private boolean isPushed;
	private int selectedRow;
	private AnalysisComponentTableModel model;
	
	public RemoveComponentButton(JCheckBox checkBox, AnalysisComponentTableModel model) {
        super(checkBox);
        this.model = model;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        selectedRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
        	if (model.getRowCount() >= 1 && selectedRow < model.getRowCount()) {
        		model.deleteRow(selectedRow);
        	}
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}