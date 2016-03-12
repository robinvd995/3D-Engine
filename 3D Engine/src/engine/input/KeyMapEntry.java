package engine.input;

public class KeyMapEntry {

	private int key;
	private boolean isMouseKey;
	
	public KeyMapEntry(int key, boolean isMouseKey){
		this.key = key;
		this.isMouseKey = isMouseKey;
	}
	
	public void setKey(int key, boolean isMouseKey){
		this.key = key;
		this.isMouseKey = isMouseKey;
	}
	
	public int getKey(){
		return key;
	}
	
	public boolean isMouseKey(){
		return isMouseKey;
	}
}