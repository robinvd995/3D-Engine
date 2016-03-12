package engine.renderer;

public class DynamicModel extends RawModel{

	private int vboID;
	
	public DynamicModel(int id, int vbo, int vCount) {
		super(id, vCount);
		vboID = vbo;
	}

	public int getVBO(){
		return vboID;
	}
}
