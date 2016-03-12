package engine.renderer.gui;

public class GUITextureMap {
	
	private int textureID;
	private int textureSize;
	
	public GUITextureMap(int textureID, int textureSize){
		this.textureID = textureID;
		this.textureSize = textureSize;
	}
	
	public int getTextureID(){
		return textureID;
	}
	
	public int getTextureSize(){
		return textureSize;
	}
}
