package cdp2.mindle.data;

public class ScriptQuestion {
	private String question;
	private boolean selected;
	
	public ScriptQuestion() {
		question = "";
		selected = false;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
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
		
		result += "질문 : " + question;
		
		return result;
	}
}
