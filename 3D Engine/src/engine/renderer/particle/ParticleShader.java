package engine.renderer.particle;

import engine.renderer.ShaderProgram;
import engine.utils.Matrix4f;

public class ParticleShader extends ShaderProgram {

	private static final String VERTEX_FILE = "src/engine/shader/particleVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/engine/shader/particleFragmentShader.txt";

	private int location_numberOfRows;
	private int location_projectionMatrix;

	public ParticleShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_numberOfRows = super.getUniformLocation("numberOfRows");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "modelViewMatrix");
		super.bindAttribute(5, "texOffsets");
		super.bindAttribute(6, "blendFactor");
	}

	protected void loadProjectionMatrix(Matrix4f projectionMatrix) {
		super.loadMatrix(location_projectionMatrix, projectionMatrix);
	}
	
	protected void loadNumberOfRows(float f){
		super.loadFloat(location_numberOfRows, f);
	}

}
