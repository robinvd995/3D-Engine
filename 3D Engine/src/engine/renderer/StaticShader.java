package engine.renderer;

import java.util.List;

import engine.utils.Matrix4f;
import engine.utils.Vector3f;
import engine.utils.Vector4f;

public class StaticShader extends ShaderProgram {

	private static final int MAX_LIGHTS = 4;

	private static final String VERTEX_FILE = "src/engine/shader/staticVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/engine/shader/staticFragmentShader.txt";

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int[] location_lightPosition;
	private int[] location_lightColor;
	private int[] location_attenuation;
	private int location_shineDamper;
	private int location_reflectivity;
	private int location_color;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
		location_color = super.getUniformLocation("color");
		
		location_lightPosition = new int[MAX_LIGHTS];
		location_lightColor = new int[MAX_LIGHTS];
		location_attenuation = new int[MAX_LIGHTS];
		
		for(int i = 0; i < 4; i++){
			location_lightPosition[i] = super.getUniformLocation("lightPosition["+i+"]");
			location_lightColor[i] = super.getUniformLocation("lightColor["+i+"]");
			location_attenuation[i] = super.getUniformLocation("attenuation["+i+"]");
		}
	}

	public void loadColor(Vector4f color){
		super.load4DVector(location_color, color);
	}
	
	public void loadShine(float shine, float reflec){
		super.loadFloat(location_shineDamper, shine);
		super.loadFloat(location_reflectivity, reflec);
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

	public void loadLights(List<Light> lights){
		for(int i = 0; i < MAX_LIGHTS; i++){
			if(i<lights.size()){
				super.loadVector(location_lightPosition[i], lights.get(i).getPosition());
				super.loadVector(location_lightColor[i], lights.get(i).getColor());
				super.loadVector(location_attenuation[i], lights.get(i).getAttenuation());
			}
			else{
				super.loadVector(location_lightPosition[i], new Vector3f(0.0f, 0.0f, 0.0f));
				super.loadVector(location_lightColor[i], new Vector3f(0.0f, 0.0f, 0.0f));
				super.loadVector(location_attenuation[i], new Vector3f(1.0f, 0.0f, 0.0f));
			}
		}
	}
}