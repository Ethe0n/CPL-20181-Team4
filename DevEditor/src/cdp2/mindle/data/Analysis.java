package cdp2.mindle.data;

import java.util.ArrayList;
import java.util.List;

public class Analysis {
	int number;
	String name;
	List<AnalysisComponent> componentList;
	
	// 헤더
	int numberOfVariable;
	String[] variableNames = new String[5];
	boolean[] isMinus = new boolean[5];
	
	int initialValue;
	
	public Analysis()
	{
		number = 0;
		name = "";
		componentList = new ArrayList<AnalysisComponent>();
		numberOfVariable = 0;
	}
	
	public String toBinary()
	{
		return "";
	}
	
	public void parse(String binary)
	{
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}