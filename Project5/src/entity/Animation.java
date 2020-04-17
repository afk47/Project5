package entity;

import main.Timer;
import texture.Material;
import texture.Texture;

public class Animation {
	private Material[] frames;
	private int texturePointer;

	private double elapsedTime;
	private double currentTime;
	private double lastTime;
	private double fps;

	public Animation(int amount, int fps, String filename) {
		this.texturePointer = 0;
		this.elapsedTime = 0;
		this.currentTime = 0;
		this.lastTime = Timer.getTime();
		this.fps = 1.0 / fps;

		this.frames = new Material[amount];
		for (int i = 0; i < amount; i++) {
			this.frames[i] = new Material(filename + "/" + i + ".png");
		}
	}

	public void bind() {
		bind(0);
	}

	public void bind(int sampler) {
		this.currentTime = Timer.getTime();
		this.elapsedTime += currentTime - lastTime;

		if (elapsedTime >= fps) {
			elapsedTime = 0;
			texturePointer++;
		}

		if (texturePointer >= frames.length)
			texturePointer = 0;

		this.lastTime = currentTime;

		frames[texturePointer].bind(sampler);
	}
}