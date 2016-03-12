package engine.renderer;

public class RawModel {

	private int vaoID;
	private int vertexCount;
	
	public RawModel(int id, int vCount){
		vaoID = id;
		vertexCount = vCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
	
}
