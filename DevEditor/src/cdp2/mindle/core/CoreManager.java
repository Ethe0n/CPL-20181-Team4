package cdp2.mindle.core;

import cdp2.mindle.manager.AnalysisManager;
import cdp2.mindle.manager.FileManager;
import cdp2.mindle.manager.InformationManager;
import cdp2.mindle.manager.ScriptManager;

public class CoreManager {
	AnalysisManager 	analysisManager;
	FileManager 		fileManager;
	InformationManager 	informationManager;
	ScriptManager 		scriptManager;
	CoreInformation 	coreInformation;
	
	public CoreManager()
	{
		coreInformation = new CoreInformation();
		analysisManager = new AnalysisManager();
		fileManager = new FileManager();
		informationManager = new InformationManager();
		scriptManager = new ScriptManager();
	}

	public String toBinary()
	{
		return  informationManager.toBinary()
				+ scriptManager.toBinary()
				+ analysisManager.toBinary();	 
	}
	
	public void parse(String binary)
	{
		// cut -> parse(1. info, 2. scr, 3. analy)
	}
	
	public void load(String directory, String fileName)
	{
		fileManager.loadFile(directory, fileName);	
		parse(fileManager.getBinary());
	}
	
	public void save(String directory, String fileName)
	{
		fileManager.setBinary(toBinary());
		fileManager.saveFile(directory, fileName);
	}
}