package cdp2.mindle.data;

public class AnalysisComponent
{
	int refValue;
	String operator;
	String statement;
	String feedback;
	
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
}