package engine.component;

import engine.utils.Vector3f;

public class ComponentValue {

	public static final int COMPONENT_TYPE_FLOAT = 0;
	public static final int COMPONENT_TYPE_INT = 1;
	public static final int COMPONENT_TYPE_STRING = 2;
	public static final int COMPONENT_TYPE_VECTOR3 = 3;
	
	public final String valueKey;
	public final int valueType;
	private final Object value;
	
	public ComponentValue(String key, int type, Object o){
		valueKey = key;
		valueType = type;
		value = o;
	}
	
	public boolean equals(Object o){
		return (o instanceof ComponentValue) ? ((ComponentValue)o).valueKey.equals(valueKey) : false;
	}
	
	public String getString(){
		return valueType == COMPONENT_TYPE_STRING ? (String)value : null;
	}
	
	public int getInteger(){
		return valueType == COMPONENT_TYPE_INT ? (int)value : null;
	}
	
	public float getFloat(){
		return valueType == COMPONENT_TYPE_FLOAT ? (float)value : null;
	}
	
	public Vector3f getVector3f(){
		return valueType == COMPONENT_TYPE_VECTOR3? (Vector3f)value : null;
	}
	
	public ComponentValue copy(){
		return new ComponentValue(valueKey, valueType, value);
	}
	
	public String toString(){
		String type = valueType == COMPONENT_TYPE_FLOAT ? "float" : valueType == COMPONENT_TYPE_INT ? "int" : valueType == COMPONENT_TYPE_STRING ? "string" : "";
		return valueKey + " : " + type + ": " + value;
	}
}
