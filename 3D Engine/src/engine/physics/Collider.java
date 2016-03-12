package engine.physics;

import engine.component.ComponentBase;
import engine.component.ComponentData;
import engine.geometry.AABB;
import engine.utils.Vector3f;

public class Collider extends ComponentBase {

	public static final String COMPONENT_KEY = "collider";
	
	private AABB localAABB;
	private Vector3f offset = new Vector3f(0.0f, 0.0f, 0.0f);
	
	public Collider(AABB aabb){
		localAABB = aabb;
	}
	
	public boolean checkCollision(Collider other){
		Vector3f thisParentPos = getPosition();
		Vector3f otherParentPos = other.getPosition();
		return AABB.overlap(localAABB.x0() + offset.x + thisParentPos.x,
							localAABB.y0() + offset.y + thisParentPos.y,
							localAABB.z0() + offset.z + thisParentPos.z,
							localAABB.x1() + offset.x + thisParentPos.x,
							localAABB.y1() + offset.y + thisParentPos.y,
							localAABB.z1() + offset.z + thisParentPos.z,
							other.localAABB.x0() + other.offset.x + otherParentPos.x,
							other.localAABB.y0() + other.offset.y + otherParentPos.y,
							other.localAABB.z0() + other.offset.z + otherParentPos.z,
							other.localAABB.x1() + other.offset.x + otherParentPos.x,
							other.localAABB.y1() + other.offset.y + otherParentPos.y,
							other.localAABB.z1() + other.offset.z + otherParentPos.z);
	}
	
	@Override
	public void update() {
		
	}
	
	public AABB getWorldAABB(){
		return localAABB.copy().move(offset).move(getPosition());
	}
	
	public Vector3f getOffset(){
		return new Vector3f(offset.x, offset.y, offset.z);
	}

	@Override
	public String componentID() {
		return COMPONENT_KEY;
	}

	public float getAABBSizeX(){
		return localAABB.x1() - localAABB.x0();
	}
	
	public float getAABBSizeY(){
		return localAABB.y1() - localAABB.y0();
	}
	
	public float getAABBSizeZ(){
		return localAABB.z1() - localAABB.z0();
	}
	
	public Vector3f getWorldPosition(){
		Vector3f pos = getPosition();
		return new Vector3f(pos.x + offset.x, pos.y + offset.y, pos.z + offset.z);
	}
	
	@Override
	public void loadComponentData(ComponentData data) {
		
	}

	@Override
	public ComponentData writeComponentData() {
		return null;
	}
}
