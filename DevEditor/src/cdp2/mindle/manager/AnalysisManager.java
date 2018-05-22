package cdp2.mindle.manager;

import java.util.List;

import cdp2.mindle.data.Analysis;
import cdp2.mindle.data.AnalysisComponent;

public class AnalysisManager {
	List<Analysis> analysisList;
	
	public AnalysisManager()
	{
		
	}
	
	public void parse(String binary)
	{
		
	}
	
	public String toBinary()
	{
		return "";
	}
	
	public void setAnalysisList(List<Analysis> list)
	{
		analysisList = list;
	}
	
	public void setAnalysisComponentList(int index, List<AnalysisComponent> list) {
		try {
			analysisList.get(index).setAnalysisComponentList(list);	
		}
		catch (Exception e) {
			System.err.println("index : " + index + ", " + e.getMessage());
		}
		
	}
	
	public void setHeaderExist(boolean isExist, int index) {
		analysisList.get(index).setIsHeaderExist(isExist);
	}
}
