package engine;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.lwjgl.glfw.GLFW;

public class Time {

	private static long lastFrameTime;
	private static float delta;
	
	private static long getCurrentTime(){
		return (long) GLFW.glfwGetTime();
	}
	
	public static void init(){
		lastFrameTime = getCurrentTime();
	}
	
	public static void updateTime(){
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime) / 1000.0f;
		lastFrameTime = currentFrameTime;
	}
	
	public static float getDeltaTime(){
		return 0.01f;
	}
	
	public static String getTimeFormat(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime());
	}
}
