package cdp2.mindle.data;

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
}
