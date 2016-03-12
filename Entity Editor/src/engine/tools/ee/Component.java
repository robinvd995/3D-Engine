package engine.tools.ee;

import java.util.List;

import engine.component.ComponentValue;

public class Component {

	public final String componentKey;
	public final ComponentValue[] values;
	
	public Component(ComponentBase base){
		componentKey = base.getComponentKey();
		List<ComponentValue> valueList = base.getBaseComponentValueList();
		
		values = new ComponentValue[valueList.size()];
		
		int i = 0;
		for(ComponentValue v : valueList){
			values[i++] = v.copy();
		}
	}
	
	public String getLocalizedName(){
		return Localizer.getLocalizedName(componentKey);
	}
}
