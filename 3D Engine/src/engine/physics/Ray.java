package engine.physics;

import engine.utils.Vector3f;

public class Ray {

	private Vector3f origin;
	private Vector3f direction;
	private Vector3f invDirection;
	private int[] sign = new int[3];
	private float min;
	private float max;
	
	public RayHit hit;
	
	public Ray(Vector3f o, Vector3f d, float mn, float mx){
		this.origin = o;
		this.direction = d;
		this.invDirection = new Vector3f(1.0f / d.x, 1.0f / d.y, 1.0f / d.z);
		this.sign[0] = (invDirection.x < 0) ? 1 : 0;
		this.sign[1] = (invDirection.y < 0) ? 1 : 0;
		this.sign[2] = (invDirection.z < 0) ? 1 : 0;
		this.min = mn;
		this.max = mx;
	}

	public Vector3f getOrigin() {
		return origin;
	}

	public Vector3f getDirection() {
		return direction;
	}

	public Vector3f getInvDirection() {
		return invDirection;
	}

	public int getSign(int i) {
		return sign[i];
	}
	
	public float getMin(){
		return min;
	}
	
	public float getMax(){
		return max;
	}
	
	public static class RayHit{
		public Collider other;
		public float distance;
	}
}
