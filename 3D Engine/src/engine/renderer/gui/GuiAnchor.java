package engine.renderer.gui;

public enum GuiAnchor {

	TOP_LEFT(0.0f, 1.0f),
	TOP_RIGHT(1.0f, 1.0f),
	BOTTOM_LEFT(0.0f, 0.0f),
	BOTTOM_RIGHT(1.0f, 0.0f),
	CENTER(0.5f, 0.5f);
	
	public final float offsetX;
	public final float offsetY;
	
	private GuiAnchor(float offsetX, float offsetY){
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
}
