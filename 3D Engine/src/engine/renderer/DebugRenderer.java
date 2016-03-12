package engine.renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.MainGameLoop;
import engine.geometry.BasicShapes;
import engine.physics.Collider;
import engine.physics.Ray;
import engine.utils.MathHelper;
import engine.utils.Matrix4f;
import engine.utils.Quaternion;
import engine.utils.Vector3f;

public class DebugRenderer {

	private static RawModel cubeModel;
	private static DynamicModel lineModel;
	private static LineShader lineShader;
	
	private static List<Collider> collidersToDraw;
	private static List<Ray> raysToDraw;
	
	public static void init(){
		cubeModel = MainGameLoop.theLoader.loadToVAO(BasicShapes.CUBE_VERTICES, BasicShapes.CUBE_INDICES);
		lineModel = MainGameLoop.theLoader.loadLine(BasicShapes.LINE_VERTICES, BasicShapes.LINE_INDICES);
		lineShader = new LineShader();
		lineShader.start();
		lineShader.loadProjectionMatrix(MainGameLoop.theMasterRenderer.getProjectionMatrix());
		lineShader.stop();
		
		collidersToDraw = new ArrayList<Collider>();
		raysToDraw = new ArrayList<Ray>();
	}
	
	public static void render(Camera camera){
		lineShader.start();	
		lineShader.loadViewMatrix(camera);
		renderColliders();
		renderRays();
		lineShader.stop();
		clearRays();
		collidersToDraw.clear();
	}
	
	private static void renderColliders(){
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		lineShader.loadColor(new Vector3f(0.0f, 1.0f, 0.0f));
		GL30.glBindVertexArray(cubeModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		for(Collider c : collidersToDraw){
			float xScale = c.getAABBSizeX();
			float yScale = c.getAABBSizeY();
			float zScale = c.getAABBSizeZ();
			Matrix4f transformationMatrix = MathHelper.createTransformationMatrix(c.getWorldPosition(), xScale, yScale, zScale, new Quaternion());
			lineShader.loadTransformationMatrix(transformationMatrix);
			GL11.glDrawElements(GL11.GL_LINE_STRIP, cubeModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		}
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	
	private static void renderRays(){
		GL30.glBindVertexArray(lineModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		lineShader.loadColor(new Vector3f(0.0f, 0.0f, 1.0f));
		for(Ray ray : raysToDraw){
			Vector3f rayStartPos = MathHelper.add(MathHelper.multVector3f(ray.getDirection(), ray.getMin()), ray.getOrigin());
			Vector3f rayEndPos = MathHelper.add(MathHelper.multVector3f(ray.getDirection(), ray.getMax()), ray.getOrigin());
			float[] vertices = new float[6];
			vertices[0] = rayStartPos.x;
			vertices[1] = rayStartPos.y;
			vertices[2] = rayStartPos.z;
			vertices[3] = rayEndPos.x;
			vertices[4] = rayEndPos.y;
			vertices[5] = rayEndPos.z;
			MainGameLoop.theLoader.updateVBOData(lineModel.getVBO(), vertices);
			Matrix4f transformationMatrix = MathHelper.createTransformationMatrix(new Vector3f(0.0f, 0.0f, 0.0f), 1.0f, 1.0f, 1.0f, new Quaternion());
			lineShader.loadTransformationMatrix(transformationMatrix);
			GL11.glDrawElements(GL11.GL_LINES, 2, GL11.GL_UNSIGNED_INT, 0);
		}
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	private static void clearRays(){
		raysToDraw.clear();
	}
	
	public static void addRayToDraw(Ray ray){
		/**/
		raysToDraw.add(ray);
	}
	
	public static void addCollidersToDraw(Collection<Collider> col){
		collidersToDraw.addAll(col);
	}
	
	public static void addColliderToDraw(Collider c){
		collidersToDraw.add(c);
	}
	
	public static void cleanUp(){
		lineShader.cleanUp();
	}
}
