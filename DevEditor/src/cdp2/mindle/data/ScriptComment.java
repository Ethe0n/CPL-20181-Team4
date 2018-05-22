package cdp2.mindle.data;

import cdp2.mindle.manager.SmartBuffer;

public class ScriptComment extends Script {
	private String comment;
	
	public ScriptComment() {
		super();
		comment = "";
	}
	
	public void setData(String comment) {
		this.comment = comment;
	}
	
	public String getData() {
		return comment;
	}
	
	@Override
	public String toString() {
		String result;
		result = "주석 내용 : " + comment;
		return result;
	}
	
	@Override
	public String toBinary()
	{
		String bits = "0000";
	
		bits += SmartBuffer.variableStrToBinaryArray(comment, 12);
		
		return bits;
	}
}
