package engine.renderer;

import engine.component.ComponentBase;
import engine.component.ComponentData;
import engine.utils.Vector4f;

public class RenderComponent extends ComponentBase{

	public static final String COMPONENT_KEY = "renderer";
	
	private TexturedModel model;
	protected float scale;
	protected Vector4f color;
	
	public RenderComponent(TexturedModel m, float s){
		this(m, s, new Vector4f(1.0f, 1.0f, 1.0f, 1.0f));
	}
	
	public RenderComponent(TexturedModel m, float s, Vector4f color){
		this.model = m;
		this.scale = s;
		this.color = color;
	}

	public TexturedModel getModel() {
		return model;
	}
	
	public float getScale() {
		return scale;
	}
	
	public Vector4f getColor(){
		return new Vector4f(color.x, color.y, color.z, color.w);
	}
	
	public void setColor(float r, float g, float b, float a){
		color.set(r, g, b, a);
	}

	@Override
	public void update() {}

	@Override
	public String componentID() {
		return COMPONENT_KEY;
	}

	public boolean shouldRender(){
		return true;
	}
	
	@Override
	public void loadComponentData(ComponentData data) {
		
	}

	@Override
	public ComponentData writeComponentData() {
		return null;
	}
}
