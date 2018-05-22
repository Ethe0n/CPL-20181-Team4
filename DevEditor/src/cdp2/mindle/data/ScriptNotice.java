package cdp2.mindle.data;

import cdp2.mindle.manager.SmartBuffer;

public class ScriptNotice extends Script{
	private String id;
	private String notice;
	
	public ScriptNotice() {
		super();
		id = "";
		notice = "";
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	public String getId() {
		return id;
	}
	
	public String getNotice() {
		return notice;
	}
	
	@Override
	public String toString() {
		String result;
		result = "아이디 : " + id + "\n"
				+ "안내문 : " + notice;
		return result;
	}
	
	@Override
	public String toBinary()
	{
		String bits = "1000";
		
		bits += SmartBuffer.variableStrToBinaryArray(id, 12);
		bits += SmartBuffer.variableStrToBinaryArray(notice, 16);
		
		return bits;
	}
}
