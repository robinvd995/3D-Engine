/*package engine.renderer.font;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class GUITextOld {

	private String textString;
	private float fontSize;

	private int textMeshVao;
	private int vertexCount;
	private Vector3f colour = new Vector3f(0f, 0f, 0f);

	private Vector2f position;
	private float lineMaxSize;
	private int numberOfLines;

	private FontType font;

	private boolean centerText = false;
	
	private Vector3f borderColor = new Vector3f(0.0f, 0.0f, 0.0f);
	private float borderWidth = 0.0f;
	private float borderEdge = 0.1f;

	public GUITextOld(String text, float fontSize, FontType font, Vector2f position, float maxLineLength,
			boolean centered) {
		this.textString = text;
		this.fontSize = fontSize;
		this.font = font;
		this.position = position;
		this.lineMaxSize = maxLineLength;
		this.centerText = centered;
	}

	public void remove() {
		TextMaster.removeText(this);
	}

	public FontType getFont() {
		return font;
	}

	public GUIText setColor(float r, float g, float b) {
		colour.set(r, g, b);
		return this;
	}

	public Vector3f getColour() {
		return colour;
	}

	public int getNumberOfLines() {
		return numberOfLines;
	}

	public Vector2f getPosition() {
		return position;
	}

	public int getMesh() {
		return textMeshVao;
	}

	public void setMeshInfo(int vao, int verticesCount) {
		this.textMeshVao = vao;
		this.vertexCount = verticesCount;
	}

	public int getVertexCount() {
		return this.vertexCount;
	}

	protected float getFontSize() {
		return fontSize;
	}

	protected void setNumberOfLines(int number) {
		this.numberOfLines = number;
	}

	protected boolean isCentered() {
		return centerText;
	}

	protected float getMaxLineSize() {
		return lineMaxSize;
	}

	protected String getTextString() {
		return textString;
	}

	public GUIText addBorder(Vector3f color, float width, float edge){
		borderColor = color;
		borderWidth = width;
		borderEdge = edge;
		return this;
	}
	
	public Vector3f getBorderColor(){
		return borderColor;
	}
	
	public float getBorderWidth(){
		return borderWidth;
	}
	
	public float getBorderEdge(){
		return borderEdge;
	}
	
	public void setText(String s){
		this.textString = s;
	}
	
	public void setPosition(float x, float y){
		this.position.x = x;
		this.position.y = y;
	}
}
*/