package engine.renderer;

import engine.Scene;
import engine.component.ComponentBase;
import engine.component.ComponentData;

public class ComponentGuiTexture extends ComponentBase {

	public static final String COMPONENT_KEY = "componentguitexture";
	
	public ComponentGuiTexture(){
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public String componentID() {
		return COMPONENT_KEY;
	}

	public void onAddedToScene(Scene scene){
		
	}

	public void onRemovedFromScene(Scene scene){
		
	}
	
	@Override
	public void loadComponentData(ComponentData data) {
		
	}

	@Override
	public ComponentData writeComponentData() {
		return null;
	}
}
