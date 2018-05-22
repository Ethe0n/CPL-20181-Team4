package cdp2.mindle.core;

import java.io.ByteArrayOutputStream;
import java.util.List;

import cdp2.mindle.data.Analysis;
import cdp2.mindle.data.AnalysisComponent;
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
	
	public byte[] toBinary()
	{
		return null; 
	}
	
	public void parse(String binary)
	{
		// cut -> parse(1. info, 2. scr, 3. analy)
		informationManager.parse(binary);
		scriptManager.parse(binary);
		analysisManager.parse(binary);
	}
	
	public void load(String path) {
		try {
			byte[] data = fileManager.loadFile(path);
			System.out.println("data size : " + data.length + "b");

			SmartBuffer test = new SmartBuffer(data);
			System.out.println(test.readString(data.length * 8));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void save(String path) {
		try {
			String bits = "";
			bits += CoreInformation.toBinary();
			bits += informationManager.toBinary();
			bits += scriptManager.toBinary();
			bits += analysisManager.toBinary();
			
			byte[] dataBytes = SmartBuffer.binaryStringToByteArray(bits);
			int sum = 0;
			for (byte iter : dataBytes) {
				sum = (sum + iter) % 256;
			}
			bits += SmartBuffer.intToBinaryArray(256 - sum, 8);
			
			fileManager.saveFile(path, SmartBuffer.binaryStringToByteArray(bits));
			
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
	
	public void setAnalysisComponent(int index, List<AnalysisComponent> components) {
		analysisManager.setAnalysisComponentList(index, components);
	}
	
	public void setAnalysis(List<Analysis> analysis) {
		analysisManager.setAnalysisList(analysis);
	}
	
	public void setAnalysisHeaderExist(boolean isExist, int index)
	{
		analysisManager.setHeaderExist(isExist, index);
	}
}