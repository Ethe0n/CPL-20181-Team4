package cdp2.mindle.gui;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame
{
	InformationPanel informationPanel = new InformationPanel();
	ScriptPanel scriptPanel = new ScriptPanel();
	AnalysisPanel analysisPanel = new AnalysisPanel();
	
	public MainFrame(String name)
	{
		super(name);
		JPanel mainPanel = new JPanel();
		mainPanel.setSize(1000, 1000);
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				           .addComponent(informationPanel)
				           .addComponent(scriptPanel)
				           .addComponent(analysisPanel))
				);
		
		layout.setVerticalGroup(
				   layout.createSequentialGroup()
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				           .addComponent(informationPanel))
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					           .addComponent(scriptPanel))
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					           .addComponent(analysisPanel))
				);
		
		JScrollPane scrollPane = new JScrollPane(
				mainPanel, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
				);
		
		add(scrollPane);
	}
}
