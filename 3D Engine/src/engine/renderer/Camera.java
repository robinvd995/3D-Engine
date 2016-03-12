package engine.renderer;

import engine.MainGameLoop;
import engine.component.ComponentBase;
import engine.component.ComponentData;
import engine.utils.MathHelper;
import engine.utils.Matrix4f;
import engine.utils.Vector2f;
import engine.utils.Vector3f;
import engine.utils.Vector4f;

public class Camera extends ComponentBase{

	public static final String COMPONENT_KEY = "camera";
	
	public Camera(){}

	public void update() {
		
	}
	
	public Matrix4f createViewMatrix(){
		return MathHelper.createViewMatrix(this);
	}
	
	public Vector3f mouseToWorldPosition(){
		return screenToWorldPosition(400, 400);
	}
	
	public Vector3f screenToWorldPosition(float x, float y){
		Vector2f normalizedCoords = DisplayManager.getNormalizedDeviceCoords(x, y);
		Vector4f clipCoords = new Vector4f(normalizedCoords.x, normalizedCoords.y, -1f, 1f);
		Vector4f eyeCoords = toEyeCoords(clipCoords);
		Vector3f worldRay = toWorldCoords(eyeCoords);
		return worldRay;
	}
	
	private Vector3f toWorldCoords(Vector4f eyeCoords){
		Matrix4f invView = Matrix4f.invert(createViewMatrix(), null);
		Vector4f rayWorld = Matrix4f.transform(invView, eyeCoords, null);
		Vector3f mouseRay = new Vector3f(rayWorld.x, rayWorld.y, rayWorld.z);
		mouseRay.normalise();
		return mouseRay;
	}
	
	private Vector4f toEyeCoords(Vector4f clipCoords){
		Matrix4f invProjection = Matrix4f.invert(MainGameLoop.theMasterRenderer.getProjectionMatrix(), null);
		Vector4f eyeCoords = Matrix4f.transform(invProjection, clipCoords, null);
		return new Vector4f(eyeCoords.x, eyeCoords.y, -1.0f, 0.0f);
	}

	@Override
	public String componentID() {
		return COMPONENT_KEY;
	}
	
	@Override
	public void loadComponentData(ComponentData data) {}

	@Override
	public ComponentData writeComponentData() {
		return null;
	}
}