package cdp2.mindle.manager;

import java.util.ArrayList;
import java.util.List;

import cdp2.mindle.data.Script;

public class ScriptManager 
{
	Script script;
	
	public ScriptManager()
	{
		script = new Script();
	}
	
	public void setCommand(String command) {
		script.setCommand(command);
	}
	
	public String toBinary()
	{
		return "";
	}
	
	public void parse(String binary)
	{
		
	}
}
