package engine.renderer;

import engine.GameObject;
import engine.Player;
import engine.utils.GlobalAxis;
import engine.utils.Quaternion;
import engine.utils.Vector3f;

public class ChaserCamera extends Camera{

	private Player thePlayer;
	
	public ChaserCamera(GameObject player){
		thePlayer = (Player) player.getComponent("player");
	}
	
	public void update(){
		Vector3f dest = thePlayer.getParent().getRelativePosition(new Vector3f(0.0f, 5.0f, -40.0f));
		parent.lerp(dest, 0.05F * (thePlayer.getSpeed() + 0.5F));
		Quaternion q = thePlayer.getParent().getRotation();
		q = q.mult(new Quaternion((float)Math.toRadians(180.0f), GlobalAxis.Y.toVector()));
		parent.slerp(q.inverse(), 0.25f);
	}
}