package textures;

public class ModelTexture {
	
	private int textureID;
	
	private float shineDamper = 1;
	private float specularity = 0;
	
	public ModelTexture(int id) {
		this.textureID = id;
	}

	public int getID() {
		return textureID;
	}

	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getSpecularity() {
		return specularity;
	}

	public void setSpecularity(float specularity) {
		this.specularity = specularity;
	}
	
	
}
