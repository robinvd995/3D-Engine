package engine.renderer.particle;

import engine.Time;
import engine.utils.Vector3f;

public class ParticleSystem {

	private float pps;
	private float speed;
	private float gravity;
	private float lifeLength;
	
	private ParticleTexture texture;
	
	public ParticleSystem(ParticleTexture texture, float pps, float speed, float gravity, float lifeLength) {
		this.pps = pps;
		this.speed = speed;
		this.gravity = gravity;
		this.lifeLength = lifeLength;
		this.texture = texture;
	}
	
	public void generateParticles(Vector3f position){
		float delta = Time.getDeltaTime();
		float particlesToCreate = delta * pps;
		int count = (int) Math.floor(particlesToCreate);
		float partialParticle = particlesToCreate % 1;
		for(int i = 0; i < count; i++){
			emitParticle(position);
		}
		if(Math.random() < partialParticle){
			emitParticle(position);
		}
	}
	
	private void emitParticle(Vector3f position){
		float dirX = (float) Math.random() * 2f - 1f;
		float dirZ = (float) Math.random() * 2f - 1f;
		Vector3f velocity = new Vector3f(dirX, 1.0f, dirZ);
		velocity.normalise();
		velocity.scale(speed);
		new Particle(texture, new Vector3f(position), velocity, gravity, lifeLength, 0, 1);
	}
}
