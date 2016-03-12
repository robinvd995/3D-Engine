package engine.renderer.gui;

import engine.renderer.ShaderProgram;
import engine.utils.Matrix4f;
import engine.utils.Vector4f;

public class GuiShader extends ShaderProgram{

	private static final String VERTEX_FILE = "src/engine/shader/guiVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/engine/shader/guiFragmentShader.txt";

	private int location_transformationMatrix;
	private int location_textureCoords;
	private int location_color;

	public GuiShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_textureCoords = super.getUniformLocation("texCoords");
		location_color = super.getUniformLocation("color");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	public void loadTransformation(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadTextureCoords(Vector4f coords){
		super.load4DVector(location_textureCoords, coords);
	}
	
	public void loadColor(Vector4f color){
		super.load4DVector(location_color, color);
	}
}