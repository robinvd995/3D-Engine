/*package engine.renderer.gui.old;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

public class GuiTexture {

	private int texture;
	private int textureSize;
	private Vector2f position;
	private Vector2f scale;
	private Vector4f color;
	
	public GuiTexture(int texture, Vector2f position, int textureSize) {
		this(texture, position, textureSize, new Vector4f(1.0f, 1.0f, 1.0f, 1.0f));
	}
	
	public GuiTexture(int texture, Vector2f position, int textureSize, Vector4f color){
		this.texture = texture;
		this.position = position;
		this.color = color;
		this.textureSize = textureSize;
		this.scale = getRealTextureSize();
	}

	public int getTexture() {
		return texture;
	}

	public Vector2f getPosition() {
		return position;
	}

	public Vector2f getScale() {
		return scale;
	}
	
	public Vector4f getColor() {
		return new Vector4f(color.x, color.y, color.z, color.w);
	}
	
	public void setColor(float r, float g, float b, float a) {
		color.set(r, g, b, a);
	}
	
	public void setPosition(float x, float y){
		position.x = x;
		position.y = y;
	}
	
	public Vector2f getRealTextureSize(){
		return new Vector2f((float)(textureSize) / (float)(Display.getWidth()), (float)(textureSize) / (float)(Display.getHeight()));
	}
}*/