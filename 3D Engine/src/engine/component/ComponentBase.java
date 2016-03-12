package engine.component;

import engine.GameObject;
import engine.Scene;
import engine.utils.Quaternion;
import engine.utils.Vector3f;

public abstract class ComponentBase implements IComponent{

	protected GameObject parent;
	private boolean active = true;
	
	@Override
	public void setParent(GameObject obj) {
		parent = obj;
	}
	
	public GameObject getParent(){
		return parent;
	}
	
	public Vector3f getPosition(){
		return parent.getPosition();
	}
	
	public Quaternion getRotation(){
		return parent.getRotation();
	}
	
	public boolean shouldRender(){
		return false;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
	public void onAddedToScene(Scene scene){
		
	}
	
	public void onRemovedFromScene(Scene scene){
		
	}
	
	public boolean hasSharedData(){
		return false;
	}
}
