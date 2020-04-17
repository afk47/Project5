package entity;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.joml.Vector3f;

import gui.Window;
import texture.Material;
import texture.Model;
import texture.Texture;

public abstract class Entity {

	// coordinates of center of character and direction player is moving in
	protected float x;
	protected float[] vertices = new float[] { -0.5f, 0.5f, 0, 0.5f, 0.5f, 0, 0.5f, -0.5f, 0, -0.5f, -0.5f, 0, };

	protected float[] texture_coords = new float[] { 0, 0, 1, 0, 1, 1, 0, 1, };

	protected int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };

	protected Matrix4f target = new Matrix4f();

	protected float y;

	// pixels moved per frame/tick -- 0 when not moving
	protected float xVelocity = 0, yVelocity = 0;
	protected float acceleration;

	protected Material texture;
	protected Matrix4f scale;
	//
	protected Vector2f position;
	protected Window window;
	protected Model model = new Model(vertices, texture_coords, indices);
	protected Matrix4f projection;

	protected float resistance = 0;
	protected Animation[] animations;
	private int use_animation;

	protected Transform transform;

	public Entity(int max_animations, Transform transform) {
		this.animations = new Animation[max_animations];

		this.transform = transform;
		this.use_animation = 0;
	}

	protected void setAnimation(int index, Animation animation) {
		animations[index] = animation;
	}

	public void useAnimation(int index) {
		this.use_animation = index;
	}

	public void move(Vector2f direction) {
		transform.pos.add(new Vector3f(direction, 0));

		position.set(transform.pos.x, transform.pos.y);
	}

	public Entity(Window window) {
		position = new Vector2f(0, 0);
		projection = new Matrix4f().setOrtho2D(-window.getWidth() / 2, window.getWidth() / 2, -window.getHeight() / 2,
				window.getHeight() / 2);
	}

	/*
	 * getters and setters for Different
	 * 
	 */

	public float getResistance() {
		return resistance;
	}

	public void setResistance(float r) {
		this.resistance = r;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model m) {
		model = m;
	}

	public Texture getTexture() {
		return texture.getTexture();
	}

	public Material getMaterial() {
		return texture;
	}

	public void setMaterial(Material m) {
		texture = m;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		position = new Vector2f(x, position.y());
		this.x = x;
	}

	public void setY(float y) {
		position = new Vector2f(position.x(), y);
		this.y = y;
	}

	public float getXVelocity() {
		return xVelocity;
	}

	public void setXVelocity(float velocity) {
		xVelocity = velocity;
	}

	public float getYVelocity() {
		return yVelocity;
	}

	public void setYVelocity(float velocity) {
		yVelocity = velocity;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

	public void accelerate() {
		yVelocity += acceleration;

	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public void addPosition(Vector2f position) {
		this.position.add(position);

	}

	public Vector2f getPosition() {
		return position;
	}

	public Matrix4f getProjection() {
		Matrix4f target = new Matrix4f();
		Matrix4f pos = new Matrix4f().setTranslation(new Vector3f(position, 0));

		target = projection.mul(pos, target);

		return target;
	}

	public void update() {
		accelerate();
		addPosition(new Vector2f(position.x() + getXVelocity(), position.y() + getXVelocity()));
		setAcceleration(acceleration - resistance);
	}

}
