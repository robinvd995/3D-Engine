package engine.renderer.font;

import engine.renderer.ShaderProgram;
import engine.utils.Vector2f;
import engine.utils.Vector3f;

public class FontShader extends ShaderProgram{

	private static final String VERTEX_FILE = "src/engine/shader/fontVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/engine/shader/fontFragmentShader.txt";
	
	private int location_color;
	private int location_translation;
	private int location_width;
	private int location_edge;
	
	private int location_borderColor;
	private int location_borderWidth;
	private int location_borderEdge;
	
	public FontShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_color = super.getUniformLocation("color");
		location_translation = super.getUniformLocation("translation");
		location_width = super.getUniformLocation("width");
		location_edge = super.getUniformLocation("edge");
		
		location_borderColor = super.getUniformLocation("borderColor");
		location_borderWidth = super.getUniformLocation("borderWidth");
		location_borderEdge = super.getUniformLocation("borderEdge");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	protected void loadColor(Vector3f color){
		super.loadVector(location_color, color);
	}
	
	protected void loadTranslation(Vector2f translation){
		super.load2DVector(location_translation, translation);
	}
	
	protected void loadCharacterData(float w, float e){
		super.loadFloat(location_width, w);
		super.loadFloat(location_edge, e);
	}
	
	protected void loadBorderData(Vector3f color, float w, float e){
		super.loadVector(location_borderColor, color);
		super.loadFloat(location_borderWidth, w);
		super.loadFloat(location_borderEdge, e);
	}
}
