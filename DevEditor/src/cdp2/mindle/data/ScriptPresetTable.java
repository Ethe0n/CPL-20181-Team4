package cdp2.mindle.data;

import java.util.ArrayList;
import java.util.List;

import cdp2.mindle.manager.SmartBuffer;

public class ScriptPresetTable {
	private String data;
	private List<ScriptPresetGroupTable> group;
	private boolean selected;
	
	public ScriptPresetTable() {
		super();
		data = "";
		group = new ArrayList<ScriptPresetGroupTable>();
		selected = false;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	public void setGroup(List<ScriptPresetGroupTable> group) {
		this.group = group;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getData() {
		return data;
	}
	public List<ScriptPresetGroupTable> getGroup() {
		return group;
	}
	public boolean getSelected() {
		return selected;
	}
	
	@Override
	public String toString() {
		String result = "";
		
		result += "데이터 : " + data + "\n";
		
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
		bits += SmartBuffer.variableStrToBinaryArray(data, 8);
		
		return bits;
	}
}
