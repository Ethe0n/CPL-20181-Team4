package cdp2.mindle.data;

import java.util.ArrayList;
import java.util.List;

public class ScriptQuestionTable {
	private String item;
	private boolean selected;
	private List<ScriptPresetGroupTable> group;
	
	void ScriptQuestionTable() {
		item = "";
		selected = false;
		group = new ArrayList<ScriptPresetGroupTable>();
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public boolean getSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public void setGroup(List<ScriptPresetGroupTable> group) {
		this.group = group;
	}
	public List<ScriptPresetGroupTable> getGroup() {
		return group;
	}
	
	public String toString()
	{
		String result = "";
		
		result +=  group.toString() + "\n";
		
		return result;
	}
}
