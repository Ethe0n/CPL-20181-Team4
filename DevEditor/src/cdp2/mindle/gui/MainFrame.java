package cdp2.mindle.gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	InformationPanel informationPanel = new InformationPanel();
	ScriptPanel scriptPanel = new ScriptPanel();
	AnalysisPanel analysisPanel = new AnalysisPanel();
	
	public MainFrame(String name)
	{
		super(name);
		this.add(informationPanel);
		this.add(scriptPanel);
		this.add(analysisPanel);
	}
}
