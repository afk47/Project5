package entity;

import gui.Window;

public class Collision {

	public boolean collides(Entity ent, Entity ent2) {
		float[] rect1 = toRectangle(ent);
		float[] rect2 = toRectangle(ent2);

		if (rect1[0] < rect2[0] + rect2[3] && rect1[0] + rect1[3] > rect2[0] && rect1[1] < rect2[1] + rect2[2]
				&& rect1[1] + rect1[2] > rect2[1]) {
			return true;
		}
		return false;
	}

	public boolean onScreen(Entity ent, Window win) {
		
		float[] rect = toRectangle(ent);
		if (rect[0] < 0 + win.getWidth() && rect[0] + rect[3] > rect[0] && rect[1] < 0 + win.getHeight()
				&& rect[1] + rect[2] > 0) {
			return true;
		}
		return false;

	}

	/*
	 * Returns an array of floats at each corner of an entities Collision box
	 * 
	 */
	private float[] toRectangle(Entity ent) {
		float[] rect = new float[] { ent.getPosition().x, ent.getPosition().y, ent.getTexture().getHeight(),
				ent.getTexture().getWidth() };
		return rect;

	}
}
