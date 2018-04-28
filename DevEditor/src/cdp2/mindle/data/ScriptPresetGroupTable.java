package cdp2.mindle.data;

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
}
