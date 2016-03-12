package engine.renderer;

import engine.utils.Vector2f;

public class GaussianBlurShader extends ShaderProgram{

	private static final String VERTEX_FILE = "src/engine/shader/gblurVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/engine/shader/gblurFragmentShader.txt";
	
	private int location_direction;
	
	public GaussianBlurShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	@Override
	protected void getAllUniformLocations() {
		location_direction = super.getUniformLocation("direction");
	}

	@Override
	protected void bindAttributes() {
		this.bindAttribute(0, "position");
		this.bindAttribute(1, "texCoords");
	}

	public void loadBlurInfo(Vector2f dir){
		super.load2DVector(location_direction, dir);
	}
}
