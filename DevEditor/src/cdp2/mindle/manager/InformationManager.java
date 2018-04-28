package cdp2.mindle.manager;

import java.io.IOException;
import java.util.List;

import cdp2.mindle.data.ExtensionInformation;
import cdp2.mindle.data.Information;

public class InformationManager {
	Information information;
	
	public InformationManager()
	{
		information = new Information();
	}
	
	public byte[] toBinary() throws IOException
	{
		return information.toBinary();
	}
	
	public void parse(String binary)
	{
		
	}
	
	public void setName(String name) {
		information.setName(name);
	}

	public void setCode(String code) {
		information.setCode(code);
	}
	
	public void setTarget(String type, boolean isTrue) throws Exception {
			information.setTarget(type, isTrue);
	}
	
	public void setLanguage(String language) throws Exception {
		information.setLanguage(language);
	}
	
	public void setExtension(List<ExtensionInformation> extension) {
		information.setExtension(extension);
	}
	
	@Override
	public String toString() 
	{
		return information.toString();
	}
}
