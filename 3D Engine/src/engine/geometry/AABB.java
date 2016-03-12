package engine.geometry;

import engine.utils.Vector3f;

public class AABB {

	private float x0;
	private float y0;
	private float z0;
	private float x1;
	private float y1;
	private float z1;
	
	public AABB(float x0, float y0, float z0, float x1, float y1, float z1){
		this.x0 = x0;
		this.y0 = y0;
		this.z0 = z0;
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
	}
	
	public AABB move(Vector3f pos){
		x0 += pos.x;
		x1 += pos.x;
		y0 += pos.y;
		y1 += pos.y;
		z0 += pos.z;
		z1 += pos.z;
		return this;
	}
	
	public static AABB toWorldSpaceCoords(Vector3f position, AABB origin){
		float x0 = origin.x0 + position.x;
		float y0 = origin.y0 + position.y;
		float z0 = origin.z0 + position.z;
		float x1 = origin.x1 + position.x;
		float y1 = origin.y1 + position.y;
		float z1 = origin.z1 + position.z;
		return new AABB(x0, y0, z0, x1, y1, z1);
	}
	
	public static boolean overlap(float ax0, float ay0, float az0, float ax1, float ay1, float az1, float bx0, float by0, float bz0, float bx1, float by1, float bz1){
		if(ax1 < bx0) return false;
		if(ax0 > bx1) return false;
		if(ay1 < by0) return false;
		if(ay0 > by1) return false;
		if(az1 < bz0) return false;
		if(az0 > bz1) return false;
		return true;
	}
	
	public float x0(){
		return x0;
	}
	
	public float y0(){
		return y0;
	}
	
	public float z0(){
		return z0;
	}
	
	public float x1(){
		return x1;
	}
	
	public float y1(){
		return y1;
	}
	
	public float z1(){
		return z1;
	}
	
	public AABB copy(){
		return new AABB(x0, y0, z0, x1, y1, z1);
	}
}
