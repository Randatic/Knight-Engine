package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		RawModel model = OBJLoader.loadObjModel("jake", loader);
		TexturedModel texturedModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("jake")));
		ModelTexture texture = texturedModel.getTexture();
		texture.setShineDamper(5);
		//texture.setSpecularity(1);
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -10), 0, 0, 0, 1);
		
		RawModel model2 = OBJLoader.loadObjModel("dragon", loader);
		TexturedModel texturedModel2 = new TexturedModel(model2, new ModelTexture(loader.loadTexture("test_pattern_5")));
		ModelTexture texture2 = texturedModel2.getTexture();
		texture2.setShineDamper(10);
		texture2.setSpecularity(1);
		Entity entity2 = new Entity(texturedModel2, new Vector3f(0, 0, -20), 0, 0, 0, 1);
		
		Entity[] entities = new Entity[] {entity, entity2};
		
		Light light = new Light(new Vector3f(-1, 0, -5), new Vector3f(1, 1, 1));
		
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()) {
			//entity.increasePosition(0, 0, -0.1f);
			//entity.increaseRotation(0f, 1f, 0f);
			camera.move();
			for(Entity e : entities) {
				renderer.processEntity(e);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
			
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
