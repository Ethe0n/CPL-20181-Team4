package cdp2.mindle.data;

import java.util.ArrayList;
import java.util.List;

import cdp2.mindle.manager.SmartBuffer;

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
	
	public String toBinary()
	{
		String bits = "";
		
		bits += SmartBuffer.intToBinaryArray(0, 12);
		bits += SmartBuffer.intToBinaryArray(group.size(), 4);
		for (ScriptPresetGroupTable iter : group) {
			bits += iter.toBinary();
		}

		bits += SmartBuffer.variableStrToBinaryArray(item, 8);
		
		return bits;
	}
}
