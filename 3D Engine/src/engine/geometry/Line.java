package engine.geometry;

public class Line {

	public Point p0;
	public Point p1;
	
	public Line(float p00, float p01, float p02, float p10, float p11, float p12){
		this.p0 = new Point(p00, p01, p02);
		this.p1 = new Point(p10, p11, p12);
	}
}
