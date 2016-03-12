package engine;

import engine.component.ComponentData;
import engine.component.ComponentLoader;
import engine.input.KeyAction;
import engine.input.KeyMap;

public class Test {

	public static void main(String[] args) {
		testHashMap();
	}
	
	private static void testHashMap(){
		KeyMap map = new KeyMap();
		map.loadAction(new KeyAction("key", 0, false));
	}

	private static void testComponentData(){
		ComponentData data = new ComponentData("testComponent");
		data.addFloat("floater", 1.0f);
		data.addInteger("integer", 69);
		data.addString("astring", "testString");
		data.addVector3f("vector", 1.0f, 254.0f, 2455.05665f);
		ComponentLoader.writeComponentData("save/componentTestFile", data);
		
		ComponentData d = ComponentLoader.loadComponentData("save/componentTestFile", ".csf");
		System.out.println(d.getFloat("floater"));
		System.out.println(d.getInteger("integer"));
		System.out.println(d.getString("astring"));
		System.out.println(d.getVector3f("vector"));
	}
}
