package engine;

import java.util.EnumSet;

import engine.geometry.AABB;
import engine.input.InputAction;
import engine.input.InputManager;
import engine.physics.Collider;
import engine.physics.Ray;
import engine.physics.Ray.RayHit;
import engine.renderer.Camera;
import engine.renderer.ComponentGuiTag;
import engine.renderer.ComponentGuiTexture;
import engine.renderer.Light;
import engine.renderer.ModelTexture;
import engine.renderer.OBJLoader;
import engine.renderer.RawModel;
import engine.renderer.RenderComponent;
import engine.renderer.TexturedModel;
import engine.renderer.gui.GUITexture;
import engine.renderer.gui.GUITextureMap;
import engine.renderer.gui.GUIWindow;
import engine.renderer.gui.GUIWindowStyle;
import engine.renderer.gui.GuiAnchor;
import engine.renderer.gui.GuiRenderer;
import engine.utils.GlobalAxis;
import engine.utils.Vector3f;
import engine.utils.Vector4f;

public class SceneCollision extends Scene {

	private GameObject cube1;
	private GameObject cube2;

	private GUITexture crosshair;
	
	private float time = 0.0f;
	
	private GUIWindow testWindow;
	
	public SceneCollision() {
		super();
	}

	@Override
	public void initScene() {
		RawModel model = OBJLoader.loadObjModel("cube", MainGameLoop.theLoader);
		ModelTexture texture = new ModelTexture(MainGameLoop.theLoader.loadGameTexture("texture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);

		cube1 = new GameObject(this, new Vector3f(-5.0f, 0.0f, 0.0f)).addComponent(new Collider(new AABB(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f))).addComponent(new SimpleMove(false)).addComponent(new RenderComponent(texturedModel, 1.0f, new Vector4f(0.0f, 1.0f, 0.0f, 1.0f))).setTag(GameTag.PLAYER).addComponent(new ComponentGuiTag());
		cube2 = new GameObject(this, new Vector3f(5.0f, 0.0f, 0.0f)).addComponent(new Collider(new AABB(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f))).addComponent(new SimpleMove(true)).addComponent(new RenderComponent(texturedModel, 1.0f, new Vector4f(0.0f, 0.0f, 1.0f, 1.0f))).setTag(GameTag.FRIENDLY).addComponent(new ComponentGuiTexture());
		//cube1.getComponent(RenderComponent.COMPONENT_KEY).setActive(false);
		//cube2.getComponent(RenderComponent.COMPONENT_KEY).setActive(false);
		GameObject camera = new GameObject(this, new Vector3f(0.0f, 10.0f, 10.0f)).addComponent(new Camera());
		camera.rotateLocal(45.0f, GlobalAxis.X.toVector());

		theCamera = (Camera) camera.getComponent(Camera.COMPONENT_KEY);

		Light light0 = new Light(new Vector3f(-5.0f,0,0), new Vector3f(0.41f, 0.25f, 0.85f), new Vector3f(1, 0.01f, 0.002f));
		Light light1 = new Light(new Vector3f(5.0f, 0,0), new Vector3f(0.61f, 0.25f, 0.65f), new Vector3f(1, 0.01f, 0.002f));
		Light light2 = new Light(new Vector3f(0,0,0), new Vector3f(0.31f, 0.85f, 0.15f), new Vector3f(1, 0.01f, 0.002f));
		Light light3 = new Light(new Vector3f(0,0,0), new Vector3f(1f, 1f, 1f));
		lights.add(light0);
		lights.add(light1);
		lights.add(light2);
		lights.add(light3);

		this.addGameObjectToScene(cube1);
		this.addGameObjectToScene(cube2);
		this.addGameObjectToScene(camera);
		
		GUITextureMap crosshairGuiMap = new GUITextureMap(MainGameLoop.theLoader.loadGuiTexture("crosshairs"), 512);
		crosshair = new GUITexture(crosshairGuiMap, GuiAnchor.CENTER, 512, 0, 0, 0, 128, 128);
		this.guiTextures.add(crosshair);
		
		GUITextureMap testWindowStyleMap = new GUITextureMap(MainGameLoop.theLoader.loadGuiTexture("window"), 256);
		GUIWindowStyle testWindowStyle = new GUIWindowStyle(testWindowStyleMap, 0, 0, 128, 128, 24, 25, 4, 3, 12);
		testWindow = new GUIWindow(testWindowStyle, "Test Window", 200, 200, 300, 300);
		this.guiTextures.addAll(testWindow.getTextures());
		this.addText(testWindow.getText());
	}

	@Override
	public GuiRenderer createGuiRenderer() {
		return new GuiRenderer();
	}

	public void updateScene(){
		super.updateScene();
		time += Time.getDeltaTime();
		float scale = ((float) Math.sin(time) + 1.0f) * 0.5f + 0.5f;
		crosshair.setScale(scale, scale);
		//crosshair.setPosition(Mouse.getX(), 800 - Mouse.getY());
		
		//testWindow.updateWindowPosition(testWindow.getWindowPositionX() +1, testWindow.getWindowPositionY());
		//testWindow.updateWindow(Mouse.getX(), 800 - Mouse.getY());
		
		for(InputAction a : InputAction.values()){
			if(InputManager.manager.isActionKeyDown(a)){
				System.out.println(a);
			}
		}
		
		Collider c1 = (Collider) cube1.getComponent(Collider.COMPONENT_KEY);
		Collider c2 = (Collider) cube2.getComponent(Collider.COMPONENT_KEY);
		RenderComponent r1 = (RenderComponent) cube1.getComponent(RenderComponent.COMPONENT_KEY);
		RenderComponent r2 = (RenderComponent) cube2.getComponent(RenderComponent.COMPONENT_KEY);
		if(c1.checkCollision(c2)){
			r1.setColor(1.0f, 0.0f, 0.0f, 1.0f);
			r2.setColor(1.0f, 0.0f, 0.0f, 1.0f);
		}else{
			r1.setColor(0.0f, 1.0f, 0.0f, 1.0f);
			r2.setColor(0.0f, 0.0f, 1.0f, 1.0f);
		}

		/*if(Mouse.isButtonDown(0)){
			System.out.println("Mouse button down!");
			Vector3f mouseCoords = theCamera.mouseToWorldPosition();
			Ray mouseRay =  new Ray(theCamera.getPosition(), mouseCoords, 0.0f, 50.0f);
			RayHit mouseHit = this.rayTrace(null, mouseRay, EnumSet.allOf(GameTag.class));
			if(mouseHit != null){
				this.removeFromScene(mouseHit.other.getParent(), false);
			}
		}*/
	}
}
