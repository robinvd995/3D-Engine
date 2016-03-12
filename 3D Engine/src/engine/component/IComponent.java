package engine.component;

import engine.GameObject;
import engine.Scene;

public interface IComponent {

	void setParent(GameObject obj);
	GameObject getParent();
	void update();
	String componentID();
	boolean shouldRender();
	boolean isActive();
	void setActive(boolean active);
	void onAddedToScene(Scene scene);
	void onRemovedFromScene(Scene scene);
	void loadComponentData(ComponentData data);
	ComponentData writeComponentData();
	boolean hasSharedData();
}
