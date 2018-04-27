package cdp2.mindle.data;

import java.util.HashMap;

import cdp2.mindle.core.CoreInformation;

public class Information {
	String name;
	String code;
	String language;
	HashMap<String, Boolean> target;
	
	public Information()
	{
		name = "";
		code = "";
		
		target = new HashMap<String, Boolean>();
		for (String iter : CoreInformation.targetList) {
			target.put(iter, false);
		}
	}
	
	public String toBinary()
	{
		return "";
	}
	
	public void parse(String binary)
	{
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) throws Exception {
		if (CoreInformation.languageList.containsKey(language)) {
			this.language = language;
		}
		
		throw new Exception("invalid language type");
	}
	public void setTarget(String type, boolean isTrue) throws Exception {
		try {
			target.replace(type, isTrue);
		}
		catch (Exception e) {
			throw new Exception("invalid target type");
		}
	}
}
