package cdp2.mindle.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cdp2.mindle.core.CoreInformation;
import cdp2.mindle.manager.SmartBuffer;

public class Information {
	String name;
	String code;
	String language;
	HashMap<String, Boolean> target;
	List<ExtensionInformation> extension;

	public Information() {
		name = "";
		code = "";

		target = new HashMap<String, Boolean>();
		for (String iter : CoreInformation.targetList) {
			target.put(iter, false);
		}

		extension = new ArrayList<ExtensionInformation>();
	}

	public String toBinary(){
		String bits = "";
		
		bits += SmartBuffer.variableStrToBinaryArray(code, 8);
		bits += SmartBuffer.strToBinaryArray(CoreInformation.languageList.get(language));
		bits += getTargetByte();
		bits += SmartBuffer.variableStrToBinaryArray(name, 8);
		bits += SmartBuffer.intToBinaryArray(extension.size(), 8);
		for (ExtensionInformation iter : extension) {
			bits += iter.toBinary();
		}
		
		return bits;
	}

	public void parse(String binary) {

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
		else {
			throw new Exception(language + " is invalid language type");	
		}
	}

	public void setTarget(String type, boolean isTrue) throws Exception {
		try {
			target.replace(type, isTrue);
		} catch (Exception e) {
			throw new Exception("invalid target type");
		}
	}
	
	public void setExtension(List<ExtensionInformation> extension) {
		this.extension = extension;
	}
	
	@Override
	public String toString()
	{
		String result = "";
		
		result += "이름 : " + name + "\n"
				+ "식별 코드 : " + code + "\n";
		result += "언어 : " + language + "\n";
		
		for (String iter : CoreInformation.targetList) {
			result += iter + " : " + target.get(iter) + "\n";
		}
		result += extension.toString();
		
		return result;
	}
	
	private String getTargetByte() {
		byte result = 0;
		String[] t = CoreInformation.targetList;
		byte flag[] = new byte[] {
				(byte)0x80, (byte)0x40, 8, 4, 2, 1
		};
		
		for (int i = 0; i < flag.length; ++i) {
			result += target.get(t[i]) ? flag[i] : 0;
		}
		
		return SmartBuffer.intToBinaryArray(result, 8);
	}
}
