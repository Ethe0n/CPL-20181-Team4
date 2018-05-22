package cdp2.mindle.data;

import cdp2.mindle.manager.SmartBuffer;

public class ScriptPresetGroupTable {
	private String keyName;
	private String value;
	private boolean selected;
	
	public ScriptPresetGroupTable() {
		keyName = "";
		value = "";
		selected = false;
	}
	
	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean getSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public String toString() {
		String result = "";
		
		result += " 항목 이름 : " + keyName + "\n";
		result += " 변화량 : " + value + "\n";
		
		return result;
	}
	
	public String toBinary() {
		String bits = "";
		
		bits += SmartBuffer.intToBinaryArray(Integer.parseInt(keyName), 8);
		bits += SmartBuffer.intToBinaryArray(Integer.parseInt(value), 8);
		
		return bits;
	}
}
