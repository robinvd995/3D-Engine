package engine.input;

import java.util.HashMap;

public class KeyMap {
	
	private HashMap<InputAction,KeyMapEntry> keyMap = new HashMap<InputAction,KeyMapEntry>();
	
	public void loadDefaultValues(){
		InputAction[] values = InputAction.values();
		for(int i = 0; i < values.length; i++){
			InputAction action = values[i];
			keyMap.put(action, new KeyMapEntry(action.defaultKey, action.isMouseButton));
		}
	}
	
	public KeyMapEntry getEntry(InputAction action){
		return keyMap.get(action);
	}
}
