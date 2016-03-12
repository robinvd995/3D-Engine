package engine;

import engine.audio.AudioSource;
import engine.renderer.Camera;
import engine.renderer.ChaserCamera;
import engine.renderer.Light;
import engine.renderer.ModelTexture;
import engine.renderer.OBJLoader;
import engine.renderer.RawModel;
import engine.renderer.RenderComponent;
import engine.renderer.TexturedModel;
import engine.renderer.font.TextMaster;
import engine.renderer.gui.GUIText;
import engine.renderer.gui.GuiRenderer;
import engine.renderer.particle.ParticleSystem;
import engine.renderer.particle.ParticleTexture;
import engine.utils.Vector2f;
import engine.utils.Vector3f;

public class SceneTest extends Scene{

	protected Player thePlayer;
	private ParticleSystem system;
	
	public SceneTest() {
		super();
	}

	@Override
	public void initScene() {
		RawModel model = OBJLoader.loadObjModel("spaceship", MainGameLoop.theLoader);
		ModelTexture texture = new ModelTexture(MainGameLoop.theLoader.loadGameTexture("texture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);

		texture.setShineDamper(10);
		texture.setReflectivity(5);

		GameObject player = new GameObject(this, new Vector3f(0,-5,-25)).addComponent(new Player()).addComponent(new RenderComponent(texturedModel, 0.1f)).addComponent(new AudioSource());
		GameObject camera = new GameObject(this).addComponent(new ChaserCamera(player));
		theCamera = (Camera) camera.getComponent(Camera.COMPONENT_KEY);
		thePlayer = (Player) player.getComponent(Player.COMPONENT_KEY);
		
		Light light0 = new Light(new Vector3f(0,0,-20), new Vector3f(0.41f, 0.25f, 0.85f), new Vector3f(1, 0.01f, 0.002f));
		Light light1 = new Light(new Vector3f(0,0,20), new Vector3f(0.61f, 0.25f, 0.65f), new Vector3f(1, 0.01f, 0.002f));
		Light light2 = new Light(new Vector3f(-20,0,-20), new Vector3f(0.31f, 0.85f, 0.15f), new Vector3f(1, 0.01f, 0.002f));
		//Light light3 = new Light(new Vector3f(20,0,-20), new Vector3f(0.81f, 0.75f, 0.15f));
		
		lights.add(light0);
		lights.add(light1);
		lights.add(light2);
		//lights.add(light3);
		
		//GuiTexture gui = new GuiTexture(MainGameLoop.theLoader.loadGuiTexture("healthbar"), new Vector2f(-0.75f, 0.75f), new Vector2f(0.25f, 0.25f));
		//guiTextures.add(gui);
		
		
		this.addGameObjectToScene(player);
		this.addGameObjectToScene(camera);
		
		addText(new GUIText("3D Space Shooter Game - Development stage", 1f, TextMaster.candara, new Vector2f(0.0f, 0.0f), 1f, true).setColor(1.0f, 1.0f, 1.0f));
		
		
		ParticleTexture particleTexture = new ParticleTexture(MainGameLoop.theLoader.loadGameTexture("particleAtlas"), 2);
		system = new ParticleSystem(particleTexture, 30, 20, 0, 4);
	}

	@Override
	public GuiRenderer createGuiRenderer() {
		return new GuiRenderer();
	}
	
	public void updateScene(){
		super.updateScene();
		system.generateParticles(thePlayer.getPosition());
	}

}
