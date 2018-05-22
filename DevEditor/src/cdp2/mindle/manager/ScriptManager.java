package cdp2.mindle.manager;

import java.util.ArrayList;
import java.util.List;

import cdp2.mindle.data.Script;
import cdp2.mindle.data.ScriptComment;
import cdp2.mindle.data.ScriptNotice;
import cdp2.mindle.data.ScriptPreset;
import cdp2.mindle.data.ScriptQuestion;

public class ScriptManager 
{
	private List<Script> scriptList;
	
	public ScriptManager()
	{
		scriptList = new ArrayList<Script>();
	}
	
	public String toBinary()
	{
		return "";
	}
	
	public void parse(String binary)
	{
		
	}
	
	public void setScriptList(List<Script> scriptList) {
		this.scriptList = scriptList;
	}
	public List<Script> getScriptList() {
		return scriptList;
	}
	
	public Script findScriptbyId(String id) {		
		Script temp;
		for(int index = 0; index < scriptList.size(); index++) {
			temp = scriptList.get(index);
			
			if(temp.getScriptObject() instanceof ScriptComment) {
				continue;
			}		
			else if(temp.getScriptObject() instanceof ScriptNotice) {
				ScriptNotice sn = (ScriptNotice) temp.getScriptObject();					
				if(sn.getId().equals(id)) return temp;
			}
			else if(temp.getScriptObject() instanceof ScriptQuestion) {
				ScriptQuestion sq = (ScriptQuestion) temp.getScriptObject();
				if(sq.getId().equals(id)) return temp;
			}
			else if(temp.getScriptObject() instanceof ScriptPreset) {
				
				ScriptPreset sp = (ScriptPreset) temp.getScriptObject();		
				if(sp.getId().equals(id)) return temp;
			}
		}
		return null;
	}
}
