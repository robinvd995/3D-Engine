package engine.renderer.gui;

public class GUIWindowStyle {
	
	public final GUITextureMap texture;
	
	public final int windowPosX;
	public final int windowPosY;
	public final int windowSizeX;
	public final int windowSizeY;
	public final int headerHeight;
	public final int headerCornerWidth;
	public final int borderWidth;
	public final int bottomHeight;
	public final int bottomCornerWidth;
	
	private int buttonPosX;
	private int buttonPosY;
	private int buttonSizeX;
	private int buttonSizeY;
	
	public GUIWindowStyle(GUITextureMap texture, int windowPosX, int windowPosY, int windowSizeX, int windowSizeY, int headerHeight, int headerCornerWidth, int borderWidth, int bottomHeight, int bottomCornerWidth) {
		this.texture = texture;
		this.windowPosX = windowPosX;
		this.windowPosY = windowPosY;
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
		this.headerHeight = headerHeight;
		this.headerCornerWidth = headerCornerWidth;
		this.borderWidth = borderWidth;
		this.bottomHeight = bottomHeight;
		this.bottomCornerWidth = bottomCornerWidth;
	}
	
	public void setButtonInfo(int buttonPosX, int buttonPosY, int buttonSizeX, int buttonSizeY){
		this.buttonPosX = buttonPosX;
		this.buttonPosY = buttonPosY;
		this.buttonSizeX = buttonSizeX;
		this.buttonSizeY = buttonSizeY;
	}
	
	public int getIdleButtonPosX(){
		return buttonPosX;
	}
	
	public int getIdleButtonPosY(){
		return buttonPosY;
	}
	
	public int getHoveringButtonPosX(){
		return buttonPosX;
	}
	
	public int getHoveringButtonPosY(){
		return buttonPosY + buttonSizeY;
	}
	
	public int getPressedButtonPosX(){
		return buttonPosX + buttonSizeX;
	}
	
	public int getPressedButtonPosY(){
		return buttonPosY;
	}
	
	public int getDisabledButtonPosX(){
		return buttonPosX + buttonSizeX;
	}
	
	public int getDisabledButtonPosY(){
		return buttonPosY + buttonSizeY;
	}
}