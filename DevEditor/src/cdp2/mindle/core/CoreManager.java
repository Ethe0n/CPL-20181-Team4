package cdp2.mindle.core;

import cdp2.mindle.manager.AnalysisManager;
import cdp2.mindle.manager.FileManager;
import cdp2.mindle.manager.InformationManager;
import cdp2.mindle.manager.ScriptManager;

public class CoreManager {
	AnalysisManager analysisManager;
	FileManager fileManager;
	InformationManager informationManager;
	ScriptManager scriptManager;
	private static CoreManager instance;
	
	public CoreManager()
	{
		analysisManager = new AnalysisManager();
		fileManager = new FileManager();
		informationManager = new InformationManager();
		scriptManager = new ScriptManager();
	}

	public static CoreManager getInstance() {
		if ( instance == null )
			instance = new CoreManager();
		return instance;
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
	
	public void load(String path)
	{
		fileManager.loadFile(path);	
		parse(fileManager.getBinary());
	}
	
	public void save(String path)
	{
		fileManager.setBinary(toBinary());
		fileManager.saveFile(path);
	}
}