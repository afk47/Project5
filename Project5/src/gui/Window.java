package gui;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWGammaRamp;
import org.lwjgl.glfw.GLFWVidMode;

import main.Input;

public class Window {

	private int width;
	private int height;
	private boolean fullscreen = false;
	private long window;
	private Input input;

	public Window() {
		setSize(1900, 1000);

	}

	public static void setCallbacks() {
		glfwSetErrorCallback(new GLFWErrorCallback() {
			@Override
			public void invoke(int error, long desc) {
				throw new IllegalStateException(GLFWErrorCallback.getDescription(desc));
			}

		});
	}

	public void createWindow(String title) {

		if (!glfwInit()) {
			System.err.println("failed to initialize window");
			System.exit(1);
		}

		window = glfwCreateWindow(width, height, title, fullscreen ? glfwGetPrimaryMonitor() : 0, 0);

		if (window == 0)
			throw new IllegalStateException("Window Initialization Failed");

		GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
		if (!fullscreen) {
			glfwSetWindowPos(window, (vid.width() - width) / 2, (vid.height() - height) / 2);

			glfwShowWindow(window);
		}
		input = new Input(window);
		glfwMakeContextCurrent(window);
		
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

	public void swapBuffers() {
		glfwSwapBuffers(window);
	}

	public void setSize(int w, int h) {
		width = w;
		height = h;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Input getInput() {
		return input;
	}
	public void update() {
		input.update();
		glfwPollEvents();
	}
	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

}
