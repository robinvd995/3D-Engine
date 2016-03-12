package engine.renderer;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

import engine.MainGameLoop;
import engine.geometry.BasicShapes;

public class FrameBuffer {

	private int quadVAO;
	
	private int fbo;
	private int colorTextureID;
	private int depthTextureID;
	
	private int textureWidth;
	private int textureHeight;
	
	public FrameBuffer(int w, int h){
		quadVAO = MainGameLoop.theLoader.loadToVAO(BasicShapes.QUAD_VERTICES, BasicShapes.QUAD_TEXTURE_COORDS);
		textureWidth = w;
		textureHeight = h;
		fbo = createFrameBuffer();
		colorTextureID = createColorTextureAttachment(textureWidth, textureHeight);
		depthTextureID = createDepthTextureAttachment(textureWidth, textureHeight);
	}

	public int getColorTexture(){
		return colorTextureID;
	}
	
	public int getDepthTexure(){
		return depthTextureID;
	}
	
	public int getQuadVAO(){
		return quadVAO;
	}
	
	private int createFrameBuffer(){
		int frameBuffer = GL30.glGenFramebuffers();
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
		GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
		return frameBuffer;
	}

	private int createColorTextureAttachment(int width, int height) {
		int texture = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		//HDR rendering: GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB16, width, height, 0, GL11.GL_RGBA, GL11.GL_FLOAT, (ByteBuffer) null);
		//Normal rendering: GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, (ByteBuffer) null);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB16, width, height, 0, GL11.GL_RGBA, GL11.GL_FLOAT, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, texture, 0);
		return texture;
	}

	private int createDepthTextureAttachment(int width, int height){
		int texture = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL14.GL_DEPTH_COMPONENT32, width, height, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, texture, 0);
		return texture;
	}
	
	public void bindFrameBuffer(){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fbo);
		GL11.glViewport(0, 0, textureWidth, textureHeight);
	}
	
	public void unbindCurrentFrameBuffer() {
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
        GL11.glViewport(0, 0, DisplayManager.DISPLAY_WIDTH, DisplayManager.DISPLAY_HEIGHT);
    }
	
	public void cleanUp(){
		GL30.glDeleteFramebuffers(fbo);
		GL11.glDeleteTextures(colorTextureID);
		GL11.glDeleteTextures(depthTextureID);
	}
}
