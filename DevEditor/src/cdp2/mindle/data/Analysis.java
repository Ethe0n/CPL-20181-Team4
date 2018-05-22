package cdp2.mindle.data;

import java.util.ArrayList;
import java.util.List;

import cdp2.mindle.manager.SmartBuffer;

public class Analysis {
	int number;
	String name;
	List<AnalysisComponent> componentList;
	
	// 헤더
	boolean isHeaderExist;
	int numberOfVariable;
	String[] variableNames = new String[5];
	boolean[] isMinus = new boolean[5];
	
	int initialValue;
	
	public Analysis()
	{
		isHeaderExist = false;
		number = 0;
		name = "";
		componentList = new ArrayList<AnalysisComponent>();
		numberOfVariable = 0;
	}
	
	public void setIsHeaderExist(boolean isExist)
	{
		this.isHeaderExist = isExist;
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
	
	public void setAnalysisComponentList(List<AnalysisComponent> list) {
		componentList = list;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s", componentList.toString());
	}
	
	public String toBinary()
	{
		String bits = "";
		
		if (isHeaderExist) {
			bits += "10";
			bits += SmartBuffer.intToBinaryArray(numberOfVariable - 2, 2);
			for (int i = 0; i < numberOfVariable; ++i) {
				bits += isMinus[i] ? "1" : "0";
			}
			for (int i = 0; i < numberOfVariable; ++i) {
				bits += SmartBuffer.intToBinaryArray(Integer.parseInt(variableNames[i]), 8);
			}
		}
		else {
			bits += SmartBuffer.intToBinaryArray(0, 8);
		}
		
		bits += SmartBuffer.intToBinaryArray(number, 8);
		bits += SmartBuffer.variableStrToBinaryArray(name, 8);
		bits += SmartBuffer.intToBinaryArray(initialValue, 8);
		bits += SmartBuffer.intToBinaryArray(componentList.size(), 8);
		for (AnalysisComponent iter : componentList) {
			bits += iter.toBinary();
		}
		
		return bits;
	}
}