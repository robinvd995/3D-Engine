package engine.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import engine.log.Logger;
import engine.renderer.skybox.SkyboxRenderer;
import engine.utils.AngleHelper;
import engine.utils.Matrix4f;

public class MasterRenderer {

	public static int missingTextureID;
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1F;
	private static final float FAR_PLANE = 1000.0F;
	
	private Matrix4f projectionMatrix;
	private SkyboxRenderer skyboxRenderer;
	
	private StaticShader shader = new StaticShader();
	private Renderer renderer;
	
	private Map<TexturedModel, List<RenderComponent>> entities = new HashMap<TexturedModel, List<RenderComponent>>();
	
	public static final Logger LOG = new Logger("RENDERER", "renderLog");;
	
	public MasterRenderer(Loader loader){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		createProjectionMatrix();
		renderer = new Renderer(shader, projectionMatrix);
		skyboxRenderer = new SkyboxRenderer(loader, projectionMatrix);
	}
	
	public static void prepare(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public void render(List<Light> light, Camera camera){
		shader.start();
		shader.loadLights(light);
		shader.loadViewMatrix(camera);
		renderer.render(entities);
		shader.stop();
		entities.clear();
	}
	
	public void renderSkybox(Camera camera){
		skyboxRenderer.render(camera);
	}
	
	public void processEntity(RenderComponent entity){
		TexturedModel model = entity.getModel();
		List<RenderComponent> batch = entities.get(model);
		if(batch != null){
			batch.add(entity);
		}
		else{
			List<RenderComponent> newBatch = new ArrayList<RenderComponent>();
			newBatch.add(entity);
			entities.put(model, newBatch);
		}
	}
	
	public void cleanUp(){
		shader.cleanUp();
	}
	
	private void createProjectionMatrix(){
		float aspectRatio = (float) DisplayManager.DISPLAY_WIDTH / (float) DisplayManager.DISPLAY_HEIGHT;
		float yScale = ((1F / AngleHelper.tan(AngleHelper.toRadians(FOV / 2F))) * aspectRatio);
		float xScale = yScale / aspectRatio;
		float frustrumLength = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustrumLength);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustrumLength);
		projectionMatrix.m33 = 0;
	}
	
	public Matrix4f getProjectionMatrix(){
		return projectionMatrix;
	}
}
