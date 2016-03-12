package engine.renderer.gui;

import engine.utils.Vector2f;
import engine.utils.Vector4f;

public class GUITexture {

	private GUIWindow parentWindow;
	
	private GUITextureMap map;
	private int screenPosX;
	private int screenPosY;
	
	private int texturePosX;
	private int texturePosY;
	
	private int textureSizeX;
	private int textureSizeY; 
	
	private GuiAnchor anchor;
	
	private Vector4f color = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	private Vector2f scale = new Vector2f(1.0f, 1.0f);
	
	public GUITexture(GUITextureMap map, GuiAnchor anchor, int screenPosX, int screenPosY){
		this(map, anchor, screenPosX, screenPosY, 0, 0, map.getTextureSize(), map.getTextureSize());
	}
	
	public GUITexture(GUITextureMap map, GuiAnchor anchor, int screenPosX, int screenPosY, int texturePosX, int texturePosY, int textureSizeX, int textureSizeY){
		this.map = map;
		this.anchor = anchor;
		this.texturePosX = texturePosX;
		this.texturePosY = texturePosY;
		this.textureSizeX = textureSizeX;
		this.textureSizeY = textureSizeY;
		setPosition(screenPosX, screenPosY);
	}
	
	
	
	public void setPosition(int x, int y){
		int offsetX = (int) ((anchor.offsetX * (float)textureSizeX) * scale.x);
		int offsetY = (int) ((anchor.offsetY * (float)textureSizeY) * scale.y);
		this.screenPosX = x - offsetX;
		this.screenPosY = y + offsetY;
	}
	
	public void setScale(float x, float y){
		scale.set(x, y);
	}
	
	public int getTextureID(){
		return map.getTextureID();
	}
	
	public float getNormalizedScreenPosX(float displayWidth){
		return (float) (screenPosX + getWindowPositionX())/ displayWidth * 2.0f - 1.0f;
	}
	
	public float getNormalizedScreenPosY(float displayHeight){
		return (float) (screenPosY + getWindowPositionY())/ displayHeight * 2.0f - 1.0f;
	}
	
	public float getNormalizedTexturePosX(){
		return (float) texturePosX / (float)map.getTextureSize();
	}
	
	public float getNormalizedTexturePosY(){
		return (float) texturePosY / (float)map.getTextureSize();
	}
	
	public float getNormalizedTexturePosX1(){
		return (float)(textureSizeX) / (float)map.getTextureSize();
	}
	
	public float getNormalizedTexturePosY1(){
		return (float)(textureSizeY) / (float)map.getTextureSize();
	}
	
	public float getNormalizedTextureSizeX(float displayWidth){
		return (float)textureSizeX / (float)displayWidth * 2.0f * scale.x;
	}
	
	public float getNormalizedTextureSizeY(float displayHeight){
		return (float)textureSizeY / (float)displayHeight * 2.0f * scale.y;
	}
	
	public boolean hasParentWindow(){
		return parentWindow != null;
	}
	
	public int getWindowPositionX(){
		return hasParentWindow() ? parentWindow.getWindowPositionX() : 0;
	}
	
	public int getWindowPositionY(){
		return hasParentWindow() ? parentWindow.getWindowPositionY() : 0;
	}
	
	public GUITexture setColor(float r, float g, float b){
		return setColor(r, g, b, 1.0f);
	}
	
	public GUITexture setColor(float r, float g, float b, float a){
		this.color.set(r, g, b, a);
		return this;
	}
	
	public Vector4f getColor(){
		return color;
	}
	
	public void setParentWindow(GUIWindow window){
		parentWindow = window;
	}
}
