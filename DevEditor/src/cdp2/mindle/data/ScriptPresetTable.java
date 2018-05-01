package cdp2.mindle.data;

import java.util.ArrayList;
import java.util.List;

public class ScriptPresetTable {
	private String data;
	private List<ScriptPresetGroupTable> group;
	private boolean selected;
	
	public ScriptPresetTable() {
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
}
