package cdp2.mindle.data;

import java.util.ArrayList;
import java.util.List;

public class ScriptPreset extends Script{
	private String id;
	private int minAns;
	private int maxAns;
	private List<ScriptPresetTable> presetTable;
	
	public ScriptPreset() {
		//super();
		id = "";
		minAns = 0;
		maxAns = 0;
		presetTable = new ArrayList<ScriptPresetTable>();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setMinAns(int minAns) {
		this.minAns = minAns;
	}
	public void setMaxAns(int maxAns) {
		this.maxAns = maxAns;
	}
	public void setPresetTable(List<ScriptPresetTable> presetTable) {
		this.presetTable = presetTable;
	}
	public String getId() {
		return id;
	}
	public int minAns() {
		return minAns;
	}
	public int maxAns() {
		return maxAns;
	}
	public List<ScriptPresetTable> getPresetTable() {
		return presetTable;
	}
	
	@Override
	public String toString() {
		String result = "";
		
		result += "아이디 : " + id + "\n";
		result += " 최소 답변 수 : " + minAns + "\n";
		result += " 최대 답변 수 : " + maxAns + "\n";
		result += " 프리셋 : " + presetTable.toString() + "\n";
		
		return result;
	}
}
