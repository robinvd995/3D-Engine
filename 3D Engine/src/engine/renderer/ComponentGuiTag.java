package engine.renderer;

import engine.MainGameLoop;
import engine.Scene;
import engine.component.ComponentBase;
import engine.component.ComponentData;
import engine.renderer.font.TextMaster;
import engine.renderer.gui.GUIText;
import engine.utils.MathHelper;
import engine.utils.Vector3f;

public class ComponentGuiTag extends ComponentBase {

	public static final String COMPONENT_KEY = "componentguitag";

	private GUIText text;
	
	public ComponentGuiTag(){
		
	}

	@Override
	public void update() {
		Vector3f screenPos = MathHelper.worldToScreenSpace(getPosition(), parent.getActiveScene().theCamera().createViewMatrix(), MainGameLoop.theMasterRenderer.getProjectionMatrix());
		text.setPosition((int)(screenPos.x * DisplayManager.DISPLAY_WIDTH), (int)(screenPos.y * DisplayManager.DISPLAY_HEIGHT));
	}

	@Override
	public String componentID() {
		return COMPONENT_KEY;
	}

	public void onAddedToScene(Scene scene){
		Vector3f screenPos = MathHelper.worldToScreenSpace(getPosition(), scene.theCamera().createViewMatrix(), MainGameLoop.theMasterRenderer.getProjectionMatrix());
		text = new GUIText("test label", 1f, TextMaster.candara, (int)(screenPos.x * DisplayManager.DISPLAY_WIDTH), (int)(screenPos.y * DisplayManager.DISPLAY_HEIGHT), 1f, true).setColor(1.0f, 1.0f, 1.0f);
		TextMaster.loadText(text);
	}

	public void onRemovedFromScene(Scene scene){
		TextMaster.removeText(text);
	}
	
	@Override
	public void loadComponentData(ComponentData data) {
		
	}

	@Override
	public ComponentData writeComponentData() {
		return null;
	}
}