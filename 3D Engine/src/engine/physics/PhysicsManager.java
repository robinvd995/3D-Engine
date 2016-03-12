package engine.physics;

import engine.geometry.AABB;
import engine.physics.Ray.RayHit;
import engine.utils.Vector3f;

public class PhysicsManager {

	public static RayHit intersects(Ray r, Collider col){
		if(rayAABBCheck(r, col.getWorldAABB())){
			r.hit.other = col;
		}
		return r.hit;
	}

	private static boolean rayAABBCheck(Ray r, AABB aabb){
		float tmin;
		float tmax;
		float tymin;
		float tymax;
		float tzmin;
		float tzmax;

		Vector3f[] parameters = new Vector3f[2];
		parameters[0] = new Vector3f(aabb.x0(), aabb.y0(), aabb.z0());
		parameters[1] = new Vector3f(aabb.x1(), aabb.y1(), aabb.z1());

		tmin = (parameters[r.getSign(0)].x - r.getOrigin().x) * r.getInvDirection().x;
		tmax = (parameters[1 - r.getSign(0)].x - r.getOrigin().x) * r.getInvDirection().x;
		tymin = (parameters[r.getSign(1)].y - r.getOrigin().y) * r.getInvDirection().y;
		tymax = (parameters[1 - r.getSign(1)].y - r.getOrigin().y) * r.getInvDirection().y;

		if((tmin > tymax) || (tymin > tmax)){
			return false;
		}
		if(tymin > tmin){
			tmin = tymin;
		}
		if(tymax < tmax){
			tmax = tymax;
		}
		tzmin = (parameters[r.getSign(2)].z - r.getOrigin().z) * r.getInvDirection().z;
		tzmax = (parameters[1 - r.getSign(2)].z - r.getOrigin().z) * r.getInvDirection().z;
		if((tmin > tzmax) || (tzmin > tmax)){
			return false;
		}
		if(tzmin > tmin){
			tmin = tzmin;
		}
		if(tzmax < tmax){
			tmax = tzmax;
		}

		boolean hit = (tmin < r.getMax() && tmax > r.getMin());
		if(hit){
			r.hit = new RayHit();
			r.hit.distance = tmin < 0.0f ? tmax : tmin;
		}
		return hit;
	}
}
