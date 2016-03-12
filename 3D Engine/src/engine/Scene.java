package engine;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import engine.physics.Collider;
import engine.physics.PhysicsManager;
import engine.physics.Ray;
import engine.physics.Ray.RayHit;
import engine.renderer.Camera;
import engine.renderer.DebugRenderer;
import engine.renderer.Light;
import engine.renderer.RenderComponent;
import engine.renderer.font.TextMaster;
import engine.renderer.gui.GUIText;
import engine.renderer.gui.GUITexture;
import engine.renderer.gui.GuiRenderer;
import engine.renderer.particle.ParticleManager;

public abstract class Scene {

	protected Camera theCamera;
	private List<GameObject> gameObjects;
	protected List<Light> lights;
	protected List<GUITexture> guiTextures;
	protected List<GUIText> sceneText;
	protected List<Collider> colliders;

	private GuiRenderer guiRenderer;

	private boolean enableDebugRenderer = true;

	public Scene(){
		gameObjects = new ArrayList<GameObject>();
		lights = new ArrayList<Light>();
		guiTextures = new ArrayList<GUITexture>();
		sceneText = new ArrayList<GUIText>();
		colliders = new ArrayList<Collider>();

		guiRenderer = createGuiRenderer();
	}

	public abstract void initScene();

	public abstract GuiRenderer createGuiRenderer();

	public void updateScene(){
		for(GameObject o : gameObjects){
			o.updateGameObject();

			if(o.shouldRender()){
				MainGameLoop.theMasterRenderer.processEntity((RenderComponent) o.getComponent("renderer"));
			}
		}

		ParticleManager.update(theCamera);
	}

	public void renderScene(){
		MainGameLoop.theMasterRenderer.render(lights, theCamera);
		ParticleManager.renderParticles(theCamera);
		if(enableDebugRenderer){
			renderDebug();
		}
	}
	
	public void renderSkybox(){
		MainGameLoop.theMasterRenderer.renderSkybox(theCamera);
	}
	
	public void renderSceneGui(){
		guiRenderer.render(guiTextures);
		TextMaster.render();
	}

	protected void renderDebug(){
		DebugRenderer.addCollidersToDraw(colliders);
		DebugRenderer.render(theCamera);
	}

	public void removeFromScene(GameObject o, boolean alreadyRemoved){
		if(!gameObjects.contains(o) && !alreadyRemoved){
			System.out.println("Game object " + o + " is not in the scene!");
		}

		if(o.hasCollider()){
			removeCollider((Collider) o.getComponent(Collider.COMPONENT_KEY));
		}

		o.onRemovedFromScene(this);
		gameObjects.remove(o);
	}

	private void removeCollider(Collider collider){
		colliders.remove(collider);
	}

	public void closeScene(){
		
		if(!gameObjects.isEmpty()){
			Iterator<GameObject> it = gameObjects.iterator();
			while(it.hasNext()){
				GameObject o = it.next();
				it.remove();
				removeFromScene(o, true);
			}
		}

		theCamera = null;
		gameObjects.clear();
		lights.clear();
		guiTextures.clear();
		guiRenderer = null;

		for(GUIText t : sceneText){
			t.remove();
		}
	}

	protected void addText(GUIText text){
		sceneText.add(text);
		TextMaster.loadText(text);
	}
	
	protected void addText(List<GUIText> text){
		for(GUIText t : text){
			sceneText.add(t);
			TextMaster.loadText(t);
		}
	}

	public void addGameObjectToScene(GameObject obj){
		gameObjects.add(obj);

		if(obj.hasCollider()){
			colliders.add((Collider) obj.getComponent(Collider.COMPONENT_KEY));
		}

		obj.onAddedToScene(this);
	}

	public RayHit rayTrace(Collider caster, Ray ray, EnumSet<GameTag> tags){
		RayHit hit;
		for(Collider collider : colliders){

			if(tags.isEmpty())
				tags = EnumSet.allOf(GameTag.class);

			if(caster != null && caster == collider)
				continue;

			if(!tags.contains(collider.getParent().getTag()))
				continue;

			hit = PhysicsManager.intersects(ray, collider);
			if(hit == null)
				continue;

			return hit;
		}
		return null;
	}

	public Camera theCamera(){
		return theCamera;
	}
	
	public void addGuiTexture(GUITexture texture){
		guiTextures.add(texture);
	}
	
	public void removeGuiTexture(GUITexture texture){
		guiTextures.remove(texture);
	}
}