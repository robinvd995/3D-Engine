package engine.audio;

import static org.lwjgl.openal.AL10.*;

import java.util.HashMap;

import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALContext;
import org.lwjgl.openal.ALDevice;

public class AudioManager {

	private static HashMap<String, Integer> sfxMap = new HashMap<String, Integer>();
	private static ALDevice device;
	private static ALContext context;
	
	public static void init(){
		device = ALDevice.create(null);
		
		if(device == null)
			throw new IllegalStateException("Failed to open the default device!");
		
		ALCCapabilities caps = device.getCapabilities();
		
		System.out.println("OpenALC10: " + caps.OpenALC10);
		System.out.println("OpenALC11: " + caps.OpenALC11);
		System.out.println("caps.ALC_EXT_EFX = " + caps.ALC_EXT_EFX);
		
		context = ALContext.create();
	}
	
	public static void setListenerData(){
		alListener3f(AL_POSITION, 0.0f, 0.0f, 0.0f);
		alListener3f(AL_VELOCITY, 0.0f, 0.0f, 0.0f);
	}
	
	public static int loadSound(String sfx){
		int buffer = alGenBuffers();
		sfxMap.put(sfx, buffer);
		WaveData waveFile = WaveData.create("/res/audio/" + sfx + ".wav");
		if(waveFile == null){
			return 0;
		}
		alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		return buffer;
	}
	
	public static void cleanUp(){
		for(int i : sfxMap.values()){
			alDeleteBuffers(i);
		}
		context.destroy();
		device.destroy();
	}
}