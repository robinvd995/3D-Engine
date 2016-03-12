package engine;

import engine.component.ComponentBase;
import engine.component.ComponentData;

public class SimpleMove extends ComponentBase{

	public static String COMPONENT_KEY = "simplemove";
	
	private int upKey;
	private int downKey;
	private int leftKey;
	private int rightKey;
	
	public SimpleMove(boolean useArrowKeys){
		/*upKey = useArrowKeys ? Keyboard.KEY_UP : Keyboard.KEY_W;
		downKey = useArrowKeys ? Keyboard.KEY_DOWN : Keyboard.KEY_S;
		leftKey = useArrowKeys ? Keyboard.KEY_LEFT : Keyboard.KEY_A;
		rightKey = useArrowKeys ? Keyboard.KEY_RIGHT : Keyboard.KEY_D;*/
	}
	
	@Override
	public void update() {
		/*if(Keyboard.isKeyDown(upKey)){
			parent.moveGlobal(0.0f, 0.0f, -0.1f);
		}
		if(Keyboard.isKeyDown(downKey)){
			parent.moveGlobal(0.0f, 0.0f, 0.1f);
		}
		if(Keyboard.isKeyDown(leftKey)){
			parent.moveGlobal(-0.1f, 0.0f, 0.0f);
		}
		if(Keyboard.isKeyDown(rightKey)){
			parent.moveGlobal(0.1f, 0.0f, 0.0f);
		}*/
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
