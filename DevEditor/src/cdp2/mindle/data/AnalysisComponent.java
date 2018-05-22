package cdp2.mindle.data;

import cdp2.mindle.manager.SmartBuffer;

public class AnalysisComponent
{
	int refValue;
	String operator;
	String statement;
	String feedback;
	
	final static String[] operationList = new String[] {
			"이상", "이하", "초과", "미만", "나머지 경우"
	};
	final static String[] operationBits = new String[] {
			"00000011", "00000010",
			"00000001", "00000000",
			"10000000"
	};
	
	public AnalysisComponent()
	{
		this(0, "", "", "");
	}
	
	public AnalysisComponent(int refValue, String operator, String statement, String feedback)
	{
		this.refValue = 0;
		this.operator = operator;
		this.statement = statement;
		this.feedback = feedback;
	}
	
	public int getRefValue() {
		return refValue;
	}
	public void setRefValue(int refValue) {
		this.refValue = refValue;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	@Override
	public String toString()
	{
		return String.format("%d%s일때 %s", getRefValue(), getOperator(), getStatement());
	}
	
	public String toBinary()
	{
		String bits = "";
		
		for (int i = 0; i < 5; ++i) {
			if (operator == operationList[i]) {
				bits += operationBits[i];
				break;
			}
		}
		
		bits += SmartBuffer.variableStrToBinaryArray(statement, 8);
		bits += SmartBuffer.intToBinaryArray(refValue, 8);
		bits += SmartBuffer.variableStrToBinaryArray(feedback, 16);
		
		return bits;
	}
}