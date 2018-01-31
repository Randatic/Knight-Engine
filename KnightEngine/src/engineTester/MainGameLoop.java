package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		//OpenGL expects vertices to be defined counterclockwise by default
		float[] vertices = {
			    -0.5f, 0.5f, 0f,
			    -0.5f, -0.5f, 0f,
			    0.5f, -0.5f, 0f,
			    0.5f, 0.5f, 0f,
			  };
		int[] indices = {
				0, 1, 3,
				3, 1, 2
		};
		
		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		ModelTexture texture = new ModelTexture(loader.loadTexture("test_pattern_5"));
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, 0), 0, 0, 0, 1);
		
		while(!Display.isCloseRequested()) {
			//entity.increasePosition(0.002f,	0, 0);
			entity.increaseRotation(0.2f, 0.2f, 0.2f);
			renderer.prepare();
			shader.start();
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
