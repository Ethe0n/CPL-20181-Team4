package cdp2.mindle.data;

public class ScriptNotice {
	private String id;
	private String notice;
	
	public ScriptNotice() {
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
}
