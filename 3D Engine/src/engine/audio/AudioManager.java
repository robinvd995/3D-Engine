package engine.audio;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.openal.AL11;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALDevice;
import org.lwjgl.openal.ALUtil;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.openal.ALC11.*;
import static org.lwjgl.openal.ALUtil.*;
import static org.lwjgl.stb.STBVorbis.*;

public class AudioManager {

	public static void init(){
		ALDevice device = ALDevice.create(null);
		if(device == null)
			throw new IllegalStateException("Failed to open the default device!");
		
		ALCCapabilities caps = device.getCapabilities();
		
		System.out.println("OpenALC10: " + caps.OpenALC10);
		System.out.println("OpenALC11: " + caps.OpenALC11);
		System.out.println("caps.ALC_EXT_EFX = " + caps.ALC_EXT_EFX);
		
		if(caps.OpenALC11){
			List<String> devices = getStringList(NULL, ALC_ALL_DEVICES_SPECIFIER);
			if(devices == null)
				ALUtil.checkALCError(null);
			else{
				for(int i = 0; i < devices.size(); i++){
					System.out.println(i + ": " + devices.get(i));
				}
			}
		}
		
		String defaultDeviceSpecifier = alcGetString(NULL, ALC_DEFAULT_DEVICE_SPECIFIER);
		assertTrue(defaultDeviceSpecifier!= null);
	}
	
	private static List<String> getStringList(long deviceHandle, int token) {
		long __result = nalcGetString(deviceHandle, token);
		if ( __result == NULL )
			return null;

		ByteBuffer buffer = memByteBuffer(__result, Integer.MAX_VALUE);

		List<String> strings = new ArrayList<String>();

		int offset = 0;
		while ( true ) {
			if ( buffer.get() == 0 ) {
				int limit = buffer.position() - 1;
				if ( limit == offset ) // Previous char was also a \0 == end of list.
					break;

				// Prepare for decoding
				buffer.position(offset); // Start index
				buffer.limit(limit); // \0 index

				// Decode
				strings.add(memDecodeUTF8(buffer));

				// Reset
				buffer.limit(Integer.MAX_VALUE);
				buffer.position(offset = limit + 1); // index after \0
			}
		}

		return strings;
	}
	
	//private static HashMap<String, Integer> sfxMap = new HashMap<String, Integer>();
	/*private static List<Integer> soundBuffers = new ArrayList<Integer>();
	
	public static void init(){
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setListenerData(){
		AL10.alListener3f(AL10.AL_POSITION, 0.0f, 0.0f, 0.0f);
		AL10.alListener3f(AL10.AL_VELOCITY, 0.0f, 0.0f, 0.0f);
	}
	
	public static int loadSound(String sfx){
		int buffer = AL10.alGenBuffers();
		soundBuffers.add(buffer);
		WaveData waveFile = WaveData.create("/res/audio/" + sfx + ".wav");
		if(waveFile == null){
			return 0;
		}
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		return buffer;
	}
	
	public static void cleanUp(){
		for(int i : soundBuffers){
			AL10.alDeleteBuffers(i);
		}
		AL.destroy();
	}*/
}