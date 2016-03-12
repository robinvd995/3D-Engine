package engine.renderer.gui;

import java.util.ArrayList;
import java.util.List;

import engine.renderer.font.TextMaster;

public class GUIWindow {
	
	private GUIWindowStyle windowStyle;
	
	private int windowPosX;
	private int windowPosY;
	private int windowSizeX;
	private int windowSizeY;
	
	private List<GUITexture> windowTextures;
	private List<GUIText> windowText;
	
	private boolean isDragged = false;
	
	public GUIWindow(GUIWindowStyle windowStyle, String windowName, int posX, int posY, int sizeX, int sizeY){
		this.windowStyle = windowStyle;
		this.windowPosX = posX;
		this.windowPosY = posY;
		this.windowSizeX = sizeX;
		this.windowSizeY = sizeY;
		
		initWindowTextures();
		initWindowText(windowName);
	}
	
	public void updateWindow(int mouseX, int mouseY){
		
		/*if(!isDragged && Mouse.isButtonDown(0)){
			if(mouseX > windowPosX && mouseX < windowPosX + windowSizeX && mouseY > windowPosY && mouseY < windowPosY + windowStyle.headerHeight){
				System.out.println("Header Clicked!");
			}
		}*/
	}
	
	/*public void onMouseClicked(int mouseX, int mouseY){
		
	}*/
	
	private void initWindowText(String s){
		
		windowText = new ArrayList<GUIText>();
		
		GUIText header = new GUIText(s, 0.75f, TextMaster.candara, windowStyle.headerCornerWidth + 4, 3, 1.0f, false).setColor(1.0f, 1.0f, 1.0f);
		addWindowText(header);
	}
	
	private void initWindowTextures(){
		windowTextures = new ArrayList<GUITexture>();
		
		int x0, x1, x2, y0, y1, y2;
		
		x0 = 0;//windowPosX;
		y0 = 0;//windowPosY;
		x1 = windowStyle.windowPosX;
		y1 = windowStyle.windowPosY;
		x2 = windowStyle.headerCornerWidth;
		y2 = windowStyle.headerHeight;
		GUITexture topLeftCorner = new GUITexture(windowStyle.texture, GuiAnchor.TOP_LEFT, x0, y0, x1, y1, x2, y2);
		addWindowTexture(topLeftCorner);
		
		x0 = windowSizeX;
		x1 = windowStyle.windowPosX + windowStyle.windowSizeX - windowStyle.headerCornerWidth;
		GUITexture topRightCorner = new GUITexture(windowStyle.texture, GuiAnchor.TOP_RIGHT, x0, y0, x1, y1, x2, y2);
		addWindowTexture(topRightCorner);
		
		x0 = windowStyle.headerCornerWidth;
		x1 = windowStyle.windowPosX + windowStyle.headerCornerWidth;
		int textureSize = windowStyle.windowSizeX - windowStyle.headerCornerWidth * 2;
		int remainingSpace = windowSizeX - windowStyle.headerCornerWidth * 2;
		int drawSize;
		int i = 0;
		while(remainingSpace > 0){
			drawSize = textureSize;
			if(drawSize > remainingSpace){
				drawSize = remainingSpace;
			}
			addWindowTexture(new GUITexture(windowStyle.texture, GuiAnchor.TOP_LEFT, x0 + textureSize * i, y0, x1, y1, drawSize, y2));
			i++;
			remainingSpace -= drawSize;
		}
		
		x0 = 0;
		y0 = windowStyle.headerHeight;
		x1 = windowStyle.windowPosX;
		y1 = windowStyle.windowPosY + windowStyle.headerHeight;
		x2 = windowStyle.borderWidth;
		textureSize = windowStyle.windowSizeY - windowStyle.headerHeight - windowStyle.bottomHeight;
		remainingSpace = windowSizeY - windowStyle.headerHeight - windowStyle.bottomHeight;
		i = 0;
		while(remainingSpace > 0){
			drawSize = textureSize;
			if(drawSize > remainingSpace){
				drawSize = remainingSpace;
			}
			addWindowTexture(new GUITexture(windowStyle.texture, GuiAnchor.TOP_LEFT, x0, y0 + textureSize * i, x1, y1, x2, drawSize));
			i++;
			remainingSpace -= drawSize;
		}
		
		x0 = windowSizeX;
		x1 = windowStyle.windowPosX + windowStyle.windowSizeX - windowStyle.borderWidth;
		remainingSpace = windowSizeY - windowStyle.headerHeight - windowStyle.bottomHeight;
		i = 0;
		while(remainingSpace > 0){
			drawSize = textureSize;
			if(drawSize > remainingSpace){
				drawSize = remainingSpace;
			}
			addWindowTexture(new GUITexture(windowStyle.texture, GuiAnchor.TOP_RIGHT, x0, y0 + textureSize * i, x1, y1, x2, drawSize));
			i++;
			remainingSpace -= drawSize;
		}
		
		x0 = 0;
		y0 = windowSizeY;
		x1 = windowStyle.windowPosX;
		y1 = windowStyle.windowPosX + windowStyle.windowSizeY - windowStyle.bottomHeight;
		x2 = windowStyle.bottomCornerWidth;
		y2 = windowStyle.bottomHeight;
		GUITexture bottomLeftCorner = new GUITexture(windowStyle.texture, GuiAnchor.BOTTOM_LEFT, x0, y0, x1, y1, x2, y2);
		addWindowTexture(bottomLeftCorner);
		
		x0 = windowSizeX;
		x1 = windowStyle.windowPosX + windowStyle.windowSizeX - windowStyle.bottomCornerWidth;
		GUITexture bottomRightCorner = new GUITexture(windowStyle.texture, GuiAnchor.BOTTOM_RIGHT, x0, y0, x1, y1, x2, y2);
		addWindowTexture(bottomRightCorner);
		
		x0 = windowStyle.bottomCornerWidth;
		x1 = windowStyle.windowPosX + windowStyle.bottomCornerWidth;
		textureSize = windowStyle.windowSizeX - windowStyle.bottomCornerWidth * 2;
		remainingSpace = windowSizeX - windowStyle.bottomCornerWidth * 2;
		i = 0;
		while(remainingSpace > 0){
			drawSize = textureSize;
			if(drawSize > remainingSpace){
				drawSize = remainingSpace;
			}
			addWindowTexture(new GUITexture(windowStyle.texture, GuiAnchor.BOTTOM_LEFT, x0 + textureSize * i, y0, x1, y1, drawSize, y2));
			i++;
			remainingSpace -= drawSize;
		}
		
		x0 = windowStyle.borderWidth;
		y0 = windowStyle.headerHeight;
		x1 = windowStyle.windowPosX + windowStyle.borderWidth;
		y1 = windowStyle.windowPosY + windowStyle.headerHeight;
		
		int textureSizeX = windowStyle.windowSizeX - windowStyle.borderWidth * 2;
		int textureSizeY = windowStyle.windowSizeY - windowStyle.headerHeight - windowStyle.bottomHeight;
		int remainingX = windowSizeX - windowStyle.borderWidth * 2;
		int remainingY = windowSizeY - windowStyle.headerHeight - windowStyle.bottomHeight;
		
		int drawSizeX;
		int drawSizeY;
		int x = 0;
		int y = 0;
		
		while(remainingX > 0){
			drawSizeX = textureSizeX;
			if(drawSizeX > remainingX){
				drawSizeX = remainingX;
			}
			while(remainingY > 0){
				drawSizeY = textureSizeY;
				if(drawSizeY > remainingY){
					drawSizeY = remainingY;
				}
				addWindowTexture(new GUITexture(windowStyle.texture, GuiAnchor.TOP_LEFT, x0 + textureSizeX * x, y0 + textureSizeY * y, x1, y1, drawSizeX, drawSizeY));
				y++;
				remainingY -= drawSizeY;
				System.out.println("looped y");
			}
			y = 0;
			remainingY = windowSizeY - windowStyle.headerHeight - windowStyle.bottomHeight;
			x++;
			remainingX -= drawSizeX;
			System.out.println("looped x");
		}
	}
	
	public void updateWindowPosition(int x, int y){
		windowPosX = x;
		windowPosY = y;
	}
	
	public int getWindowPositionX(){
		return windowPosX;
	}
	
	public int getWindowPositionY(){
		return windowPosY;
	}
	
	private void addWindowTexture(GUITexture texture){
		texture.setParentWindow(this);
		windowTextures.add(texture);
	}
	
	private void addWindowText(GUIText text){
		text.setParentWindow(this);
		windowText.add(text);
	}
	
	public List<GUITexture> getTextures(){
		return windowTextures;
	}
	
	public List<GUIText> getText(){
		return windowText;
	}
}