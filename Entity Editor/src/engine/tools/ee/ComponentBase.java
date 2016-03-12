package engine.tools.ee;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import engine.component.ComponentData;
import engine.component.ComponentLoader;
import engine.component.ComponentValue;

public class ComponentBase {
	
	private static HashMap<String,ComponentBase> baseMap = new HashMap<String,ComponentBase>();
	
	public static void loadBaseComponents(){
		File folder = new File("components/");
		for(File file : folder.listFiles()){
			ComponentData data = ComponentLoader.loadComponentData("components/" + file.getName(), "");
			ComponentBase base = new ComponentBase(data);
			EntityEditor.theWindow.log("Loaded component base '" + base.componentKey + "':");
			for(ComponentValue v : base.values){
				EntityEditor.theWindow.log("    " + v.toString());
			}
			baseMap.put(base.componentKey, base);
			EntityEditor.theWindow.log("END");
			EntityEditor.theWindow.log("");
		}
	}
	
	private String componentKey;
	private List<ComponentValue> values = new ArrayList<ComponentValue>();
	
	public ComponentBase(ComponentData data){
		componentKey = data.componentKey;
		values.addAll(data.getValues());
	}
	
	public String getComponentKey(){
		return componentKey;
	}
	
	public List<ComponentValue> getBaseComponentValueList(){
		return values.subList(0, values.size());
	}
	
	public boolean isValid(){
		return componentKey == null ? false : !componentKey.isEmpty();
	}
}
