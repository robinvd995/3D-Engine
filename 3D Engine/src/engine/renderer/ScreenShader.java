package engine.renderer;

public class ScreenShader extends ShaderProgram{

	private static final String VERTEX_FILE = "src/engine/shader/screenVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/engine/shader/screenFragmentShader.txt";
	
	private int location_gammaValue;
	
	public ScreenShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_gammaValue = super.getUniformLocation("gamma");
	}

	@Override
	protected void bindAttributes() {
		this.bindAttribute(0, "position");
		this.bindAttribute(1, "texCoords");
	}

	public void loadGammaCorrection(float gamma){
		super.loadFloat(location_gammaValue, gamma);
	}
}
