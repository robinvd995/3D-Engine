package engine;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.input.InputManager;
import engine.renderer.DebugRenderer;
import engine.renderer.DisplayManager;
import engine.renderer.FrameBuffer;
import engine.renderer.Loader;
import engine.renderer.MasterRenderer;
import engine.renderer.ScreenShader;
import engine.renderer.font.TextMaster;
import engine.renderer.particle.ParticleManager;

public class MainGameLoop {

	public static Loader theLoader;
	public static MasterRenderer theMasterRenderer;

	public static GLFWErrorCallback errorCallback;
	
	public static void main(String[] args){

		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		
		DisplayManager.initDisplay();
		GL.createCapabilities();
		Time.init();
		
		InputManager.init(DisplayManager.manager.window);

		theLoader = new Loader();
		theMasterRenderer = new MasterRenderer(theLoader);
		ParticleManager.init(theMasterRenderer.getProjectionMatrix());
		TextMaster.init();

		//AudioManager.init();
		//AudioManager.setListenerData();

		DebugRenderer.init();

		Scene testScene = new SceneCollision();
		testScene.initScene();

		FrameBuffer skyboxBuffer = new FrameBuffer(1024, 800);
		FrameBuffer frameBuffer = new FrameBuffer(1024, 800);
		ScreenShader screenShader = new ScreenShader();
		//GaussianBlurShader blurShader = new GaussianBlurShader();

		GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		
		while(glfwWindowShouldClose(DisplayManager.manager.window) == GLFW_FALSE){
			testScene.updateScene();

			glfwSwapBuffers(DisplayManager.manager.window);
			glfwPollEvents();
			
			skyboxBuffer.bindFrameBuffer();
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			testScene.renderSkybox();
			skyboxBuffer.unbindCurrentFrameBuffer();

			frameBuffer.bindFrameBuffer();
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			testScene.renderScene();
			frameBuffer.unbindCurrentFrameBuffer();

			/*if(Keyboard.isKeyDown(Keyboard.KEY_P)){
				try {
					TextureSaver.saveBufferColorTexture(skyboxBuffer, 1024, 800, "/res/save/skybox", "PNG");
					TextureSaver.saveBufferColorTexture(frameBuffer, 1024, 800, "/res/save/scene", "PNG");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}*/

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL30.glBindVertexArray(frameBuffer.getQuadVAO());

			screenShader.start();
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);

			screenShader.loadGammaCorrection(1.0f);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, skyboxBuffer.getColorTexture());
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

			screenShader.loadGammaCorrection(1.0f);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, frameBuffer.getColorTexture());
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
			
			GL20.glDisableVertexAttribArray(1);
			GL20.glDisableVertexAttribArray(0);
			GL30.glBindVertexArray(0);
			screenShader.stop();

			testScene.renderSceneGui();
			Time.updateTime();
		}

		//AudioManager.cleanUp();
		testScene.closeScene();
		InputManager.cleanUp();
		TextMaster.cleanUp();
		theMasterRenderer.cleanUp();
		theLoader.cleanUp();
		ParticleManager.cleanUp();
		DebugRenderer.cleanUp();
		
		DisplayManager.manager.cleanUp();
		errorCallback.release();
		
	}
}