package engine.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class InputManager {

	public static InputManager manager;

	private long window;
	
	private GLFWKeyCallback keyCallback;
	private GLFWMouseButtonCallback mouseButtonCallback;
	private GLFWCursorPosCallback mousePositionCallback;

	private int mousePosX;
	private int mousePosY;
	
	private KeyMap keyMap = new KeyMap();
	private boolean[] keyDown = new boolean[350];
	private boolean[] mouseDown = new boolean[8];
	
	public static void init(long window){
		if(manager != null)
			return;

		manager = new InputManager(window);
		manager.init();
	}
	
	public static void cleanUp(){
		manager.keyCallback.release();
		manager.mouseButtonCallback.release();
		manager.mousePositionCallback.release();
	}

	public InputManager(long window){
		this.window = window;
	}

	private void init(){
		keyMap.loadDefaultValues();
		initKeyCallback();
		initMousePosCallback();
		initMouseButtonCallback();
	}

	private void initKeyCallback(){
		GLFW.glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback(){

			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if(action == 1){
					keyDown[key] = true;
				}
				if(action == 0){
					keyDown[key] = false;
				}
			}

		});
	}

	private void initMousePosCallback(){
		GLFW.glfwSetCursorPosCallback(window, mousePositionCallback = new GLFWCursorPosCallback(){

			@Override
			public void invoke(long window, double posX, double posY) {
				manager.mousePosX = (int) posX;
				manager.mousePosY = (int) posY;
			}
			
		});
	}

	private void initMouseButtonCallback(){
		GLFW.glfwSetMouseButtonCallback(window, mouseButtonCallback = new GLFWMouseButtonCallback(){

			@Override
			public void invoke(long window, int button, int action, int mods) {
				if(action == 1){
					mouseDown[button] = true;
				}
				if(action == 0){
					mouseDown[button] = false;
				}
			}

		});
	}
	
	public boolean isActionKeyDown(InputAction a){
		KeyMapEntry entry = keyMap.getEntry(a);
		if(entry.isMouseKey()){
			return mouseDown[entry.getKey()];
		}
		else{
			return keyDown[entry.getKey()];
		}
	}

	public int getMouseX(){
		return mousePosX;
	}
	
	public int getMouseY(){
		return mousePosY;
	}

}
