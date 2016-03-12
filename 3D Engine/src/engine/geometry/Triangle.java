package engine.geometry;

public class Triangle {

	public Point p0;
	public Point p1;
	public Point p2;
	
	public Triangle(float p00, float p01, float p02, float p10, float p11, float p12, float p20, float p21, float p22){
		this.p0 = new Point(p00, p01, p02);
		this.p1 = new Point(p10, p11, p12);
		this.p2 = new Point(p20, p21, p22);
	}
}