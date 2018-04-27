package cdp2.mindle.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import cdp2.mindle.core.CoreInformation;
import cdp2.mindle.core.CoreManager;

public class MainFrame extends JFrame implements ActionListener
{
	InformationPanel informationPanel = new InformationPanel();
	ScriptPanel scriptPanel = new ScriptPanel();
	AnalysisPanel analysisPanel = new AnalysisPanel();
	JButton saveButton;
	JButton loadButton;
	private JFileChooser fileChooser = new JFileChooser();
	
	public MainFrame(String name)
	{
		super(name);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e) {
		}
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter(CoreInformation.fileNameExtension, CoreInformation.fileNameExtension));
		fileChooser.setMultiSelectionEnabled(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setSize(1000, 1000);
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		saveButton = new JButton("\uC800\uC7A5");
		saveButton.setFont(new Font("굴림", Font.BOLD, 15));
		saveButton.addActionListener(this);
		menuBar.add(saveButton);
		
		loadButton = new JButton("\uBD88\uB7EC\uC624\uAE30");
		loadButton.setFont(new Font("굴림", Font.BOLD, 15));
		loadButton.addActionListener(this);
		menuBar.add(loadButton);
		
		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
				   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   .addComponent(menuBar)
				           .addComponent(informationPanel)
				           .addComponent(scriptPanel)
				           .addComponent(analysisPanel)
				           )
				);
		
		layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   .addComponent(menuBar)   
				   .addComponent(informationPanel)
				   .addComponent(scriptPanel)
				   .addComponent(analysisPanel)
				);
		
		JScrollPane scrollPane = new JScrollPane(
				mainPanel, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
				);
		
		add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == loadButton) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				CoreManager.getInstance().load(fileChooser.getSelectedFile().toString());
			}
		} else if (event.getSource() == saveButton) {
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				CoreManager.getInstance().save(fileChooser.getSelectedFile().toString() + "." + fileChooser.getFileFilter().getDescription());
			}
		}
	}
}
