package engine.geometry;

public class Point {

	public float x;
	public float y;
	public float z;
	
	public Point(float p0, float p1, float p2){
		this.x = p0;
		this.y = p1;
		this.z = p2;
	}
	
	public Point add(Point p){
		x += p.x;
		y += p.y;
		z += p.z;
		return this;
	}
	
	public Point sub(Point p){
		x -= p.x;
		y -= p.y;
		z -= p.z;
		return this;
	}
	
	public Point cross(Point p){
		float cx = y * p.z - z * p.y;
		float cy = z * p.x - x * p.z;
		float cz = x * p.y - y * p.x;
		x = cx;
		y = cy;
		z = cz;
		return this;
	}
	
	public float dot(Point p){
		return x * p.x + y * p.y + z * p.z;
	}
}
