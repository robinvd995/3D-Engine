package engine.renderer;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.utils.MathHelper;
import engine.utils.Matrix4f;

public class Renderer {
	
	private StaticShader shader;
	
	public Renderer(StaticShader s, Matrix4f projectionMatrix){
		shader = s;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void render(Map<TexturedModel, List<RenderComponent>> entities){
		for(TexturedModel model : entities.keySet()){
			prepareTexturedModel(model);
			List<RenderComponent> batch = entities.get(model);
			for(RenderComponent entity : batch){
				prepareInstance(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbindTexturedModel();
		}
	}
	
	private void prepareTexturedModel(TexturedModel texturedModel){
		RawModel model = texturedModel.getRawModel();
		ModelTexture texture = texturedModel.getModelTexture();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		shader.loadShine(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getModelTexture().getID());
	}
	
	private void unbindTexturedModel(){
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	private void prepareInstance(RenderComponent object){
		float scale = object.getScale();
		Matrix4f transformationMatrix = MathHelper.createTransformationMatrix(object.getPosition(), scale, scale, scale, object.getRotation());
		shader.loadTransformationMatrix(transformationMatrix);
		shader.loadColor(object.getColor());
	}
}
