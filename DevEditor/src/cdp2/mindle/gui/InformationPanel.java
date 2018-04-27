package cdp2.mindle.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

import javax.swing.AbstractButton;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import cdp2.mindle.core.CoreInformation;
import cdp2.mindle.core.CoreManager;
import cdp2.mindle.data.ExtensionInformation;

@SuppressWarnings("serial")
public class InformationPanel extends JPanel {
	private JTextField nameInputField;
	private JTextField codeInputField;
	private JTable extensionTable;
	private JComboBox<String> languageSelectCombobox;
	
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

		// 이름 입력 필드
		nameInputField = new JTextField();
		nameInputField.setColumns(10);
		nameInputField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				String txt = nameInputField.getText();
				if (txt.length() > 0 && txt.getBytes().length >= CoreInformation.nameLimitBytes) {
					nameInputField.setText(substring(txt, CoreInformation.nameLimitBytes));
				}
				else {
					CoreManager.getInstance().setInformationName(txt);
				}
			}
		});
		
		// 식별 코드 입력 필드
		codeInputField = new JTextField();
		codeInputField.setColumns(10);
		codeInputField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				String txt = codeInputField.getText();
				if (txt.length() > 0 && txt.getBytes().length >= CoreInformation.codeLimitBytes) {
					codeInputField.setText(substring(txt, CoreInformation.codeLimitBytes));
				}
				else {
					CoreManager.getInstance().setInformationCode(txt);
				}
			}
		});
		
		ActionListener checkboxEvent = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				CoreManager.getInstance().setInformationTarget(abstractButton.getText(), selected);
			}
		};
		
		JCheckBox maleCheckbox = new JCheckBox("\uB0A8\uC131");
		maleCheckbox.setBackground(new Color(250, 235, 215));
		maleCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));
		maleCheckbox.addActionListener(checkboxEvent);
		
		JCheckBox femaleCheckbox = new JCheckBox("\uC5EC\uC131");
		femaleCheckbox.setBackground(new Color(250, 235, 215));
		femaleCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));
		femaleCheckbox.addActionListener(checkboxEvent);
		
		JCheckBox oldCheckbox = new JCheckBox("\uB178\uC778");
		oldCheckbox.setBackground(new Color(250, 235, 215));
		oldCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));
		oldCheckbox.addActionListener(checkboxEvent);
		
		JCheckBox adultCheckbox = new JCheckBox("\uC131\uC778");
		adultCheckbox.setBackground(new Color(250, 235, 215));
		adultCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));
		adultCheckbox.addActionListener(checkboxEvent);
		
		JCheckBox teenCheckbox = new JCheckBox("\uCCAD\uC18C\uB144");
		teenCheckbox.setBackground(new Color(250, 235, 215));
		teenCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));
		teenCheckbox.addActionListener(checkboxEvent);
		
		JCheckBox childCheckbox = new JCheckBox("\uC544\uB3D9");
		childCheckbox.setBackground(new Color(250, 235, 215));
		childCheckbox.setFont(new Font("굴림", Font.PLAIN, 15));
		childCheckbox.addActionListener(checkboxEvent);
		
		languageSelectCombobox = new JComboBox<>();
		languageSelectCombobox.setModel(new DefaultComboBoxModel(CoreInformation.languageList.keySet().toArray()));
		CoreManager.getInstance().setInformationLanguage(languageSelectCombobox.getSelectedItem().toString());
		languageSelectCombobox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				CoreManager.getInstance().setInformationLanguage(languageSelectCombobox.getSelectedItem().toString());
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();

		final ExtensionTableModel tableModel = new ExtensionTableModel();
		extensionTable = new JTable();
		extensionTable.setBackground(Color.WHITE);
		extensionTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		extensionTable.setFont(new Font("굴림", Font.PLAIN, 12));
		extensionTable.setModel(tableModel);
		extensionTable.getColumnModel().getColumn(0).setPreferredWidth(39);
		extensionTable.getColumnModel().getColumn(0).setMinWidth(10);
		extensionTable.getColumnModel().getColumn(0).setMaxWidth(39);
		extensionTable.getColumnModel().getColumn(3).setPreferredWidth(42);
		extensionTable.getColumnModel().getColumn(3).setMaxWidth(42);
		extensionTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				CoreManager.getInstance().setInformationExtension(tableModel.getData());
			}
		});
		extensionTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CoreManager.getInstance().setInformationExtension(tableModel.getData());
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				CoreManager.getInstance().setInformationExtension(tableModel.getData());
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				CoreManager.getInstance().setInformationExtension(tableModel.getData());
			}
		});
		extensionTable.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				CoreManager.getInstance().setInformationExtension(tableModel.getData());
			}
		});
		
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
	
	// 문자열 인코딩을 고려해서 문자열 자르기
    private String substring(String parameterName, int maxLength) {
        int DB_FIELD_LENGTH = maxLength;

        Charset utf8Charset = Charset.forName("UTF-8");
        CharsetDecoder cd = utf8Charset.newDecoder();

        try {
            byte[] sba = parameterName.getBytes("UTF-8");
            // Ensure truncating by having byte buffer = DB_FIELD_LENGTH
            ByteBuffer bb = ByteBuffer.wrap(sba, 0, DB_FIELD_LENGTH); // len in [B]
            CharBuffer cb = CharBuffer.allocate(DB_FIELD_LENGTH); // len in [char] <= # [B]
            // Ignore an incomplete character
            cd.onMalformedInput(CodingErrorAction.IGNORE);
            cd.decode(bb, cb, true);
            cd.flush(cb);
            parameterName = new String(cb.array(), 0, cb.position());
        } catch (UnsupportedEncodingException e) {
            System.err.println("### 지원하지 않는 인코딩입니다." + e);
        }

        return parameterName;
    }

    // 문자열 인코딩에 따라서 글자수 체크
    private int length(CharSequence sequence) {
        int count = 0;
        for (int i = 0, len = sequence.length(); i < len; i++) {
            char ch = sequence.charAt(i);

            if (ch <= 0x7F) {
                count++;
            } else if (ch <= 0x7FF) {
                count += 2;
            } else if (Character.isHighSurrogate(ch)) {
                count += 4;
                ++i;
            } else {
                count += 3;
            }
        }
        return count;
    }

}
