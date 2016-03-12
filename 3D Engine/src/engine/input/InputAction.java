package engine.input;

import static org.lwjgl.glfw.GLFW.*;

public enum InputAction {
	
	MOVE_LEFT(GLFW_KEY_A, false),
	MOVE_RIGHT(GLFW_KEY_D, false),
	MOVE_UP(GLFW_KEY_W, false),
	MOVE_DOWN(GLFW_KEY_S, false),
	FIRE1(0, true),
	FIRE2(1, true);
	
	public final int defaultKey;
	public final boolean isMouseButton;
	
	private InputAction(int defaultKey, boolean mouse){
		this.defaultKey = defaultKey;
		this.isMouseButton = mouse;
	}
	
	public static int length(){
		return values().length;
	}
}
