package engine.tools.ee;

import java.util.HashMap;

public class Localizer {

	private static HashMap<String,String> localizationMap = new HashMap<String,String>();
	
	public static void init(){
		addLocalization("component.test", "Test");
	}
	
	public static void addLocalization(String key, String localization){
		
	}
	
	public static String getLocalizedName(String key){
		return localizationMap.containsKey(key) ? localizationMap.get(key) : key;
	}
}
