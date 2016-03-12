package engine.audio;

import java.io.IOException;

public class AudioTest {

	public static void main(String[] args) throws IOException, InterruptedException{
		AudioManager.init();
		AudioManager.setListenerData();
		int sound = AudioManager.loadSound("bounce");
		
		AudioSource source = new AudioSource();
		source.play(sound);
		System.out.println("Sleeping...");
		Thread.sleep(1000);
		System.out.println("Closing...");
		
		AudioManager.cleanUp();
	}
}