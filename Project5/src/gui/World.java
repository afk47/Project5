package gui;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import texture.*;

public class World {

	public static Material texture = new Material("bg.jpg");
	private Vector3f position;
	private Matrix4f projection;

	private float[] vertices = new float[] { 
			-1f, 1f, 0,
			1f, 1f, 0, 
			1f, -1f, 0, 
			-1f, -1f, 0 };

	private float[] tcoord = new float[] { 0, 0, 1, 0, 1, 1, 0, 1, };

	private int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };

	private Model background = new Model(vertices, tcoord, indices);

	public World(int width, int height) {
		position = new Vector3f(0, 0, 0);
		projection = new Matrix4f().setOrtho2D(-width / 2, width / 2, -height / 2, height / 2);

	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void addPosition(Vector3f position) {
		this.position.add(position);

	}

	public Vector3f getPosition() {

		return position;

	}

	public Matrix4f getProjection() {
		Matrix4f target = new Matrix4f();
		Matrix4f pos = new Matrix4f().setTranslation(position);

		target = projection.mul(pos, target);

		return target;
	}

	public Model getBackground() {
		return background;
	}

	public void setBackground(Model background) {
		this.background = background;
	}

}
