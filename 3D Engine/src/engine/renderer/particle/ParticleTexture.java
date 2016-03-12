package engine.renderer.particle;

import org.lwjgl.opengl.GL11;

public class ParticleTexture {

	private int textureID;
	private int rows;
	
	private int particleBlendType;
	
	public ParticleTexture(int textureID, int rows){
		this.textureID = textureID;
		this.rows = rows;
		this.setAlphaBlending();
	}
	
	public int getTextureID(){
		return textureID;
	}
	
	public int getRows(){
		return rows;
	}
	
	public ParticleTexture setAlphaBlending(){
		particleBlendType = GL11.GL_ONE_MINUS_SRC_ALPHA;
		return this;
	}
	
	public ParticleTexture setAdditiveBlending(){
		particleBlendType = GL11.GL_ONE;
		return this;
	}
	
	public int getBlendType(){
		return particleBlendType;
	}
	
	public boolean isAdditiveBlending(){
		return particleBlendType == GL11.GL_ONE;
	}
}
