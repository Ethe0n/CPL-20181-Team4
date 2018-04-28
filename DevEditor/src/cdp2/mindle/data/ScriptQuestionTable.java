package cdp2.mindle.data;

public class ScriptQuestionTable {
	private String item;
	private boolean selected;
	
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
	
	public String toString()
	{
		String result = "";
		
		result +=  item + "\n";
		
		return result;
	}
}
