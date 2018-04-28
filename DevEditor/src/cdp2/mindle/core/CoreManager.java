package cdp2.mindle.core;

import java.util.List;

import cdp2.mindle.data.ExtensionInformation;
import cdp2.mindle.manager.AnalysisManager;
import cdp2.mindle.manager.FileManager;
import cdp2.mindle.manager.InformationManager;
import cdp2.mindle.manager.ScriptManager;
import cdp2.mindle.manager.SmartBuffer;

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
		try {
			SmartBuffer test = new SmartBuffer(fileManager.loadFile(path));
			System.out.println(test.readString(120));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void save(String path)
	{
		byte[] test = new byte[] {
				(byte)0xeb, (byte)0xa1, (byte)0xa4, (byte)0xed,
				(byte)0x95, (byte)0x98, (byte)0xea, (byte)0xb3,
				(byte)0xa0, (byte)0xec, (byte)0x8b, (byte)0xb6,
				(byte)0xeb, (byte)0x8b, (byte)0xa4	
		};
		try {
			fileManager.saveFile(path, test);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void setInformationName(String name) {
		informationManager.setName(name);
	}
	
	public void setInformationCode(String code) {
		informationManager.setCode(code);
	}
	
	public void setInformationTarget(String type, boolean isTrue) {
		try {
			informationManager.setTarget(type, isTrue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setInformationLanguage(String language) {
		try {
			informationManager.setLanguage(language);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setInformationExtension(List<ExtensionInformation> extension) {
		informationManager.setExtension(extension);
	}
	
	public void setCommand(String command) {
		scriptManager.setCommand(command);
	}
}