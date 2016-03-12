package engine.geometry;

public class BasicShapes {
	
	public static float[] CUBE_VERTICES = {
		-0.5f, -0.5f, 0.5f, 
		0.5f, -0.5f, 0.5f, 
		0.5f, 0.5f, 0.5f, 
		-0.5f, 0.5f, 0.5f, 
		-0.5f, -0.5f, -0.5f, 
		0.5f, -0.5f, -0.5f, 
		0.5f, 0.5f, -0.5f, 
		-0.5f, 0.5f, -0.5f
	};
	
	public static int[] CUBE_INDICES = {
		0, 1, 2, 2, 3, 0, 
		3, 2, 6, 6, 7, 3, 
		7, 6, 5, 5, 4, 7, 
		4, 0, 3, 3, 7, 4, 
		0, 1, 5, 5, 4, 0,
		1, 5, 6, 6, 2, 1 
	};
	
	public static float[] LINE_VERTICES = {
		0.0f, 0.0f, 0.0f,
		0.0f, 0.0f, 1.0f
	};
	
	public static int[] LINE_INDICES = {
		0, 1
	};
	
	public static float[] QUAD_VERTICES = {
			-1.0f, 1.0f,
			-1.0f, -1.0f,
			1.0f, -1.0f,
			
			1.0f, -1.0f,
			1.0f, 1.0f,
			-1.0f, 1.0f,
	};
	
	public static float[] QUAD_TEXTURE_COORDS = {
			0.0f, 1.0f,
			0.0f, 0.0f,
			1.0f, 0.0f,
			
			1.0f, 0.0f,
			1.0f, 1.0f,
			0.0f, 1.0f
	};
}
