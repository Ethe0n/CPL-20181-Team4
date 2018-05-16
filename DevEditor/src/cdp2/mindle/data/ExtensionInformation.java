package cdp2.mindle.data;

public class ExtensionInformation {
	private String key;
	private String value;
	private boolean selected;
	
	public ExtensionInformation() {
		key = "";
		value = "";
		selected = false;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
	
	public String toString()
	{
		String result = "";
		
		result += "키 : " + key + ", "
				+ "값 : " + value;
		
		return result;
	}
	
	public byte[] toBinary()
	{
		return null;
	}
}
