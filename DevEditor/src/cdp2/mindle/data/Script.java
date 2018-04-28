package cdp2.mindle.data;

public class Script {
	String command;
	String data;
	boolean selected;
	
	public Script() {
		command = "";
		data = "";
		selected = false;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public void appendData(String data) {
		this.data += data + " | ";
	}
	
	public String getCommand() {
		return command;
	}
	
	public String getData() {
		return data;
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public String toBinary()
	{
		return "";
	}
	
	public void parse(String binary)
	{
		
	}
}
