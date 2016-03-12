package engine.component;

import java.util.ArrayList;
import java.util.List;

import engine.utils.Vector3f;

public class ComponentData {

	public final String componentKey;
	private List<ComponentValue> values = new ArrayList();

	public ComponentData(String key){
		componentKey = key;
	}

	public void addValue(ComponentValue value){
		if(!values.contains(value)){
			values.add(value);
		}else{
			System.out.println("Trying to add double component!");
		}
	}
	
	public void addInteger(String key, int value){
		this.addValue(new ComponentValue(key, ComponentValue.COMPONENT_TYPE_INT, value));
	}

	public void addFloat(String key, float value){
		this.addValue(new ComponentValue(key, ComponentValue.COMPONENT_TYPE_FLOAT, value));
	}

	public void addString(String key, String value){
		this.addValue(new ComponentValue(key, ComponentValue.COMPONENT_TYPE_STRING, value));
	}

	public void addVector3f(String key, float x, float y, float z){
		this.addValue(new ComponentValue(key, ComponentValue.COMPONENT_TYPE_VECTOR3, new Vector3f(x, y, z)));
	}
	
	private ComponentValue getValue(String key){
		for(ComponentValue v : values){
			if(v.valueKey.equals(key)){
				return v;
			}
		}
		return null;
	}
	
	public int getInteger(String key){
		ComponentValue v = getValue(key);
		return v == null ? 0 : v.valueType == ComponentValue.COMPONENT_TYPE_INT ? v.getInteger() : 0;
	}
	
	public float getFloat(String key){
		ComponentValue v = getValue(key);
		return v == null ? 0.0f : v.valueType == ComponentValue.COMPONENT_TYPE_FLOAT ? v.getFloat() : 0.0f;
	}
	
	public String getString(String key){
		ComponentValue v = getValue(key);
		return v == null ? null : v.valueType == ComponentValue.COMPONENT_TYPE_STRING ? v.getString() : null;
	}
	
	public Vector3f getVector3f(String key){
		ComponentValue v = getValue(key);
		return v == null ? null : v.valueType == ComponentValue.COMPONENT_TYPE_VECTOR3 ? v.getVector3f() : null;
	}
	
	public boolean isEmpty(){
		return values.isEmpty();
	}
	
	public List<ComponentValue> getValues(){
		return values;
	}
}
