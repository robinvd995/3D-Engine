package engine.renderer.particle;

import engine.Time;
import engine.renderer.Camera;
import engine.utils.Vector2f;
import engine.utils.Vector3f;

public class Particle {

	private Vector3f position;
	private Vector3f velocity;
	private float gravityEffect;
	private float lifeLength;
	private float rotation;
	private float scale;
	
	private float lifeTime;

	private Vector2f texOffset1 = new Vector2f();
	private Vector2f texOffset2 = new Vector2f();
	private float blend = 0.0f;
	
	private ParticleTexture texture;
	private float distance;
	
	private Vector3f reusableChange = new Vector3f();
	
	public Particle(ParticleTexture texture, Vector3f position, Vector3f velocity, float gravityEffect, float lifeLength, float rotation, float scale) {
		super();
		this.texture = texture;
		this.position = position;
		this.velocity = velocity;
		this.gravityEffect = gravityEffect;
		this.lifeLength = lifeLength;
		this.rotation = rotation;
		this.scale = scale;
		ParticleManager.addParticle(this);
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getVelocity() {
		return velocity;
	}

	public float getGravityEffect() {
		return gravityEffect;
	}

	public float getLifeLength() {
		return lifeLength;
	}

	public float getRotation() {
		return rotation;
	}

	public float getScale() {
		return scale;
	}

	public float getLifeTime() {
		return lifeTime;
	}
	
	public ParticleTexture getTexture(){
		return texture;
	}
	
	public Vector2f getTexOffset1() {
		return texOffset1;
	}

	public Vector2f getTexOffset2() {
		return texOffset2;
	}

	public float getBlend() {
		return blend;
	}
	
	public int getTextureRows(){
		return texture.getRows();
	}
	
	public float getDistance(){
		return distance;
	}
	
	protected boolean update(Camera camera){
		velocity.y -= gravityEffect * Time.getDeltaTime();
		reusableChange.set(velocity);
		distance = Vector3f.sub(camera.getPosition(), position, null).lengthSquared();
		reusableChange.scale(Time.getDeltaTime());
		Vector3f.add(reusableChange, position, position);
		lifeTime += Time.getDeltaTime();
		updateTextureCoordInfo();
		return lifeTime < lifeLength;
	}
	
	private void updateTextureCoordInfo(){
		float lifeFactor = lifeTime / lifeLength;
		int stageCount = texture.getRows() * texture.getRows();
		float progression = lifeFactor * stageCount;
		int stage = (int) Math.floor(progression);
		int nextStage = stage < stageCount ? stage + 1 : stage;
		blend = progression % 1;
		setTextureOffset(texOffset1, stage);
		setTextureOffset(texOffset2, nextStage);
	}
	
	private void setTextureOffset(Vector2f offset, int index){
		int column = index % texture.getRows();
		int row = index / texture.getRows();
		offset.x = (float)(column) / (float)texture.getRows();
		offset.y = (float)(row) / (float)texture.getRows();
		
	}
}
