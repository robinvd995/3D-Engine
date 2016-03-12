package engine.renderer.gui;

public enum EnumCrosshair {
	
	GUN(0),
	ROCKET(1);
	
	public final int spriteID;
	
	private EnumCrosshair(int id){
		spriteID = id;
	}
}
