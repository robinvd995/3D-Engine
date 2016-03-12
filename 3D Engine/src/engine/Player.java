package engine;

import engine.component.ComponentBase;
import engine.component.ComponentData;
import engine.utils.GlobalAxis;
import engine.utils.MathHelper;

public class Player extends ComponentBase{

	public static final String COMPONENT_KEY = "player";
	
	private float speed = 0.0f;
	private float maxSpeed = 1.0f;
	private float acceleration = 0.01f;
	
	private boolean yAxisInverted = true;
	
	public Player() {
		
	}
	
	public float getSpeed(){
		return speed;
	}
	
	public void update(){
		
		float inversion = yAxisInverted ? -1.0f : 1.0f;
		float rotSpeed = 1F * speed;
		
		/*if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			parent.rotateLocal(-rotSpeed, GlobalAxis.Z.toVector());
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			parent.rotateLocal(rotSpeed, GlobalAxis.Z.toVector());
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			parent.rotateLocal(-rotSpeed * inversion, GlobalAxis.X.toVector());
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			parent.rotateLocal(rotSpeed * inversion, GlobalAxis.X.toVector());
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			parent.rotateLocal(rotSpeed, GlobalAxis.Y.toVector());
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			parent.rotateLocal(-rotSpeed, GlobalAxis.Y.toVector());
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			speed += acceleration;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
			speed -= acceleration;
		}*/
		
		speed = MathHelper.clampf(speed, 0.0f, maxSpeed);
		
		parent.moveForward(speed);
	}

	@Override
	public String componentID() {
		return COMPONENT_KEY;
	}

	@Override
	public void loadComponentData(ComponentData data) {
		
	}

	@Override
	public ComponentData writeComponentData() {
		return null;
	}
	
}
