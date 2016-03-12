package engine.audio;

import org.lwjgl.openal.AL10;

import engine.component.ComponentBase;
import engine.component.ComponentData;
import engine.utils.Vector3f;

public class AudioSource extends ComponentBase{

	public static String COMPONENT_KEY = "audiosource";
	
	private final int sourceID;
	private Vector3f offset;
	
	public AudioSource(){
		this(new Vector3f(0.0f, 0.0f, 0.0f));
	}
	
	public AudioSource(Vector3f offset){
		this.offset = offset;
		this.sourceID = AL10.alGenSources();
		AL10.alSourcef(sourceID, AL10.AL_GAIN, 1);
		AL10.alSourcef(sourceID, AL10.AL_PITCH, 1);
		AL10.alSource3f(sourceID, AL10.AL_POSITION, 0.0f, 0.0f, 0.0f);
	}
	
	public void play(int buffer){
		AL10.alSourcei(sourceID, AL10.AL_BUFFER, buffer);
		AL10.alSourcePlay(sourceID);
	}
	
	public void playLoop(int buffer){
		AL10.alSourcei(sourceID, AL10.AL_LOOPING, AL10.AL_TRUE);
		play(buffer);
	}
	
	public void stopLoop(){
		AL10.alSourcei(sourceID, AL10.AL_LOOPING, AL10.AL_FALSE);
	}
	
	public AudioSource makeRelative(){
		AL10.alSourcei(sourceID, AL10.AL_SOURCE_RELATIVE, AL10.AL_TRUE);
		return this;
	}
	
	public AudioSource setPosition(Vector3f position){
		AL10.alSource3f(sourceID, AL10.AL_POSITION, position.x, position.y, position.z);
		return this;
	}
	
	public void delete(){
		AL10.alDeleteSources(sourceID);
	}

	@Override
	public void update() {
		
	}
	
	private void updatePosition(Vector3f pos){
		
	}
	
	@Override
	public void loadComponentData(ComponentData data) {
		
	}

	@Override
	public ComponentData writeComponentData() {
		return null;
	}

	@Override
	public String componentID() {
		return COMPONENT_KEY;
	}
}
