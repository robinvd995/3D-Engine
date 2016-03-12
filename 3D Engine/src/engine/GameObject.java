package engine;

import java.util.HashMap;

import engine.component.IComponent;
import engine.physics.Collider;
import engine.renderer.RenderComponent;
import engine.utils.GlobalAxis;
import engine.utils.MathHelper;
import engine.utils.Quaternion;
import engine.utils.Vector3f;

public final class GameObject {
	
	protected GameTag tag = GameTag.NONE;
	
	protected final Scene theScene;
	
	protected final Vector3f position;
	protected final Quaternion rotation;
	
	private boolean isStatic = false;
	private HashMap<String,IComponent> components;

	private boolean hasRenderer = false;
	
	protected HashMap<String,String> sharedData;
	
	public GameObject(Scene scene){
		this(scene, new Vector3f(0.0f, 0.0f, 0.0f));
	}

	public GameObject(Scene scene, Vector3f position){
		this(scene, position, new Quaternion());
	}

	public GameObject(Scene scene, Vector3f position, Vector3f rotation){
		this(scene, position, Quaternion.fromVector(rotation));
	}
	
	public GameObject(Scene scene, Vector3f position, Quaternion rotation){
		this.theScene = scene;
		this.position = position;
		this.rotation = rotation;
		components = new HashMap<String,IComponent>();
	}
	
	public void updateGameObject(){
		for(IComponent comp : components.values()){
			if(comp.isActive()){
				comp.update();
			}
		}
	}
	
	public Vector3f getPosition(){
		return new Vector3f(position.x, position.y, position.z);
	}
	
	public Quaternion getRotation(){
		return rotation.copy();
	}

	public void setPosition(Vector3f pos){
		position.x = pos.x;
		position.y = pos.y;
		position.z = pos.z;
	}
	
	public void setPosition(float x, float y, float z){
		position.x = x;
		position.y = y;
		position.z = z;
	}

	public void setRotation(float x, float y, float z){
		rotation.reset();
		rotation.mult(new Quaternion((float)Math.toRadians(x), GlobalAxis.X.toVector()));
		rotation.mult(new Quaternion((float)Math.toRadians(y), GlobalAxis.Y.toVector()));
		rotation.mult(new Quaternion((float)Math.toRadians(z), GlobalAxis.Z.toVector()));
	}

	public void setRotation(Quaternion q){
		rotation.set(q);
		rotation.normalize();
	}

	public void moveGlobal(float dx, float dy, float dz){
		position.x += dx;
		position.y += dy;
		position.z += dz;
	}

	public void moveForward(float amount){
		moveLocal(amount, GlobalAxis.Z.toVector(amount));
	}
	
	public void moveRight(float amount){
		moveLocal(amount, GlobalAxis.X.toVector(amount));
	}
	
	public void moveUp(float amount){
		moveLocal(amount, GlobalAxis.Y.toVector(amount));
	}
	
	protected void moveLocal(float amount, Vector3f direction){
		Quaternion rotationCopy = rotation.copy();
		Vector3f delta = new Vector3f(rotationCopy.mult(direction));
		moveGlobal(delta.x, delta.y, delta.z);
	}

	public void rotateLocal(float amount, Vector3f angle){
		rotation.mult(new Quaternion((float)Math.toRadians(amount), angle));
		rotation.normalize();
	}

	//Not yet working
	public void rotateGlobal(float amount, Vector3f angle){
		
	}
	
	public void lerp(Vector3f target, float percent){
		Vector3f newPos = new Vector3f(MathHelper.lerp(position.x, target.x, percent), MathHelper.lerp(position.y, target.y, percent), MathHelper.lerp(position.z, target.z, percent));
		setPosition(newPos);
	}
	
	public void slerp(Quaternion target, float percent){
		Quaternion quad = Quaternion.slerp(percent, getRotation(), target);
		rotation.set(quad);
	}
	
	public GameObject addComponent(IComponent comp){
		components.put(comp.componentID(), comp);
		comp.setParent(this);
		if(comp.shouldRender()){
			hasRenderer = true;
		}
		if(comp.hasSharedData()){
			initSharedData();
		}
		return this;
	}
	
	public IComponent getComponent(String key){
		return components.get(key);
	}
	
	public boolean shouldRender(){
		if(!hasRenderer) return false;
		return components.get(RenderComponent.COMPONENT_KEY).isActive();
	}
	
	public GameObject makeStatic(){
		this.isStatic = true;
		return this;
	}
	
	public boolean isStatic(){
		return this.isStatic;
	}
	
	public Vector3f getRelativePosition(Vector3f delta){
		Vector3f deltaX = new Vector3f(getRotation().mult(GlobalAxis.X.toVector(delta.x)));
		Vector3f deltaY = new Vector3f(getRotation().mult(GlobalAxis.Y.toVector(delta.y)));
		Vector3f deltaZ = new Vector3f(getRotation().mult(GlobalAxis.Z.toVector(delta.z)));
		return new Vector3f(deltaX.x + deltaY.x + deltaZ.x + position.x, deltaX.y + deltaY.y + deltaZ.y + position.y, deltaX.z + deltaY.z + deltaZ.z + position.z);
	}
	
	public boolean hasCollider(){
		return components.containsKey(Collider.COMPONENT_KEY);
	}
	
	public GameObject setTag(GameTag t){
		tag = t;
		return this;
	}
	
	public GameTag getTag(){
		return tag;
	}
	
	public Scene getActiveScene(){
		return this.theScene;
	}

	public void onAddedToScene(Scene scene) {
		for(IComponent comp : components.values()){
			comp.onAddedToScene(scene);
		}
	}
	
	public void onRemovedFromScene(Scene scene){
		for(IComponent comp : components.values()){
			comp.onRemovedFromScene(scene);
		}
	}
	
	private void initSharedData(){
		if(sharedData == null)
			sharedData = new HashMap<String,String>();
	}
	
	protected String getSharedString(String key){
		return hasSharedDataKey(key) ? sharedData.get(key) : "";
	}
	
	protected int getSharedInteger(String key){
		return hasSharedDataKey(key) ? Integer.parseInt(sharedData.get(key)) : 0;
	}
	
	protected float getSharedFloat(String key){
		return hasSharedDataKey(key) ? Float.parseFloat(sharedData.get(key)) : 0.0f;
	}
	
	protected boolean hasSharedDataKey(String key){
		return sharedData.containsKey(key);
	}
	
	protected void setSharedDataString(String key, String value){
		sharedData.put(key, value);
	}
	
	protected void setSharedDataInt(String key, int value){
		sharedData.put(key, Integer.toString(value));
	}
	
	protected void setSharedDataFloat(String key, float value){
		sharedData.put(key, Float.toString(value));
	}
}
