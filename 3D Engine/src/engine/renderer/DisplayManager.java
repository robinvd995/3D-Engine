package engine.renderer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.DisplayMode;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

import engine.utils.Vector2f;

public class DisplayManager {

	public static DisplayManager manager;
	
	public static int DISPLAY_WIDTH = 1024;
	public static int DISPLAY_HEIGHT = 800;
	
	public static GLFWErrorCallback errorCallback;
	public static GLFWKeyCallback   keyCallback;
	
	public long window;
	
	public static void initDisplay(){
		manager = new DisplayManager();
		manager.init();
	}
	
	private void init(){
		if(glfwInit() != GLFW_TRUE){
			throw new IllegalStateException("Unable to initialize GLFW!");
		}

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		window = glfwCreateWindow(DISPLAY_WIDTH, DISPLAY_HEIGHT, "Display Test!", NULL, NULL);
		if(window == NULL)
			throw new RuntimeException("Failed to create the GLFW Window!");

		glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback(){
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				System.out.println("key= " + key + ", scancode= " + scancode + ", action=" + action + ", mods= " + mods);
			}
		});

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(
				window,
				(vidmode.width() - DISPLAY_WIDTH) / 2,
				(vidmode.height() - DISPLAY_HEIGHT) / 2
				);

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);
	}
	
	public void swapBuffer(){
		glfwSwapBuffers(window);
	}

	public void cleanUp(){
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	/*public static void createDisplay(){

		ContextAttribs attribs = new ContextAttribs(3,3).withForwardCompatible(true).withProfileCore(true);

		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("3D Space Shooter");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		GL11.glViewport(0, 0, WIDTH, HEIGHT);
	}

	public static void updateDisplay(){
		Display.sync(60);
		Display.update();
	}

	public static void closeDisplay(){
		Display.destroy();
	}*/

	public static Vector2f getNormalizedDeviceCoords(float x, float y){
		float nx = (2.0f * x) / DISPLAY_WIDTH - 1.0f;
		float ny = (2.0f * y) / DISPLAY_HEIGHT - 1.0f;
		return new Vector2f(nx, ny);
	}
}