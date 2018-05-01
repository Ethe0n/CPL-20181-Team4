package cdp2.mindle.core;

import java.util.HashMap;
import java.util.Map;

import cdp2.mindle.manager.SmartBuffer;

public class CoreInformation {
	public final static String version = "1.00.0";
	public final static String signature = "mindle";
	public final static String fileNameExtension = "mindle";
	public final static String[] targetList = new String[] {
			"남성", "여성", "노인", "성인", "청소년", "아동"
	};
	public final static String[] operationList = new String[] {
			"이상", "이하", "초과", "미만", "나머지 경우"
	};
	public final static Map<String, String> languageList = createLanguageList();
	private static Map<String, String> createLanguageList() 
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("한국어", "ko");	map.put("일본어", "ja");
		return map;
	}

	public final static int nameLimitBytes = 1000;
	public final static int codeLimitBytes = 1000;
	
	public static byte[] toBinary()
	{
		String str = signature.toUpperCase() + version.replace(".", "");
		
		return str.getBytes();
	}
}
