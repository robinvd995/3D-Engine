package engine.renderer.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.MainGameLoop;
import engine.renderer.DisplayManager;
import engine.renderer.RawModel;
import engine.utils.MathHelper;
import engine.utils.Matrix4f;
import engine.utils.Vector2f;
import engine.utils.Vector4f;

public class GuiRenderer {

	private final RawModel quad;
	private GuiShader shader;
	
	public GuiRenderer(){
		float[] positions = {0, 1, 0, 0, 1, 1, 1, 0};
		//float[] positions = {0,1,1,1,0,0,1,0};
		quad = MainGameLoop.theLoader.loadToVAO(positions, 2);
		shader = new GuiShader();
	}
	
	public void render(List<GUITexture> textures){
		shader.start();
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		for(GUITexture texture : textures){
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
			float texturePosX = texture.getNormalizedScreenPosX(DisplayManager.DISPLAY_WIDTH);
			float texturePosY = -1.0f * texture.getNormalizedScreenPosY(DisplayManager.DISPLAY_HEIGHT);
			Matrix4f matrix = MathHelper.createTransformationMatrix(new Vector2f(texturePosX, texturePosY), new Vector2f(texture.getNormalizedTextureSizeX(DisplayManager.DISPLAY_WIDTH), texture.getNormalizedTextureSizeY(DisplayManager.DISPLAY_HEIGHT)));
			Vector4f textureCoords = new Vector4f(texture.getNormalizedTexturePosX(), texture.getNormalizedTexturePosY(), texture.getNormalizedTexturePosX1(), texture.getNormalizedTexturePosY1());
			shader.loadTransformation(matrix);
			shader.loadTextureCoords(textureCoords);
			shader.loadColor(texture.getColor());
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
		}
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}
}
