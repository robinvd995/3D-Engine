package engine.renderer;

public class TexturedModel {

	private RawModel rawModel;
	private ModelTexture modelTexture;
	
	public TexturedModel(RawModel model, ModelTexture texture){
		rawModel = model;
		modelTexture = texture;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getModelTexture() {
		return modelTexture;
	}
	
	
}
