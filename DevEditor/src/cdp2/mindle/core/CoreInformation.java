package cdp2.mindle.core;

import java.util.HashMap;
import java.util.Map;

public class CoreInformation {
	public final static String version = "1.1.0";
	public final static String signature = "mindle";
	public final static String[] targetList = new String[] {
			"남성", "여성", "노인", "성인", "청소년", "아동"
	};
	public final static Map<String, String> languageList = createLanguageList();
	private static Map<String, String> createLanguageList() 
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("한국어", "ko");	map.put("일본어", "ja");
		return map;
	}
}
