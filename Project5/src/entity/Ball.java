package entity;

import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import gui.Window;
import gui.World;
import texture.Camera;
import texture.Material;

public class Ball extends Entity {
	
	public Ball(Window window) {
		super(window);
		yVelocity = 1;

		  texture = new Material("testImage2.jpeg");
		  scale = new Matrix4f().translate(new Vector3f(xVelocity, yVelocity, 0)).scale(500);
		  target = scale;
	}


	
	public void bounceY() {
		if (y <= 0) {
			yVelocity *= -1;
		} else if (y >= window.getHeight() - position.y()) {
			yVelocity *= -1;
		}

	}

	
	public void update(float delta, Window window, Camera camera, World world) {
		

		accelerate();
		addPosition(new Vector2f(position.x() + getXVelocity(), position.y() + getXVelocity()));
		setAcceleration(acceleration - resistance);
		Vector2f movement = new Vector2f();
		movement.add(-10 * delta, 0);
		
		movement.add(10 * delta, 0);
		movement.add(0, 10 * delta);
		movement.add(0, -10 * delta);
		
		move(movement);
		
//		if (movement.x != 0 || movement.y != 0)
//			useAnimation(ANIM_WALK);
//		else useAnimation(ANIM_IDLE);
		
		camera.getPosition().lerp(transform.pos.mul(new Vector3f(), new Vector3f()), 0.05f);
	}	

	


	public void setTexture(Material texture) {
		this.texture = texture;
	}

	public Matrix4f getScale() {
		return scale;
	}

	public void setScale(Matrix4f scale) {
		this.scale = scale;
	}

	public Matrix4f getTarget() {
		
		return target ;
	}

}
