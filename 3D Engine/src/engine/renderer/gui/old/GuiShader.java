/*package engine.renderer.gui.old;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import engine.renderer.ShaderProgram;

public class GuiShader extends ShaderProgram{

	private static final String VERTEX_FILE = "src/engine/shader/guiVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/engine/shader/guiFragmentShader.txt";

	private int location_transformationMatrix;
	private int location_color;

	public GuiShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	public void loadTransformation(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_color = super.getUniformLocation("color");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	public void loadColor(Vector4f color){
		super.load4DVector(location_color, color);
	}
}*/