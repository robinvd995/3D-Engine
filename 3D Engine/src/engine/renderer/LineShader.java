package engine.renderer;

import engine.utils.Matrix4f;
import engine.utils.Vector3f;

public class LineShader extends ShaderProgram {

	private static final String VERTEX_FILE = "src/engine/shader/lineVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/engine/shader/lineFragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_color;
	
	public LineShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_color = super.getUniformLocation("color");
	}

	public void loadColor(Vector3f color){
		super.loadVector(location_color, color);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}

	public void loadProjectionMatrix(Matrix4f matrix){
		super.loadMatrix(location_projectionMatrix, matrix);
	}

	public void loadViewMatrix(Camera camera){
		Matrix4f viewMatrix = camera.createViewMatrix();
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
}
