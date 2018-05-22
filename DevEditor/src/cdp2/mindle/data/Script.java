package cdp2.mindle.data;

import java.util.List;

import cdp2.mindle.core.CoreManager;
import cdp2.mindle.manager.SmartBuffer;

public class Script {
	private String command;
	private String data;
	private Object scriptObject;
	
	public Script() {
		command = "";
		data = "";
		scriptObject = null;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getData() {
		return scriptObject.toString();
	}
	
	
	public String getCommand() {
		return command;
	}
	
	public void setObject(Object scriptObject)
	{
		this.scriptObject = scriptObject;
	}
	
	public Object getScriptObject() {
		return scriptObject;
	}
	
	public void parse(String binary)
	{
		
	}
	
	public String toBinary()
	{
		String bits = "";
		switch (command) {
		case "주석" :
			bits += "0000"; break;
		case "안내문" :
			bits += "1000";	break;
		case "질문" :
			bits += "0001"; break;
		case "프리셋" :
			bits += "0010"; break;
		}
		
		return bits;
	}
}
