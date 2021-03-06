package texture;


import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public class Texture {

	private ByteBuffer image;
	private int width;
	private int height;

	public Texture(int width, int height, ByteBuffer image) {
		this.image = image;
		this.height = height;
		this.width = width;
	}

	public static Texture loadImage(String path) {
		ByteBuffer image;
		int width;
		int height;

		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer comp = stack.mallocInt(1);
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);

			image = STBImage.stbi_load(path, w, h, comp, 4);
			if (image == null) {
				System.err.println("Couldn't load " + path);
			}
			width = w.get();
			height = h.get();
		}
		return new Texture(width, height, image);
	}
	
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ByteBuffer getImage() {
		return image;
	}

}
