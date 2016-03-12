package engine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
 
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class DisplayTest {

	public static int DISPLAY_WIDTH = 1024;
	public static int DISPLAY_HEIGHT = 800;
	
	// The window handle
	private long window;

	public void run(){
		System.out.println("LWJGL " + Version.getVersion() + "!");

		try{
			init();
			loop();
			
			glfwDestroyWindow(window);
			keyCallback.release();
		} finally {
			glfwTerminate();
			errorCallback.release();
		}
	}

	private void init(){
		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
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

	private void loop(){
		GL.createCapabilities();
		glClearColor(1.0f, 1.0f, 0.0f, 0.0f);
		
		while(glfwWindowShouldClose(window) == GLFW_FALSE){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}
	
	public static void main(String[] args) {
		new DisplayTest().run();
	}

}
