package cdp2.mindle.data;

import java.util.List;

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
}
