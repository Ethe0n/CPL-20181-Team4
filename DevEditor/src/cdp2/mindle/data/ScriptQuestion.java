package cdp2.mindle.data;

import java.util.ArrayList;
import java.util.List;

public class ScriptQuestion {
	private String id;
	private String question;
	private int curMethod;
	
	//////method 0
	private int minAns; 
	private int maxAns;
	private List<ScriptQuestionTable> custom;
	
	//////method 1
	private String preset_id;
	private boolean check_override;
	private int numAns;
	
	//////method 2
	private int minLen;
	private int maxLen;
	
	public ScriptQuestion() {
		id = "";
		question = "";
		curMethod = 0;
		custom = new ArrayList<ScriptQuestionTable>();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestion(String question) {
		return question;
	}
	
	public void setCustom(List<ScriptQuestionTable> custom) {
		this.custom = custom;
	}
	public List<ScriptQuestionTable> getCustom() {
		return custom;
	}
	public void setCurMethod(int curMethod) {
		this.curMethod = curMethod;
	}
	public int getCurMethod() {
		return curMethod;
	}
	
	///method 0
	public void setMinAns(int minAns) {
		this.minAns = minAns;
	}
	public void setMaxAns(int maxAns) {
		this.maxAns = maxAns;
	}
	public int getMinAns() {
		return minAns;
	}
	public int getMaxAns() {
		return maxAns;
	}
	
	///method 1
	public void setPresetId(String preset_id) {
		this.preset_id = preset_id;
	}
	public void setCheckOverride(boolean check_override) {
		this.check_override = check_override;
	}
	public void setNumAns(int numAns) {
		this.numAns = numAns;
	}
	public String getPresetId() {
		return preset_id;
	}
	public boolean getCheckOverride() {
		return check_override;
	}
	public int getNumAns() {
		return numAns;
	}
	
	///method 2
	public void setMinLen(int minLen) {
		this.minLen = minLen;
	}
	public void setMaxLen(int maxLen) {
		this.maxLen = maxLen;
	}
	public int getMinLen() {
		return minLen;
	}
	public int getMaxLen() {
		return maxLen;
	}
	@Override
	public String toString() {
		String result = "";
		
		result = "아이디 : " + id + "\n"
				+ " 질문 : " + question + "\n";
		
		
		switch(curMethod) {
		case 0 :
			result += " 최소 답변 수 : " + minAns + "\n";
			result += " 최대 답변 수 : " + maxAns + "\n";
			result += " 문항 : " + custom.toString() + "\n"; 
			break;
		case 1 : 
			result += " 프리셋 아이디 : " + preset_id + "\n";
			result += " 오버라이드 여부 : " + check_override + "\n";
			result += " 답변 개수 : " + numAns + "\n";
			break;
		case 2 : 
			result += " 최소 답변 길이 : " + minLen + "\n";
			result += " 최대 답변 길이 : " + maxLen + "\n";
			break;
		}
		
		return result;
	}
	
}
